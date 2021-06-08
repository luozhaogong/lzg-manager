package com.lzg.manager.dict.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lzg.common.db.BaseDao;
import com.lzg.manager.dict.po.DictItem;

import java.util.List;

public interface DictService extends BaseDao<DictItem, String> {

	/**
	 * 通过tag模糊查询
	 * @param tag
	 * @return
	 */
	List<DictItem> findTagCode(String tagCode);

	/**
	 * 去重
	 * @param dict
	 * @return
	 */
	boolean checkDictItem(DictItem dict);

	DictItem saveDictItem(DictItem dictItem);
}
