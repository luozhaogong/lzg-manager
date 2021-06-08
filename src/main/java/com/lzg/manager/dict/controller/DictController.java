package com.lzg.manager.dict.controller;

import com.lzg.common.page.PageInfo;
import com.lzg.common.util.RespUtil;
import com.lzg.manager.dict.po.DictItem;
import com.lzg.manager.dict.po.DictTag;
import com.lzg.manager.dict.service.DictService;
import com.lzg.manager.dict.service.DictTagService;
import com.lzg.manager.dict.vo.DictGroup;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典控制台
 *
 * @author LuoZhaoGong
 * @data 2021年6月1日
 */
@RestController
@RequestMapping("/dict")
public class DictController {

    @Resource
    DictService dictService;

    @Resource
    DictTagService dictTagService;

    @GetMapping("/tag")
    public Object queryTag(DictTag tag, PageInfo page) {
        Page<DictTag> pageRes = dictTagService.findAll(tag, page.getPageable(), "tagCode");
        return RespUtil.success(pageRes.getContent(), pageRes.getTotalElements());
    }

    @PostMapping("/tag")
    public Object saveTag(DictTag tag) {
        return RespUtil.success(dictTagService.saveDictTag(tag));
    }

    @DeleteMapping("/tag/{tagCode}")
    public Object deleteTag(@PathVariable String tagCode) {
        dictTagService.deleteDictTag(tagCode);
        return RespUtil.success();
    }

    @GetMapping("/{tagCode}")
    public Object dict(@PathVariable String tagCode) {
        return RespUtil.success(dictService.findAll(DictItem.builder().tagCode(tagCode).build()));
    }

    @GetMapping("/group/{tagCode}")
    public Object dictGroup(@PathVariable String tagCode) {
        List<DictItem> dictItems = dictService.findTagCode(tagCode);
        return RespUtil.success(DictGroup.of(tagCode, dictItems));
    }

    @PostMapping
    public Object save(DictItem dict) {
        return RespUtil.success(dictService.saveDictItem(dict));
    }

    @DeleteMapping
    public Object delete(String[] ids) {
        dictService.deleteByIds(ids);
        return RespUtil.success();
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable String id) {
        dictService.deleteById(id);
        return RespUtil.success();
    }

}
