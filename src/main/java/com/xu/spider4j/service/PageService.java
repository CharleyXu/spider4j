package com.xu.spider4j.service;

import com.xu.spider4j.entity.Page;
import com.xu.spider4j.entity.PageRequest;

public interface PageService<T> {
	Page<T> findByPage(PageRequest pageRequest);
}
