package com.ident.validator.core.model;

/**
 * @author cheny
 * @version 1.0
 * @descr
 * @date 2017/7/18 9:38
 */

public class TagInfo {
    public String tid;
    public String pid;
    public String aid;
    public int vendor;

    public boolean isTest() {
        return 0 == vendor;
    }

    @Override
    public String toString() {
        return "TagInfo{" +
                "tid='" + tid + '\'' +
                ", pid='" + pid + '\'' +
                ", aid='" + aid + '\'' +
                ", vendor=" + vendor +
                '}';
    }
}
