package com.lzg.manager.validate.service.impl;

import com.lzg.common.db.BaseDaoImpl;
import com.lzg.manager.validate.po.ValidateItem;
import com.lzg.manager.validate.service.ValidateService;
import org.springframework.stereotype.Service;

/**
 * @author luoZhaoGong
 * @date 2021/6/2
 * @Description:
 */
@Service
public class ValidateServiceImpl extends BaseDaoImpl<ValidateItem,String> implements ValidateService {
}
