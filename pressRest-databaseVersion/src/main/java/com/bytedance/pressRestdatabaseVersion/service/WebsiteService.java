package com.bytedance.pressRestdatabaseVersion.service;

import com.bytedance.pressRestdatabaseVersion.entity.Website;
import com.bytedance.pressRestdatabaseVersion.mapper.WebsiteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/*
service业务层：用于给controller 层的类提供接口进行调用。
写入WebsiteService 代码：
*/

@Service
public class WebsiteService {
    @Autowired(required=false)
    public WebsiteMapper websiteMapper;

    public List<Website> findAllWebsite(){
        return websiteMapper.findAllWebsite();
    }
    public List<Website> findWebsiteById(int id){
        return websiteMapper.findWebsiteById(id);
    }
}