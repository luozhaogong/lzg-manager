package com.lzg.manager.validate.service;

import java.lang.annotation.Annotation;
import java.util.List;

import com.lzg.manager.validate.vo.ClassMetaInfo;

public interface ClassMetaService {

	List<ClassMetaInfo> getMetaForAnnotation(List<Class<? extends Annotation>> annotations);
}
