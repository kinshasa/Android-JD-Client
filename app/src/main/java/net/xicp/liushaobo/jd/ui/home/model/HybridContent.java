package net.xicp.liushaobo.jd.ui.home.model;


import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by lshaobocsu@gmail.com on 2017.4.18.
 */

public class HybridContent extends Content {

    ArrayList<SubFloor> subFloors;

    public String subFloorNum;

    HybridContent(String str) {
        super(str);
    }

    public class SubFloor {
        ArrayList<Data> data;
        public String tpl;
    }

    public class Data {
        public int rcJumpType;
        public String labelAnimation;
        public String labelWords;
        public String rcSourceValue;
        public String labelColor;
        public String moduleBgColor;
        public String img;
        public String rightCorner;
        public String maintitleColor;
        public int id;
        public String showName;
        public int imageType;
        public String subtitle;
        public String moduleTitleColor;
        public String rcJumpUrl;
        public String subtitleColor;
        public String rcJumpTo;
        public String showNameImg;

        public Advert advert;
        public Jump jump;
        public Content content;
    }

    public class Advert {

    }

    public class Content {
        public int timeRemain;
        public String message;
        public String sourceValue;
        public String scheme;
        public boolean overTime;
        public int miaoshaAdvance;
        public String name;
        public String algorithmFrom;
        public String nextMiaoshaKey;
        public String functionId;

        ArrayList<MiaoSha> indexMiaoSha;

    }

    public class MiaoSha {
        public int wareId = 1216716;
        public String wname = "【京东超市】爱他美Aptamil 幼儿配方奶粉3段(12-36个月适用)800g(德国原装进口) ";
        public String imageurl = "https://m.360buyimg.com/mobilecms/s190x190_jfs/t3673/225/2012882163/339966/eea645c0/583cf13cN71d919ff.jpg!q70.jpg.webp";
        public String good = "";
        public String jdPrice = "250";
        public String book = "false";
        public String promotion = "false";
        public String spuId = "1279473";
        public String adword = "";
        public String message = "";
        public String canBuy = "true";
        public String miaoSha = "true";
        public String rate = "7.4折";
        public int startRemainTime = -6918;
        public int endRemainTime = 79480;
        public String miaoShaPrice = "185";
        public String discount = "65.00";
        public String activeId = "295874";
        public String canFreeRead = "false";
        public String moreFunId = "searchCatelogy";
        public String cid = "";
        public String cName = "";
        public String sourceValue = "27_1_1216716_0_3";
        public String discountNew = "7.4折";
        public String promotionId = "198053863";
        public int tagType = 6;
        public String tagText = "囤货";
        public String startTimeShow = "08:00";
        public int resultSort = 3;
        public String seckillNum = "3000";
    }
}
