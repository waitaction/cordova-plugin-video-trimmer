package com.iknow.android;

import android.app.Activity;
import android.content.Intent;
import com.iknow.android.features.select.VideoSelectActivity;
import com.iknow.android.features.trim.VideoTrimmerActivity;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TrimmerCordovaPlugin extends CordovaPlugin {

    public static TrimmerCordovaPlugin instance;
    private Activity activity;
    private int REQUEST_GET_TIME = 1;
    
    public TrimmerCordovaPlugin() {
        TrimmerCordovaPlugin.instance = this;
    }

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        activity = cordova.getActivity();

    }

    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {


        if (action.equals("openTrimmerPage")) {
            JSONObject jsObj = args.getJSONObject(0);
            String path = jsObj.getString("path");
            final Intent intent = new Intent();
            intent.setClass(this.activity, VideoTrimmerActivity.class);
            intent.putExtra("path", path);
            TrimmerCordovaPlugin.this.activity.startActivity(intent);
            return true;
        }

        if (action.equals("openSelectVideoPage")) {
            JSONObject jsObj = args.getJSONObject(0);
            String outPath = jsObj.getString("outPath");
            Intent intent = new Intent();
            intent.setClass(this.activity, VideoSelectActivity.class);
            intent.putExtra("outPath",outPath);
            TrimmerCordovaPlugin.this.activity.startActivity(intent);
            callbackContext.success();
            return true;
        }
        if (action.equals("init")){
            ZApplication.init(activity);
            callbackContext.success();
        }
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
