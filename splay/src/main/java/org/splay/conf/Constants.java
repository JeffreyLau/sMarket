package org.splay.conf;


import org.splay.utils.LogUtils;

/**
 * @author Administrator
 * @version $Rev: 4 $
 * @time 2015-7-15 上午11:05:12
 * @des TODO
 * @updateAuthor $Author: admin $
 * @updateDate $Date: 2015-07-15 11:12:48 +0800 (星期三, 15 七月 2015) $
 * @updateDes TODO
 */
public class Constants {

    public static final int DEBUGLEVEL = LogUtils.LEVEL_ALL;//LEVEL_ALL,显示所有的日子,OFF:关闭日志的显示

    //http://192.168.2.8:8080/GooglePlayServer/home?index=0
    public static final class URL {
        public static final String BASEURL = "http://192.168.2.8:8080/GooglePlayServer/";
        public static final String HOMEURL = BASEURL+"home";
        public static final String IMAGEURL = BASEURL+"image?name=";
    }

}
