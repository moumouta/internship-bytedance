package com.bytedance.pressRestdatabaseVersion.controller;

import com.bytedance.pressRestdatabaseVersion.entity.Website;
import com.bytedance.pressRestdatabaseVersion.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
controller 控制层：用于负责具体模块的业务流程控制，需要调用service 逻辑设计层的接口来控制业务流程。
写入WebsiteController 代码：
*/

@RestController
@RequestMapping("/website")
class WebsiteController {
    @Autowired
    private WebsiteService websiteService;

    @RequestMapping("/getAllshow")
    @ResponseBody
    public List<Website> findAll(){
        return websiteService.findAllWebsite();
    }

    @RequestMapping("/getWebsiteId/{id}")
    public List<Website> findWebsiteById(@PathVariable int id){
        return websiteService.findWebsiteById(id);
    }
}