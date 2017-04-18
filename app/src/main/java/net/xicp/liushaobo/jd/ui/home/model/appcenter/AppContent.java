package net.xicp.liushaobo.jd.ui.home.model.appcenter;


import net.xicp.liushaobo.jd.ui.home.model.Content;
import net.xicp.liushaobo.jd.ui.home.model.Jump;

import java.util.ArrayList;

/**
 * Created by lshaobocsu@gmail.com on 2017.4.18.
 */

public class AppContent extends Content {

    Position poz;
    ArrayList<Data> data;

    public String fontColor;
    public Long time;
    public String bgPic;
    public int lightColorSwitch;
    public int notification;
    public String ynSpace;
    public int isReplace;

    public class Position {
        public String city;
        public String time;
    }

    public class Data {
        public int id;
        public String icon;
        public int order;
        public String name;
        public String appCode;

        public Jump jump;
    }
}
