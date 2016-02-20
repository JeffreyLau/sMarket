package org.splay.bean;

/**
 * Created by jeffrey on 16-2-19.
 */
public class HomeAppInfo {
    public String des;            // 应用的描述
    public String downloadUrl;    // 应用的下载地址
    public String iconUrl;        // 应用的图标地址
    public long id;            // 应用的id
    public String name;            // 应用的名字
    public String packageName;    // 应用的包名
    public long size;            // 应用的长度
    public float stars;            // 应用的评分

    @Override
    public String toString() {
        return "HomeAppInfo{" +
                "des='" + des + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", packageName='" + packageName + '\'' +
                ", size=" + size +
                ", stars=" + stars +
                '}';
    }
}
