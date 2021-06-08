package com.lzg.manager.dict.vo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.core.util.ObjectUtil;
import com.lzg.manager.dict.po.DictItem;

import lombok.Data;

@Data
public class DictGroup {
    private String tag;
    private List<Dict> items = new ArrayList<Dict>();
    private Map<String, String> dictMap = new LinkedHashMap<String, String>();

    public static DictGroup of(String tag, List<DictItem> dictItems) {
        DictGroup dictGroup = DictGroup.tag(tag);
        if (ObjectUtil.isNotEmpty(dictItems)) {
            dictItems.forEach(dictGroup::addDictItem);
        }
        return dictGroup;
    }

    public static DictGroup tag(String tag) {
        DictGroup dictGroup = new DictGroup();
        dictGroup.tag = tag;
        return dictGroup;
    }

    public DictGroup add(String name, String value) {
        this.items.add(new Dict(name, value));
        this.dictMap.put(name, value);
        return this;
    }

    public void addDictItem(DictItem dict) {
        this.items.add(new Dict(dict.getName(), dict.getValue()));
        this.dictMap.put(dict.getValue(), dict.getName());
    }
}
