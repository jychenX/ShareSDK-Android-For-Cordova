cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
    {
        "id": "com.sharesdk.cordova.share.ShareSDK",
        "file": "plugins/com.sharesdk.cordova.share/www/ShareSDK.js",
        "pluginId": "com.sharesdk.cordova.share",
        "clobbers": [
            "ShareSDK"
        ]
    },
    {
        "id": "cordova-hot-code-push-plugin.chcp",
        "file": "plugins/cordova-hot-code-push-plugin/www/chcp.js",
        "pluginId": "cordova-hot-code-push-plugin",
        "clobbers": [
            "chcp"
        ]
    }
];
module.exports.metadata = 
// TOP OF METADATA
{
    "com.sharesdk.cordova.share": "0.0.1",
    "cordova-hot-code-push-plugin": "1.5.3"
};
// BOTTOM OF METADATA
});