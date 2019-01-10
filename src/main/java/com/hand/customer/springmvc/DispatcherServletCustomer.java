package com.hand.customer.springmvc;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hand.customer.springmvc.annotation.CustomerController;
import com.hand.customer.springmvc.annotation.CustomerRequestMapping;
import com.hand.customer.springmvc.annotation.CustomerService;
import com.hand.utils.ClassUtil;
import com.hand.utils.StringUtil;

public class DispatcherServletCustomer extends HttpServlet {

	private static final long serialVersionUID = -6787656458826416038L;
	// 扫描包路径
	private String scanPackagePath = "com.hand.customer.springmvc";
	// 将所有自定义注解的class交给此类管理,包含Controller,Service,Dao层相关
	private ConcurrentHashMap<String, Object> containerBeans = new  ConcurrentHashMap<String, Object>();
	// 存放注解中bean的别名
	private ConcurrentHashMap<String, String> beansAlias = new ConcurrentHashMap<String, String>();
	// 存放请求路径与class关系
	private ConcurrentHashMap<String, Object> containerBeansUrl = new ConcurrentHashMap<String, Object>();
	// 存放请求路径与class中方法的关系(方便反射调用)
	private ConcurrentHashMap<String, String> containerBeansMethod = new ConcurrentHashMap<String, String>();



	public DispatcherServletCustomer() {
		if(containerBeans == null || containerBeans.size() == 0) {
			initSpringBean();
		}
	}

	@Override
	public void init() throws ServletException {
		// 初始化容器
		System.out.println("初始化容器开始...");
		initSpringBean();
		// 初始化映射路径
		initHanderMapping();
		System.out.println("初始化容器结束...");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//super.doGet(req, resp);
		doDispatch(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doDispatch(req, resp);
	}
	
	
	private void doDispatch(HttpServletRequest req, HttpServletResponse resp) {
		
		try {
			String url = req.getRequestURI();
			url = url.substring(10, url.length());
			Object beanObject = containerBeansUrl.get(url);
			if(beanObject == null) {
				resp.getWriter().println("http ext not found  controller 404");
				return;
			}
			// 获取请求方法
			String methodName = containerBeansMethod.get(url);
			if(StringUtil.isNotEmpty(methodName)) {
				Class<? extends Object> classInfo = beanObject.getClass();
				Method method = classInfo.getMethod(methodName);
				String resultInfo = (String) method.invoke(beanObject);
				// 视图层
				String suffix = ".jsp";
				String prefix = "/";
				req.getRequestDispatcher(prefix + resultInfo + suffix).forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}

	private void initSpringBean() {

		Map<Class, List<Class>> annotationClassList = getAllAnnotationClass();
		for (List<Class> classList : annotationClassList.values()) {
			initAnnotationBean(classList);
		}
		for (Object classInfo : containerBeans.values()) { 
			attributeInjection(classInfo);
		}
		//beansAlias = null;
	}

	private void initHanderMapping() {

		try {
			String baseUrl = null;
			String methodUrl = null;
			for (Object classObject : containerBeans.values()) {
				baseUrl = "";
				Class<? extends Object> classInfo = classObject.getClass();
				// 获取类上的根路径
				CustomerRequestMapping requestMappingClassAnnotation = classInfo.getAnnotation(CustomerRequestMapping.class);
				if(requestMappingClassAnnotation != null) {
					baseUrl = requestMappingClassAnnotation.value();
				}
				Method[] declaredMethods = classInfo.getDeclaredMethods();
				for (Method method : declaredMethods) {
					methodUrl = "";
					CustomerRequestMapping requestMappingMethodAnnotation  = method.getDeclaredAnnotation(CustomerRequestMapping.class);
					if(requestMappingMethodAnnotation != null) {
						methodUrl = requestMappingMethodAnnotation.value();
						containerBeansUrl.put(baseUrl + methodUrl, classObject);
						containerBeansMethod.put(baseUrl + methodUrl, method.getName());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

	}

	// 属性的依赖注入
	private void attributeInjection(Object object) {

		try {
			Class<? extends Object> classInfo = object.getClass();
			Field[] fieldArray = classInfo.getDeclaredFields();
			CustomerService customerServiceAnnotation = null;
			String filedName = null;
			Object injectionClass = null;
			for (Field field : fieldArray) {
				customerServiceAnnotation = field.getDeclaredAnnotation(CustomerService.class);
				if(customerServiceAnnotation != null) {
					filedName = field.getName();
					injectionClass = containerBeans.get(filedName);
					if(injectionClass != null) {
						field.setAccessible(true);
						field.set(object, injectionClass);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private void initAnnotationBean(List<Class> AnnotationClassList) {

		try {
			Object instance = null;
			String className = null;
			for (Class classInfo : AnnotationClassList) {
				instance = classInfo.newInstance();
				className = classInfo.getSimpleName();
				String beanId = beansAlias.get(className);
				containerBeans.put(beanId, instance);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	// 得到包路径下所有的注解类
	private Map<Class, List<Class>> getAllAnnotationClass() {

		Map<Class, List<Class>> resultAnntotationMap = null;
		if(StringUtil.isNotEmpty(scanPackagePath)) {
			resultAnntotationMap = new HashMap<>();
			for (CustomerAnnotation annotation : CustomerAnnotation.values()) {
				List<Class> annotationList = new ArrayList<Class>();
				resultAnntotationMap.put(annotation.getClazz(), annotationList);
			}
			String beanId = null;
			String className = null;
			// 获取包下所有类
			List<Class<?>> classeList = ClassUtil.getClasses(scanPackagePath);
			for (Class<?> classInfo : classeList) {
				for (CustomerAnnotation annotation : CustomerAnnotation.values()) {
					Annotation clazzAnnotation = classInfo.getDeclaredAnnotation(annotation.getClazz());
					if(clazzAnnotation != null) {
						List<Class> list = resultAnntotationMap.get(annotation.getClazz());
						resultAnntotationMap.get(annotation.getClazz()).add(classInfo);
						// 是否有自定义bean的名字,没有则采用类名小写
						if(clazzAnnotation instanceof CustomerController) {
							beanId = ((CustomerController) clazzAnnotation).value();
						} else if(clazzAnnotation instanceof CustomerService) {
							beanId = ((CustomerService) clazzAnnotation).value();
						}
						// 防止类重名,最好不用SimpleName()方法,这里简单处理
						className = classInfo.getSimpleName();
						if(StringUtil.isEmpty(beanId)) {
							beanId = StringUtil.toLowerCaseFirstOne(className);
						}
						try {
							beansAlias.put(className, beanId);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}

		return resultAnntotationMap;
	}

	public Object getInstanceBean(String beanId) {

		Object resultObject = null;
		if(StringUtil.isNotEmpty(beanId)) {
			resultObject = containerBeans.get(beanId);
		}

		return resultObject;

	}

	public enum CustomerAnnotation {

		CustomerService("customerService", CustomerService.class),
		CustomerController("customerController", CustomerController.class);

		private String name;
		private Class clazz;

		private CustomerAnnotation(String name, Class clazz) {
			this.name = name;
			this.clazz = clazz;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Class getClazz() {
			return clazz;
		}
		public void setClazz(Class clazz) {
			this.clazz = clazz;
		}
	}

}
