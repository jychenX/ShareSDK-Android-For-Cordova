cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
    {
        "id": "com.sharesdk.cordova.share.ShareSDK",
        "file": "plugins/com.sharesdk.cordova.share/www/ShareSDK.js",
        "pluginId": "com.sharesdk.cordova.share",
        "clobbers": [
            "ShareSDK"
        ]
    }
];
module.exports.metadata = 
// TOP OF METADATA
{
    "com.sharesdk.cordova.share": "0.0.1"
};
// BOTTOM OF METADATA
});