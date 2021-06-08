package com.lzg.manager.dict.service;

import com.lzg.common.db.BaseDao;
import com.lzg.manager.dict.po.DictItem;
import com.lzg.manager.dict.po.DictTag;

public interface DictTagService extends BaseDao<DictTag, String> {

    DictTag saveDictTag(DictTag tag);

    void deleteDictTag(String tagCode);
}
