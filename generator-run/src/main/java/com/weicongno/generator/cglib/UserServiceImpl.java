package com.weicongno.generator.cglib;

/**
 * @version 1.0 createTime:2019/9/25 14:44
 * @author:weicong
 */
public class UserServiceImpl implements UserService<String, Integer>{
    public void addUser() {
        System.out.println("增加一个用户。。。");
    }

    public void editUser() {
        System.out.println("编辑一个用户。。。");
    }

}
