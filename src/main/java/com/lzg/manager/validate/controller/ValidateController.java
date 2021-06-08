package com.lzg.manager.validate.controller;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lzg.common.page.PageInfo;
import com.lzg.common.util.RespUtil;
import com.lzg.manager.validate.po.ValidateItem;
import com.lzg.manager.validate.service.ClassMetaService;
import com.lzg.manager.validate.service.ValidateService;
import com.lzg.manager.validate.vo.ClassMetaInfo;

/**
 * 校验控制器
 *
 * @author LuoZhaoGong
 * @data 2021年6月1日
 */
@RestController
@RequestMapping("/validate")
public class ValidateController {

	@Resource
	private ValidateService validateService;

	@Resource
	private ClassMetaService classMetaService;

	@GetMapping("/class")
	public Object instance() {
		List<ClassMetaInfo> metaInfos = classMetaService
				.getMetaForAnnotation(Arrays.asList(Service.class, Controller.class));
		return RespUtil.success(metaInfos, metaInfos.size());
	}

	@GetMapping
	public Object query(ValidateItem item, PageInfo page) {
		Page<ValidateItem> pageRes = validateService.findAll(item, page.getPageable());
		return RespUtil.success(pageRes.getContent(), pageRes.getTotalElements());
	}

	@PostMapping
	public Object save(ValidateItem item) {
		return RespUtil.success(validateService.save(item));
	}

	@DeleteMapping
	public Object delete(String[] ids) {
		validateService.deleteByIds(ids);
		return RespUtil.success();
	}

	@DeleteMapping("/{id}")
	public Object delete(@PathVariable String id) {
		validateService.deleteById(id);
		return RespUtil.success();
	}
}
