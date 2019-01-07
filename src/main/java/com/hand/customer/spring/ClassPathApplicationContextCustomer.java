package com.hand.customer.spring;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.hand.customer.spring.annotation.CustomerService;
import com.hand.utils.ClassUtil;
import com.hand.utils.StringUtil;

/**
 * 基于注解版本实现spring容器管理
 * 实现原理：1.扫描指定路径下的所有class文件
 *        2.对其上有自定义注解的class放入一个全局的Map集合中,同时用一个容器来存放beanId(默认是类名首字母小写)[也可以更具类的类型来注入]
 *        3.遍历所有Map集合对其class中属性上有自定义注解进行赋值(依赖注入)
 * 
 */
@SuppressWarnings("rawtypes")
public class ClassPathApplicationContextCustomer {

	// 扫描包路径
	private String scanPackagePath = null;
	// 将所有自定义注解的class交给此类管理
	private ConcurrentHashMap<String, Object> containerBeans = new  ConcurrentHashMap<String, Object>();
	// 存放注解中bean的别名
	private ConcurrentHashMap<String, String> beansAlias = new ConcurrentHashMap<String, String>();
	
	public ClassPathApplicationContextCustomer(String scanPackagePath) {
		this.scanPackagePath = scanPackagePath;
		List<Class> annotationClassList = getAllAnnotationClass();
		initAnnotationBean(annotationClassList);
		for (Object classInfo : containerBeans.values()) { 
			attributeInjection(classInfo);
		}
		beansAlias = null;
	}
	
	// 得到包路径下所有的注解类
	private List<Class> getAllAnnotationClass() {

		List<Class> resultAllAnnotationClass = null;
		if(StringUtil.isNotEmpty(scanPackagePath)) {
			resultAllAnnotationClass = new ArrayList<Class>();
			String beanId = null;
			String className = null;
			// 获取包下所有类
			List<Class<?>> classeList = ClassUtil.getClasses(scanPackagePath);
			for (Class<?> classInfo : classeList) {
				CustomerService customerServiceAnnotation = classInfo.getDeclaredAnnotation(CustomerService.class);
				if(customerServiceAnnotation != null) {
					resultAllAnnotationClass.add(classInfo);
					// 是否有自定义bean的名字,没有则采用类名小写
					beanId = customerServiceAnnotation.value();
					// 防止类重名,最好不用SimpleName()方法,这里简单处理
					className = classInfo.getSimpleName();
					if(StringUtil.isEmpty(beanId)) {
						beanId = StringUtil.toLowerCaseFirstOne(className);
					}
					beansAlias.put(className, beanId);
				}
			}
		}

		return resultAllAnnotationClass;
	}

	// 初始化bean容器[此方法也可以合并到getAllAnnotationClass()方法中]
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
	
	public Object getInstanceBean(String beanId) {
		
		Object resultObject = null;
		if(StringUtil.isNotEmpty(beanId)) {
			resultObject = containerBeans.get(beanId);
		}
		
		return resultObject;
		
	}
}
