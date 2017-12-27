cordova.define("com.sharesdk.cordova.share.ShareSDK", function(require, exports, module) {});

function ShareSDK()
{
    var MethodAction =
    {
        ACTION_AUTHORIZING: 1,
        ACTION_GETTING_FRIEND_LIST : 2,
        ACTION_FOLLOWING_USER : 6,
        ACTION_USER_INFOR : 8,
        ACTION_SHARE : 9
    };


    /**
     * 平台类型
     * @type {object}
     */
    this.PlatformID = {
        Unknown : 0,
        SinaWeibo : 1,			//Sina Weibo
        TencentWeibo : 2,		//Tencent Weibo
        DouBan : 5,				//Dou Ban
        QZone : 6, 				//QZone
        Renren : 7,				//Ren Ren
        Kaixin : 8,				//Kai Xin
        Pengyou : 9,			//Friends
        Facebook : 10,			//Facebook
        Twitter : 11,			//Twitter
        Evernote : 12,			//Evernote
        Foursquare : 13,		//Foursquare
        GooglePlus : 14,		//Google+
        Instagram : 15,			//Instagram
        LinkedIn : 16,			//LinkedIn
        Tumblr : 17,			//Tumblr
        Mail : 18, 				//Mail
        SMS : 19,				//SMS
        Print : 20, 			//Print
        Copy : 21,				//Copy
        WeChat : 22,		    //WeChat Friends
        WeChatMoments : 23,	    //WeChat Timeline
        QQ : 24,				//QQ
        Instapaper : 25,		//Instapaper
        Pocket : 26,			//Pocket
        YouDaoNote : 27, 		//You Dao Note
        Pinterest : 30, 		//Pinterest
        Flickr : 34,			//Flickr
        Dropbox : 35,			//Dropbox
        VKontakte : 36,			//VKontakte
        WeChatFavorites : 37,	//WeChat Favorited
        YiXinSession : 38, 		//YiXin Session
        YiXinTimeline : 39,		//YiXin Timeline
        YiXinFav : 40,			//YiXin Favorited
        MingDao : 41,          	//明道
        Line : 42,             	//Line
        WhatsApp : 43,         	//Whats App
        KakaoTalk : 44,         //KakaoTalk
        KakaoStory : 45,        //KakaoStory
        FacebookMessenger : 46, //FacebookMessenger
        Bluetooth : 48,         //Bluetooth
        Alipay : 50,            //Alipay
        KakaoPlatform : 995,    //Kakao Series
        EvernotePlatform : 996, //Evernote Series
        WechatPlatform : 997,   //Wechat Series
        QQPlatform : 998,		//QQ Series
        Any : 999 				//Any Platform
    };

    /**
     * 回复状态
     * @type {object}
     */
    this.ResponseState = {
        Begin : 0,              //开始
        Success: 1,             //成功
        Fail : 2,               //失败
        Cancel :3               //取消
    };

    /**
     * 内容分享类型
     * @type {object}
     */
    this.ContentType = {
        Auto : 0,
        Text : 1,
        Image : 2,
        WebPage : 4,
        Music : 5,
        Video : 6,
        App : 7,
        File : 8,
        Emoji : 9
    };

    this.initSDKAndSetPlatfromConfig = function (appKey, platformConfig)
    {
    };

    /**
     * 用户授权
     * @param platform          平台类型
     * @param callback          回调方法
     */
    this.authorize = function (platform)
    {
        alert("authorize11");
        cordova.exec(callSuccess,callError, "ShareSDKUtils", "authorize", [platform]);
    };

    /**
     * 获取用户信息
     * @param platform          平台类型
     * @param callback          回调方法
     */
    this.getUserInfo = function (platform)
    {
        alert("ShowUser");
        cordova.exec(callSuccess,callError, "ShareSDKUtils", "ShowUser", [platform]);
    };

    /**
     * 获取授权信息
     * @param platform          平台类型
     * @param callback          回调方法
     */
    this.getAuthInfo = function (platform)
    {
        alert("getAuthInfo");
        cordova.exec(function(succ){
            alert('succ:'+succ);
            var info = "用户信息：" +
                "\n expiresIn:"+succ['expiresIn']+
                "\n expiresTime:"+succ['expiresTime']+
                "\n token:"+succ['token']+
                "\n tokenSecret:"+succ['tokenSecret']+
                "\n userGender:"+succ['userGender']+
                "\n userID:"+succ['userID']+
                "\n openID:"+succ['openID']+
                "\n userName:"+succ['userName']+
                "\n userIcon:"+succ['userIcon'];
            alert(info);

        },function(err){
            alert('fail:'+err);
        }, "ShareSDKUtils", "getAuthInfo", [platform]);
    };

    /**
     * 取消用户授权
     * @param platform          平台类型
     */
    this.cancelAuthorize = function (platform)
    {
        alert("cancelAuthorize");
        cordova.exec(callSuccess,callError, "ShareSDKUtils", "removeAccount", [platform]);
    };

    /**
     * 是否授权
     * @param platform          平台类型
     * @param callback          回调方法
     *
     */
    this.isAuthorizedValid = function (platform)
    {
        alert("isAuthValid");
        cordova.exec(callSuccess,callError, "ShareSDKUtils", "isAuthValid", [platform]);
    };

    /**
     * 客户端是否可用
     * @param platform          平台类型
     * @param callback          回调方法
     *
     */
    this.isClientValid = function (platform)
    {
        alert("isClientValid");
        cordova.exec(callSuccess,callError, "ShareSDKUtils", "isClientValid", [platform]);
    };


    /**
     * 分享内容
     * @param platform          平台类型
     * @param shareParams       分享内容
     * @param callback          回调方法
     */
    this.shareContent = function (platform, shareParams)
    {
        alert("shareContent");
        cordova.exec(callSuccess,callError, "ShareSDKUtils", "shareContent", [platform, shareParams]);
    };


    /**
     * 显示分享菜单
     * @param platforms         分享的目标平台类型集合
     * @param shareParams       分享内容
     * @param x                 弹出菜单的原点横坐标（仅用于iPad）
     * @param y                 弹出菜单的原点纵坐标（仅用于iPad）
     * @param callback          回调方法
     */
    this.showShareMenu = function (platforms, shareParams)
    {
        alert("showShareMenu");
        cordova.exec(callSuccess,callError, "ShareSDKUtils", "onekeyShare", [platforms, shareParams]);
    };


    /**
     * 获取朋友列表
     * @param platform
     * @param page
     * @param count
     * @param account
     * @param callback
     */
    this.getFriendList = function (platform, count, page)
    {
        alert("getFriendList");
        cordova.exec(callSuccess,callError, "ShareSDKUtils", "getFriendList", [platform, count, page]);
    };

    /**
     * 关注好友
     * @param platform
     * @param friendName
     * @param callback
     */
    this.addFriend = function(platform, friendName){
        alert("followFriend");
        cordova.exec(callSuccess,callError, "ShareSDKUtils", "followFriend", [platform, friendName]);
    };

    /**
     * 设置平台配置（这个接口仅对Android有效果）
     * @param platform          平台类型
     * @param config            配置信息
     */
    this.closeSSOWhenAuthorize = function (disableSSO)
    {
        alert("disableSSOWhenAuthorize");
        cordova.exec(callSuccess,callError, "ShareSDKUtils", "disableSSOWhenAuthorize", [disableSSO]);
    };

    /**
     * 接收java层的回调
     *  state  状态信息（成功，失败，取消）
     *  plat   平台ID（可以根据PlatformID函数判断社交平台类型）
     *  action 执行动作（可以根据MethodAction函数获取当做是执行登陆，还是分享还是其他的）
     *  data   返回的数据（分享和授权返回为空）
     *  error  返回的异常信息
     */
    function callSuccess(succ){
        alert("callSuccess"+succ);
        var state = succ['state'];
        var plat = succ['platformId'];
        var action = succ['action'];
        var data = succ['data'];
        var error = succ['throwable'];
        if(state == 1){
            OnComplete(plat, action, data);
        }else if(state == 0){
            OnError(plat, action, error);
        }else{
            OnCancel(plat, action);
        }
    };
    function callError(fail) {
        alert("调用方法异常"+fail);
    };


    //
    function OnComplete(platform, action, dataMap){
        alert("平台ID："+platform+
                "\naction："+action+
                "\n资料信息："+dataMap);
    };
    function OnError(platform, action, error){
        alert("平台ID："+platform+
            "\naction："+action+
            "\n异常信息："+error);
    };
    function OnCancel(platform, action){
        alert("平台ID："+platform+
            "\naction："+action);
    }
};

var  sharesdk = new ShareSDK();
// module.exports = sharesdk;