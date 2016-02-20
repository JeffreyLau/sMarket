package org.splay.bean;

import java.util.List;

/**
 * Created by jeffrey on 16-2-19.
 */
public class HomeJsonInfo {

    public List<HomeAppInfo> list;

    //首页轮播图地址列表
    public List<String> picture;

    @Override
    public String toString() {
        return "HomeJson{" +
                "list=" + list +
                ", picture=" + picture +
                '}';
    }
}
