package com.lzg.manager.validate.service.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.aop.SpringProxy;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Service;

import com.lzg.manager.validate.service.ClassMetaService;
import com.lzg.manager.validate.vo.ClassMetaInfo;

import cn.hutool.core.util.ObjectUtil;

@Service
public class DefaultClassMetaServiceImpl implements ClassMetaService, ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public List<ClassMetaInfo> getMetaForAnnotation(List<Class<? extends Annotation>> annotations) {
		return getMetaInfos(applicationContext, annotations);
	}

	private List<ClassMetaInfo> getMetaInfos(ApplicationContext applicationContext,
			List<Class<? extends Annotation>> annotations) {
		List<ClassMetaInfo> metaInfos = new ArrayList<>();
		for (Class<? extends Annotation> annotation : annotations) {
			metaInfos.addAll(getMetaInfos(applicationContext, annotation));
		}
		return metaInfos;
	}

	private List<ClassMetaInfo> getMetaInfos(ApplicationContext applicationContext,
			Class<? extends Annotation> annotation) {
		List<ClassMetaInfo> metaInfos = new ArrayList<>();
		List<Object> classList = applicationContext.getBeansWithAnnotation(annotation).values().stream()
				.collect(Collectors.toList());
		for (Object obj : classList) {
			Class<?> objClass = obj.getClass();
			if (SpringProxy.class.isInstance(obj)) {
				objClass = objClass.getSuperclass();
			}
			String className = objClass.getName();
			ClassMetaInfo classMetaInfo = ClassMetaInfo.of(className);
			metaInfos.add(classMetaInfo);

			Method[] declaredMethods = objClass.getDeclaredMethods();
			for (Method declaredMethod : declaredMethods) {
				ClassMetaInfo.MethodMetaInfo methodMetaInfo = ClassMetaInfo.MethodMetaInfo.of(declaredMethod.getName());
				classMetaInfo.addMethod(methodMetaInfo);

				ParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
				Parameter[] parameters = declaredMethod.getParameters();
				if (ObjectUtil.isEmpty(parameters)) {
					continue;
				}
				String[] parameterNames = discoverer.getParameterNames(declaredMethod);
				int len = parameters.length;
				for (int i = 0; i < len; i++) {
					Parameter parameter = parameters[i];
					ClassMetaInfo.ParamMetaInfo paramMetaInfo = ClassMetaInfo.ParamMetaInfo
							.of(parameter.getType().getName(), parameterNames[i]);
					methodMetaInfo.addParam(paramMetaInfo);
				}
			}
		}
		return metaInfos;
	}

}
