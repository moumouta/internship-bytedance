package com.bytedance.NewsPortalBackend.entity;
import com.bytedance.NewsPortalBackend.*;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsAutoIncrement;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.command.BaseModel;
import lombok.NoArgsConstructor;

import java.sql.Date;

//@Data
//@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")//设置表名
public class User extends BaseModel {

    @TableId(type = IdType.AUTO)//mybatis-plus主键注解
    @IsAutoIncrement   //自增
    @IsKey             //actable主键注解
    @Column(comment = "用户ID")//对应数据库字段，不配置name会直接采用属性名作为字段名comment是注解
    private Long id;
    @Column(comment = "昵称")
    private String nickName;
    @Column(comment = "邮箱")
    private String email;
    @Column(name = "create_time",comment = "创建时间")
    private Date createTime;
    @Column(name = "update_time",comment = "修改时间")
    private Date updateTime;
    @Column(comment = "头像")
    private String avatar;
    @Column(comment = "用户名")
    private String username;
    @Column(comment = "密码")
    private String password;
}


