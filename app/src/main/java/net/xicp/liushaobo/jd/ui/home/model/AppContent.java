package net.xicp.liushaobo.jd.ui.home.model;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    AppContent(String str) {
        super(str);
        try {
            JSONObject jsonObject = new JSONObject(str);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for(int i=0;i<jsonArray.length();i++){
                Data data = new Data(jsonArray.getString(i));
                this.data.add(data);
            }
            this.fontColor = jsonObject.getString("fontColor");
            this.time = jsonObject.getLong("time");
            this.bgPic = jsonObject.getString("bgPic");
            this.lightColorSwitch = jsonObject.getInt("lightColorSwitch");
            this.notification = jsonObject.getInt("notification");
            this.ynSpace = jsonObject.getString("ynSpace");
            this.isReplace = jsonObject.getInt("isReplace");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

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

        Data(String str){
            try {
                JSONObject jsonObject = new JSONObject(str);
                this.id = jsonObject.getInt("id");
                this.icon = jsonObject.getString("icon");
                this.order = jsonObject.getInt("order");
                this.name = jsonObject.getString("name");
                this.appCode = jsonObject.getString("appCode");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
