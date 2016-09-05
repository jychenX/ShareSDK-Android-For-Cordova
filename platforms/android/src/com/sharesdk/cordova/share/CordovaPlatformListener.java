package com.sharesdk.cordova.share;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import java.util.HashMap;

import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;

/**
 * Created by jychen on 2016/7/1.
 */
public class CordovaPlatformListener implements PlatformActionListener {

    CallbackContext callbackContext;

    CordovaPlatformListener(CallbackContext callback){
    	callbackContext = callback;
    }
    
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
    	System.out.println("成功");
        //发送事件给javascript层
    	JSONObject json  = new JSONObject();
    	try {
			json.put("platformId", ShareSDK.platformNameToId(platform.getName()));
			json.put("action",i);
	    	json.put("state", 1);
	    	if(json != null){
	    		json.put("data", hashMap);
	    	}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Message msg=new Message();
    	msg.obj = json;
		handler.sendMessage(msg);
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
    	System.out.println("失败"+throwable.toString());
        //发送事件给javascript层
    	JSONObject json  = new JSONObject();
    	try {
			json.put("platformId", ShareSDK.platformNameToId(platform.getName()));
			json.put("action",i);
	    	json.put("state", 0);
	    	json.put("throwable", throwable.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Message msg=new Message();
    	msg.obj = json;
		handler.sendMessage(msg);
    }

    @Override
    public void onCancel(Platform platform, int i) {
    	System.out.println("取消");
        //发送事件给javascript层
    	JSONObject json  = new JSONObject();
    	try {
			json.put("platformId", ShareSDK.platformNameToId(platform.getName()));
			json.put("action",i);
	    	json.put("state", 2);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Message msg=new Message();
    	msg.obj = json;
		handler.sendMessage(msg);
    }
    
    @SuppressLint("HandlerLeak")
	Handler handler=new Handler(){
		public void handleMessage(Message msg) {
			JSONObject jsonToJS = new JSONObject();
			jsonToJS = (JSONObject)msg.obj;
			callbackContext.success(jsonToJS);
		}
    };
}
