package com.sinocare.base.core.util;

/**
 * Description: 常量
 * Created by jeikerxiao on 2018/6/27 下午5:23
 */
public class Constant {

    /** 超级管理员ID */
    public static final int SUPER_ADMIN = 1;

    /**
     * 菜单类型
     *
     * @author jeikerxiao
     * @date 2018/6/27 下午5:23
     */
    public enum MenuType {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
