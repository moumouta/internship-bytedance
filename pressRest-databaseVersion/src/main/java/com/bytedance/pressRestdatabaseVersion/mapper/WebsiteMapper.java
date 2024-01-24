package com.bytedance.pressRestdatabaseVersion.mapper;

import com.bytedance.pressRestdatabaseVersion.entity.Website;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
mapper 映射层：用于对数据库进行数据持久化操作，其中的方法语句是直接针对数据库操作的，主要实现一些增删改查操作，
在mybatis中方法主要与*Mapper.xml内相互一一映射。
写入WebsiteMapper代码：
*/

@Mapper
public interface WebsiteMapper{
    public List<Website> findAllWebsite();
    public List<Website> findWebsiteById(int id);
}