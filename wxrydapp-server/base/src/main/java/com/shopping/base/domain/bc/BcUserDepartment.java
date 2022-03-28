package com.shopping.base.domain.bc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author user on 2019/7/1.
 */
@Data
@Table(name = "bc_user_department")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BcUserDepartment {
    private static final long serialVersionUID = 7060381435224185276L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    public Long id;
    /** 微信AppId */
    private  String appId;
    /** 部门名称 */
    private String name ;
    /** 部门位置 */
    private String canteenPlace;
    /** 是否启用（审核）状态 */
    private String inuse;

    /** 用户id */
    private String subid;

    /** 用户openid */
    private String subopenid;

}