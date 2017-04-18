package net.xicp.liushaobo.jd.ui.home.model;

import net.xicp.liushaobo.jd.ui.home.model.banner.ImageContent;

import java.util.ArrayList;

/**
 * Created by lshaobocsu@gmail.com on 2017.4.18.
 */

public class Floor<T> {

    private static final String TYPE_APP_CENTER = "appcenter";
    private static final String TYPE_BANNER = "banner";


    private int bottomMargin = 0;
    private T content;
    private boolean isShadow;
    private String showName;
    private String type;

    Floor(){
        //content = new ImageContent();
    }
}
