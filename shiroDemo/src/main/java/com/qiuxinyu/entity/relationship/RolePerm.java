package com.qiuxinyu.entity.relationship;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("role_perm")
public class RolePerm {
    @TableId("id")
    private String id;
    @TableField("role_id")
    private String roleId;
    @TableField("perm_id")
    private String permId;
}
