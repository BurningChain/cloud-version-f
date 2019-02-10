package com.miracle.cloud.vo;

import com.miracle.cloud.bean.Jade;
import com.miracle.cloud.bean.User;

import java.util.List;

public class UserWithJadeVO {

    private List<User> users;

    private List<Jade> jades;

    public UserWithJadeVO() {

    }

    public UserWithJadeVO(List<User> users, List<Jade> jades) {
        this.users = users;
        this.jades = jades;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Jade> getJades() {
        return jades;
    }

    public void setJades(List<Jade> jades) {
        this.jades = jades;
    }

    @Override
    public String toString() {
        return "UserWithJadeVO{" +
                "users=" + users +
                ", jades=" + jades +
                '}';
    }
}
