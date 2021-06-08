package com.lzg.manager.dict.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.lzg.common.db.BaseDaoImpl;
import com.lzg.common.db.PredicateBuilder;
import com.lzg.common.exception.GrobalException;
import com.lzg.manager.dict.po.DictItem;
import com.lzg.manager.dict.po.DictTag;
import com.lzg.manager.dict.service.DictService;
import com.lzg.manager.dict.service.DictTagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class DictTagServiceImpl extends BaseDaoImpl<DictTag,String> implements DictTagService {

    @Resource
    DictService dictService;

    @Override
    @Transactional
    public DictTag saveDictTag(DictTag tag) {
        String msgTemp = "{}字段不能为空";
        Assert.isTrue(StrUtil.isNotEmpty(tag.getTagCode()),msgTemp, "标签编码");
        Assert.isTrue(StrUtil.isNotEmpty(tag.getTagName()),msgTemp, "标签名称");
        if(checkTag(tag)){
            return save(tag);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteDictTag(String tagCode) {
        delete(DictTag.builder().tagCode(tagCode).build());
        dictService.delete(DictItem.builder().tagCode(tagCode).build());
    }

    private boolean checkTag(DictTag tag) {
        DictTag condi = DictTag.builder().tagName(tag.getTagName()).tagCode(tag.getTagCode()).build();
        List<DictTag> items = findAll(PredicateBuilder.of(condi).or(PredicateBuilder.LinkOperator.or("tagCode", "tagName")).build());
        if (ObjectUtil.isEmpty(items)) {
            return true;
        }
        if (items.size() == 1 && items.get(0).getId().equals(tag.getId())) {
            return true;
        }
        throw GrobalException.of("tagCode或tagName重复");
    }


}
