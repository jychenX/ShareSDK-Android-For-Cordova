cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
    {
        "file": "plugins/com.sharesdk.cordova.share/www/ShareSDK.js",
        "id": "com.sharesdk.cordova.share.ShareSDK",
        "pluginId": "com.sharesdk.cordova.share",
        "clobbers": [
            "ShareSDK"
        ]
    },
    {
        "file": "plugins/cordova-hot-code-push-plugin/www/chcp.js",
        "id": "cordova-hot-code-push-plugin.chcp",
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
}
// BOTTOM OF METADATA
});