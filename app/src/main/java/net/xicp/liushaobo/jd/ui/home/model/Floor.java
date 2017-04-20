package net.xicp.liushaobo.jd.ui.home.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lshaobocsu@gmail.com on 2017.4.18.
 */

public class Floor<T extends Content> {

    public static final String TYPE_APP_CENTER = "appcenter";
    public static final String TYPE_BANNER = "banner";


    public int bottomMargin = 0;
    public ArrayList<T> content;
    public boolean isShadow;
    public String showName;
    public String type;
    public String textColor;
    public String rightCorner;

    public String verticalInterval;
    public String floorOrder;
    public String head;
    public String innnerInterval;
    public String logoImage;

    public Jump jump;

    public Jump moreJump;


    Floor(String str) {
        try {
            JSONObject jsonObject = new JSONObject(str);
            JSONArray jsonArray1 = new JSONObject(str).getJSONArray("content");
           
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
