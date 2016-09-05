package com.sharesdk.cordova.share;

import android.content.Context;
import android.os.Handler;
import android.telecom.Call;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;

import com.mob.tools.utils.Hashon;

import android.os.Bundle;
import android.os.Handler;

import com.mob.tools.utils.UIHandler;
import com.sharesdk.cordovad.MainActivity;

import android.os.Message;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaActivity;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;

/**
 * Created by jychen on 2016/6/14.
 */
public class ShareSDKUtils extends CordovaPlugin implements Handler.Callback {

    private Context context;
    private static boolean DEBUG = true;
    private static boolean disableSSO = false;
    private static CallbackContext callbackListener;

    private static final int MSG_INITSDK = 1;
    private static final int MSG_AUTHORIZE = 2;
    private static final int MSG_SHOW_USER = 3;
    private static final int MSG_SHARE = 4;
    private static final int MSG_ONEKEY_SAHRE = 5;
    private static final int MSG_GET_FRIENDLIST = 6;
    private static final int MSG_FOLLOW_FRIEND = 7;

    
    @Override
	public boolean execute(String action, JSONArray json, CallbackContext callbackContext) throws JSONException {
    	context = ShareSDKUtils.this.cordova.getActivity().getApplicationContext();
    	callbackListener = callbackContext;
    	if(action.equals("initSDK")){
    		if(TextUtils.isEmpty(json.getString(0))){
    			return false;
    		}
    		initSDK(json.getString(0));
    	}else if(action.equals("setPlatformConfig")){
    		
    	}else if(action.equals("authorize")){
    		int platId = json.getInt(0);
    		authorize(platId);
    		
    	}else if(action.equals("ShowUser")){
    		int platId = json.getInt(0);
            showUser(platId);
            
    	}else if(action.equals("removeAccount")){
    		int platId = json.getInt(0);
    		removeAccount(platId);
    		
    	}else if(action.equals("isAuthValid")){
    		int platId = json.getInt(0);
    		isAuthValid(platId, callbackContext);
    		
    	}else if(action.equals("isClientValid")){
    		int platId = json.getInt(0);
    		isClientValid(platId, callbackContext);
    		
    	}else if(action.equals("shareContent")){
    		int platId = json.getInt(0);
    		String shareContent = json.getString(1);
    		shareContent(platId, shareContent);
    		
    	}else if(action.equals("onekeyShare")){
    		int platId = json.getInt(0);
    		String shareContent = json.getString(1); 
    		onekeyShare(platId, shareContent);
    	
    	}else if(action.equals("getFriendList")){
    		int platId = json.getInt(0);
    	
    	}else if(action.equals("followFriend")){
    		int platId = json.getInt(0);
    		int count = json.getInt(1);
    		int page = json.getInt(2);
    		getFriendList(platId, count, page);
    		
    	}else if(action.equals("getAuthInfo")){
    		int platId = json.getInt(0);
    		getAuthInfo(platId, callbackContext);
    		
    		
    	}else if(action.equals("disableSSOWhenAuthorize"))  {   //action=echo 
    		Boolean isSSO = json.getBoolean(0);
    		disableSSOWhenAuthorize(isSSO);
            
        }
    	else{
            callbackContext.error("method is not found!");
            return  false;
        }
    	return true;
	}


	public void initSDK(String appKey) {
        if (DEBUG) {
            System.out.println("initSDK appkey ==>>" + appKey);
        }
        if (!TextUtils.isEmpty(appKey)) {
            ShareSDK.initSDK(context, appKey);
        } else {
            ShareSDK.initSDK(context);
        }
    }

    
    public void setPlatformConfig(String configs) {
        if (DEBUG) {
            System.out.println("initSDK configs ==>>" + configs);
        }

        if (!TextUtils.isEmpty(configs)) {
            Message msg = new Message();
            msg.what = MSG_INITSDK;
            msg.obj = configs;
            UIHandler.sendMessageDelayed(msg, 1000, this);
        }
    }

    
    public void authorize(int platform) {
        if (DEBUG) {
            System.out.println("ShareSDKUtils.authorize");
        }
        Message msg = new Message();
        msg.what = MSG_AUTHORIZE;
        msg.arg1 = platform;
        UIHandler.sendMessage(msg, this);
    }

    
    public void removeAccount(int platform) {
        if (DEBUG) {
            System.out.println("ShareSDKUtils.removeAccount");
        }
        String name = ShareSDK.platformIdToName(platform);
        Platform plat = ShareSDK.getPlatform(name);
        plat.removeAccount(true);
    }

    
    public void isAuthValid(int platform, CallbackContext callback) {
        if (DEBUG) {
            System.out.println("ShareSDKUtils.isAuthValid");
        }
        String name = ShareSDK.platformIdToName(platform);
        Platform plat = ShareSDK.getPlatform(name);
        Toast.makeText(context, String.valueOf(plat.isAuthValid()), Toast.LENGTH_LONG).show();
        if(plat.isAuthValid()){
        	callback.success(1);
        }else{
            callback.success(0);
        }
    }

    
    @JavascriptInterface
    public void isClientValid(int platform, CallbackContext callback) {
        if (DEBUG) {
            System.out.println("ShareSDKUtils.isClientValid");
        }
        String name = ShareSDK.platformIdToName(platform);
        Platform plat = ShareSDK.getPlatform(name);
        Toast.makeText(context, String.valueOf(plat.isClientValid()), Toast.LENGTH_LONG).show();
        if(plat.isClientValid()){
        	callback.success(1);
        }else{
            callback.success(0);
        }
    }


    
    public void showUser(int platform) {
        if (DEBUG) {
            System.out.println("ShareSDKUtils.showUser");
        }
        Message msg = new Message();
        msg.what = MSG_SHOW_USER;
        msg.arg1 = platform;
        UIHandler.sendMessage(msg, this);
    }

    
    public void shareContent(int platform, String content) {
        if (DEBUG) {
            System.out.println("ShareSDKUtils.share");
        }
        Message msg = new Message();
        msg.what = MSG_SHARE;
        msg.arg1 = platform;
        msg.obj = content;
        UIHandler.sendMessage(msg, this);
    }

    
    public void onekeyShare(int platform, String content) {
        if (DEBUG) {
            System.out.println("ShareSDKUtils.OnekeyShare");
        }
        Message msg = new Message();
        msg.what = MSG_ONEKEY_SAHRE;
        msg.arg1 = platform;
        msg.obj = content;
        UIHandler.sendMessage(msg, this);
    }

    
    public void getFriendList(int platform, int count, int page) {
        if (DEBUG) {
            System.out.println("ShareSDKUtils.getFriendList");
        }
        Message msg = new Message();
        msg.what = MSG_GET_FRIENDLIST;
        msg.arg1 = platform;
        Bundle data = new Bundle();
        data.putInt("page", page);
        data.putInt("count", count);
        msg.setData(data);
        UIHandler.sendMessage(msg, this);
    }

    
    public void followFriend(int platform, String account) {
        if (DEBUG) {
            System.out.println("ShareSDKUtils.followFriend");
        }

        Message msg = new Message();
        msg.what = MSG_FOLLOW_FRIEND;
        msg.arg1 = platform;
        msg.obj = account;
        UIHandler.sendMessage(msg, this);
    }

    
    @JavascriptInterface
    public void getAuthInfo(int platform, CallbackContext callback) {
        if (DEBUG) {
            System.out.println("ShareSDKUtils.getAuthInfo");
        }
        String name = ShareSDK.platformIdToName(platform);
        Platform plat = ShareSDK.getPlatform(name);
        JSONObject jsonMap = new JSONObject();
        if(plat.isAuthValid()){
        	
        	try {
				jsonMap.put("expiresTime", plat.getDb().getExpiresTime());
				jsonMap.put("expiresIn", plat.getDb().getExpiresIn());
	        	jsonMap.put("token", plat.getDb().getToken());
	        	jsonMap.put("tokenSecret", plat.getDb().getTokenSecret());
	            jsonMap.put("userGender", plat.getDb().getUserGender());
	            jsonMap.put("userID", plat.getDb().getUserId());
	            jsonMap.put("openID", plat.getDb().get("openid"));
	            jsonMap.put("userName", plat.getDb().getUserName());
	            jsonMap.put("userIcon", plat.getDb().getUserIcon());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
        System.out.println(jsonMap.toString());
        callback.success(jsonMap);
    }

    
    public void disableSSOWhenAuthorize(boolean open){
        disableSSO = open;
    }

    @SuppressWarnings("unchecked")
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_INITSDK: {
                if (DEBUG) {
                    System.out.println("ShareSDKUtils.setPlatformConfig");
                }
                String configs = (String) msg.obj;
                Hashon hashon = new Hashon();
                HashMap<String, Object> devInfo = hashon.fromJson(configs);
                for(Entry<String, Object> entry: devInfo.entrySet()){
                    String p = ShareSDK.platformIdToName(Integer.parseInt(entry.getKey()));
                    if (p != null) {
                        if (DEBUG) {
                            System.out.println(p + " ==>>" + new Hashon().fromHashMap((HashMap<String, Object>)entry.getValue()));
                        }
                        ShareSDK.setPlatformDevInfo(p, (HashMap<String, Object>)entry.getValue());
                    }
                }
            }
            break;
            case MSG_AUTHORIZE: {
                int platform = msg.arg1;
                String name = ShareSDK.platformIdToName(platform);
                Platform plat = ShareSDK.getPlatform(name);
//                plat.setPlatformActionListener(new RNPlatformListener(context));
                plat.SSOSetting(disableSSO);
                plat.authorize();
            }
            break;
            case MSG_SHOW_USER: {
                int platform = msg.arg1;
                System.out.println("平台名字"+platform);
                String name = ShareSDK.platformIdToName(platform);
                System.out.println("平台名字"+name);
                Platform plat = ShareSDK.getPlatform(name);
//                plat.setPlatformActionListener(new RNPlatformListener(context));
                plat.SSOSetting(disableSSO);
                plat.showUser(null);
            }
            break;
            case MSG_SHARE: {
                int platformID = msg.arg1;
                String content = (String) msg.obj;
                String pName = ShareSDK.platformIdToName(platformID);
                Platform plat = ShareSDK.getPlatform(context, pName);
                plat.removeAccount(true);
//                plat.setPlatformActionListener(new RNPlatformListener(context));
                plat.SSOSetting(disableSSO);
                try {
                    Hashon hashon = new Hashon();
                    if (DEBUG) {
                        System.out.println("share content ==>>" + content);
                    }
                    HashMap<String, Object> data = hashon.fromJson(content);
                    ShareParams sp = new ShareParams(data);
                    //不同平台，分享不同内容
                    if (data.containsKey("customizeShareParams")) {
                        final HashMap<String, String> customizeSP = (HashMap<String, String>) data.get("customizeShareParams");
                        if (customizeSP.size() > 0) {
                            String pID = String.valueOf(platformID);
                            if (customizeSP.containsKey(pID)) {
                                String cSP = customizeSP.get(pID);
                                if (DEBUG) {
                                    System.out.println("share content ==>>" + cSP);
                                }
                                data = hashon.fromJson(cSP);
                                for (String key : data.keySet()) {
                                    sp.set(key, data.get(key));
                                }
                            }
                        }
                    }
                    plat.share(sp);
                } catch (Throwable t) {
//                    new RNPlatformListener(context).onError(plat, Platform.ACTION_SHARE, t);
                }
            }
            break;
            case MSG_ONEKEY_SAHRE: {
                int platform = msg.arg1;
                String content = (String) msg.obj;
                Hashon hashon = new Hashon();
                if (DEBUG) {
                    System.out.println("onekeyshare  ==>>" + content);
                }
                HashMap<String, Object> map = hashon.fromJson(content);
                OnekeyShare oks = new OnekeyShare();
                if (platform > 0) {
                    String name = ShareSDK.platformIdToName(platform);
                    if (DEBUG) {
                        System.out.println("ShareSDKUtils Onekeyshare shareView platform name ==>> " + name);
                    }
                    if(!TextUtils.isEmpty(name)){
                        oks.setPlatform(name);
                        oks.setSilent(false);
                    }
                }
                if (map.containsKey("text")) {
                    oks.setText((String)map.get("text"));
                }
                if (map.containsKey("imagePath")) {
                    oks.setImagePath((String)map.get("imagePath"));
                }
                if (map.containsKey("imageUrl")) {
                    oks.setImageUrl((String)map.get("imageUrl"));
                }
                if (map.containsKey("title")) {
                    oks.setTitle((String)map.get("title"));
                }
                if (map.containsKey("comment")) {
                    oks.setComment((String)map.get("comment"));
                }
                if (map.containsKey("url")) {
                    oks.setUrl((String)map.get("url"));
                    oks.setTitleUrl((String)map.get("url"));
                }
                if (map.containsKey("site")) {
                    oks.setSite((String)map.get("site"));
                }
                if (map.containsKey("siteUrl")) {
                    oks.setSiteUrl((String)map.get("siteUrl"));
                }
                if (map.containsKey("musicUrl")) {
                    oks.setSiteUrl((String)map.get("musicUrl"));
                }
                if (map.containsKey("shareType")) {
                    if ("6".equals(String.valueOf(map.get("shareType")))) {
                        if (map.containsKey("url")) {
                            oks.setVideoUrl((String)map.get("url"));
                        }
                    }
                }
                //不同平台，分享不同内容
                if (map.containsKey("customizeShareParams")) {
                    final HashMap<String, String> customizeSP = (HashMap<String, String>) map.get("customizeShareParams");
                    if (customizeSP.size() > 0) {
                        oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
                            public void onShare(Platform platform, ShareParams paramsToShare) {
                                String platformID = String.valueOf(ShareSDK.platformNameToId(platform.getName()));
                                if (customizeSP.containsKey(platformID)) {
                                    Hashon hashon = new Hashon();
                                    String content = customizeSP.get(platformID);
                                    if (DEBUG) {
                                        System.out.println("share content ==>>" + content);
                                    }
                                    HashMap<String, Object> data = hashon.fromJson(content);
                                    for (String key : data.keySet()) {
                                        paramsToShare.set(key, data.get(key));
                                    }
                                }
                            }
                        });
                    }
                }

                if(disableSSO){
                    oks.disableSSOWhenAuthorize();
                }
//                oks.setCallback(new RNPlatformListener(context));
                oks.show(context);
            }
            break;
            case MSG_GET_FRIENDLIST:{
                int platform = msg.arg1;
                int page = msg.getData().getInt("page");
                int count = msg.getData().getInt("count");
                String name = ShareSDK.platformIdToName(platform);
                Platform plat = ShareSDK.getPlatform(name);
//                plat.setPlatformActionListener(new RNPlatformListener(context));
                plat.SSOSetting(disableSSO);
                plat.listFriend(count, page, null);
            }
            break;
            case MSG_FOLLOW_FRIEND:{
                int platform = msg.arg1;
                String account = (String) msg.obj;
                String name = ShareSDK.platformIdToName(platform);
                Platform plat = ShareSDK.getPlatform(name);
//                plat.setPlatformActionListener(new RNPlatformListener(context));
                plat.SSOSetting(disableSSO);
                plat.followFriend(account);
            }
            break;
        }
        return false;
    }

}
