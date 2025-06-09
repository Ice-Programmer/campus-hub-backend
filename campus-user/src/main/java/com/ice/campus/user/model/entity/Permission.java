package com.ice.campus.user.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 权限
 * @TableName permission
 */
@TableName(value ="permission")
@Data
public class Permission implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 权限编码
     */
    private String permissionCode;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 父权限 id
     */
    private Long parentId;

    /**
     * 状态 0-禁用 1-启用
     */
    private Integer status;

    /**
     * 是否系统内置权限 0-否 1-是
     */
    private Integer isSystem;

    /**
     * 权限描述
     */
    private String description;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}