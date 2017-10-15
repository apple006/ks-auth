package com.kingfisher.model;

import javax.persistence.*;

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "roleDesc")
    private String roledesc;

    @Column(name = "roleType")
    private String roletype;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return roleDesc
     */
    public String getRoledesc() {
        return roledesc;
    }

    /**
     * @param roledesc
     */
    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc;
    }

    /**
     * @return roleType
     */
    public String getRoletype() {
        return roletype;
    }

    /**
     * @param roletype
     */
    public void setRoletype(String roletype) {
        this.roletype = roletype;
    }
}