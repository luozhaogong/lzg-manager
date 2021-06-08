package com.lzg.manager.dict.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.lzg.common.db.BaseDao;
import com.lzg.common.db.BaseDaoImpl;
import com.lzg.common.db.PredicateBuilder;
import com.lzg.common.exception.GrobalException;
import com.lzg.common.util.SpecificationUtil;
import com.lzg.manager.dict.po.DictItem;
import com.lzg.manager.dict.po.DictTag;
import com.lzg.manager.dict.service.DictService;
import com.lzg.manager.dict.service.DictTagService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

/**
 * @author luoZhaoGong
 * @date 2021/6/2
 * @Description:
 */
@Service
public class DictServiceImpl extends BaseDaoImpl<DictItem, String> implements DictService {

    @Resource
    DictTagService dictTagService;

    @Override
    public List<DictItem> findTagCode(String tagCode) {
        if(ObjectUtil.isEmpty(tagCode)){
            return Collections.emptyList();
        }
        return findAll(DictItem.builder().tagCode(tagCode).build(), Sort.by(Sort.Order.asc("orderIndex"), Sort.Order.desc("createTime")));
    }

    @Override
    public boolean checkDictItem(DictItem dict) {
        String msgTemplet = "{}字段不能为空";
        Assert.notEmpty(dict.getTagCode(), msgTemplet, "tagId");
        Assert.notEmpty(dict.getName(), msgTemplet, "name");
        Assert.notEmpty(dict.getValue(), msgTemplet, "value");
        List<DictItem> dictItems = findAll(PredicateBuilder.of(DictItem.builder().tagCode(dict.getTagCode()).name(dict.getName()).value(dict.getValue()).build()).or(PredicateBuilder.LinkOperator.or("name", "value")).build());
        if (ObjectUtil.isEmpty(dictItems)) {
            return true;
        }
        if (dictItems.size() == 1 && dictItems.get(0).getId().equals(dict.getId())) {
            return true;
        }
        throw GrobalException.of("字典name或value重复");
    }

    @Override
    @Transactional
    public DictItem saveDictItem(DictItem dictItem) {
        if (checkDictItem(dictItem)) {
            return save(dictItem);
        }
        return null;
    }

}
