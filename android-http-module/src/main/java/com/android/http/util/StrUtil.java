package com.android.http.util;

import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by liushaobo.xicp.net on 2016/6/14.
 */
public class StrUtil {

    private String url = null;
    private StringBuffer params = new StringBuffer();


    public void setUrl(String url) {
        this.url = url;
    }

    public void setUrl(String url, String... urls) {
        StringBuffer sbUrl = new StringBuffer();
        sbUrl.append(url);
        for (String aurl : urls) {
            sbUrl.append(aurl);
        }
        this.url = sbUrl.toString();
    }

    public void setUrl(String format, Object... objects) {
        this.url = String.format(format, objects);
    }

    public void add(String key, int value) {
        add(key, Integer.toString(value));
    }

    public void add(String key, String value) {
        if (key == null || key.length() == 0) {
            return;
        }
        if (value == null) {
            value = "";
        }
        key = URLEncoder.encode(key);
        if (value != null) {
            value = URLEncoder.encode(value);
        }

        if (params.length() > 0) {
            params.append("&");
        }else{
            if(!url.contains("?")){
                params.append("?");
            }
        }

        String item = String.format("%s=%s", key, value);
        params.append(item);
    }

    public String getQueryParam() {
        return params.toString();
    }

    public String toString() {
        StringBuffer result = new StringBuffer();
        if (url != null) {
            result.append(url);
        }
        result.append(params);
        return result.toString();
    }

    public void Clear() {
        params.setLength(0);
    }


    public StrUtil encodeUrl(String url,Map<String, String> params){

        this.url = url;
        if(params == null){
            return this;
        }
        Iterator iter = params.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            String val = (String) entry.getValue();
            add(key, val);
        }
        return this;
    }
}
