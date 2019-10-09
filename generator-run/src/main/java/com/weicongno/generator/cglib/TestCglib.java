package com.weicongno.generator.cglib;

/**
 * @version 1.0 createTime:2019/9/25 14:48
 * @author:weicong
 */
public class TestCglib {
    public static void main(String[] args) {
        UserServiceCglib cglib = new UserServiceCglib();
        UserServiceImpl bookFacedImpl = (UserServiceImpl) cglib.getInstance(new UserServiceImpl());
        bookFacedImpl.addUser();
    }
}
