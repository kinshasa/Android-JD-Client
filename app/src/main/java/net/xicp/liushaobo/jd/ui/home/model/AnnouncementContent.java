package net.xicp.liushaobo.jd.ui.home.model;


import java.util.ArrayList;

/**
 * Created by lshaobocsu@gmail.com on 2017.4.18.
 */

public class AnnouncementContent extends Content {

    ArrayList<Announcement> announcement;

    public String img;
    public int rotate;

    AnnouncementContent(String str) {
        super(str);
    }

    public class Announcement {
        public String slogan;
        public String content;

        public Jump jump;
    }
}
