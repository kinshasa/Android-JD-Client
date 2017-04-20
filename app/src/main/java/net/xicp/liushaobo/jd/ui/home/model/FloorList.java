package net.xicp.liushaobo.jd.ui.home.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lshaobocsu@gmail.com on 2017.4.18.
 */

public class FloorList {

    //京东首页Banner图片轮播图
    public Floor<BannerContent> bannerContentFloor;

    public Floor<AppContent> appContentFloor;

    public Floor<AnnouncementContent> announcementContentFloor;

    public Floor<HybridContent> hybridContentFloor;


    FloorList(){

    }

    FloorList(String str){
        try {
            JSONArray jsonArray = new JSONArray(str);
            setBannerContentFloor(jsonArray.getString(0));
            setAppContentFloor(jsonArray.getString(1));



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    FloorList(JSONArray jsonArray){

    }

    public void setBannerContentFloor(String str) {
        this.bannerContentFloor = new Floor<>(str);
        try {
            JSONArray jsonArray = new JSONObject(str).getJSONArray("content");
            for(int i=0;i<jsonArray.length();i++){
                BannerContent bannerContent = new BannerContent(jsonArray.getString(i));
                this.bannerContentFloor.content.add(bannerContent);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void setAppContentFloor(String str) {
        this.appContentFloor = new Floor<>(str);
        try {
            String appStr = new JSONObject(str).getString("content");
            //this.appContentFloor.content = new AppContent(appStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
