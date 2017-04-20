package net.xicp.liushaobo.jd.ui.home.model;

import net.xicp.liushaobo.jd.ui.home.model.Content;
import net.xicp.liushaobo.jd.ui.home.model.Jump;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lshaobocsu@gmail.com on 2017.4.18.
 */

public class BannerContent extends Content{
    private String activityId;
    private String sortno;
    private String abt;
    private String title;
    private String wareDisplayType;
    private String horizontalImag;
    private String exposalUrl;
    private String clickUrl;
    private Jump jump;

    BannerContent(String str){
        super(str);
        try {
            JSONObject jsonObject = new JSONObject(str);
            this.activityId = jsonObject.getString("activityId");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    Content init(String str){
        try {
            JSONObject jsonObject = new JSONObject(str);
            this.activityId = jsonObject.getString("activityId");
            this.horizontalImag = jsonObject.getString("horizontalImag");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }
}