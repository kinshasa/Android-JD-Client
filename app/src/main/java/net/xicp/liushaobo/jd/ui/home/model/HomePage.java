package net.xicp.liushaobo.jd.ui.home.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lshaobocsu@gmail.com on 2017.4.18.
 */

public class HomePage {
    public String naviVer;
    public int tipsIdleTime;
    public String toTopBtnImg;
    public int cycNum;
    public int firework;
    public String tipsShowTime;
    public String cycFirstTimeStamp;
    public String code;
    public int executeTime;
    public int appCenterAnimations;
    public int tipsFunction;
    public String personalSourceValue;
    public int tagAnimations;
    public Boolean canBeDefault;
    public int tipsShowType;

    public FloorList floorList;
    public int lazy;
    public String toBottomBtnImg;

    public HomePage() {

    }

    public HomePage(String str) {
        try {
            JSONObject jsonObject = new JSONObject(str);
            this.naviVer = jsonObject.getString("naviVer");
            String floorStr = jsonObject.getString("floorList");
            this.floorList = new FloorList(floorStr);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
