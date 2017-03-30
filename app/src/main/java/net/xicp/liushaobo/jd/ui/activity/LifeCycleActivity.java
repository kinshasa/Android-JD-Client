package net.xicp.liushaobo.jd.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import net.xicp.liushaobo.jd.Logutils.L;


/**
 * Created by liusp@gagc.com.cn on 2016/6/24.
 */
public class LifeCycleActivity extends AppCompatActivity {

    /**
     * 当前页面是否需要加入生命周期日志记录
     */
    protected static boolean lifeCycleLog = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(lifeCycleLog){
            L.v();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        if(lifeCycleLog){
            L.v();
        }
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        if(lifeCycleLog){
            L.v();
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(lifeCycleLog){
            L.v();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(lifeCycleLog){
            L.v();
        }
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    protected void onStart() {
        if(lifeCycleLog){
            L.v();
        }
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(lifeCycleLog){
            L.v();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(lifeCycleLog){
            L.v();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(lifeCycleLog){
            L.v();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(lifeCycleLog){
            L.v();
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        if(lifeCycleLog){
            L.v();
        }
    }

    @Override
    protected void onDestroy() {
        if(lifeCycleLog){
            L.v();
        }
        super.onDestroy();

    }


    /**
     * 隐藏键盘
     */
    @SuppressWarnings("unused")
    public void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            View localView = getCurrentFocus();
            if (localView != null && localView.getWindowToken() != null) {
                IBinder windowToken = localView.getWindowToken();
                inputMethodManager.hideSoftInputFromWindow(windowToken, 0);
            }
        }
    }

    /**
     * 是否打开 生命周期日志
     * @param enable
     */
    @SuppressWarnings("unused")
    public static void setLifeCycleLog(boolean enable){

        LifeCycleActivity.lifeCycleLog = enable;
    }

}
