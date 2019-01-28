const ticket = 'LIKLckvwlJT9cWIhEQTwfFHvl7lwcIOco8um9YxpOomdt_KfsM-tuumw165ns93BqineN4PhuxvZI70UO_5fWg';

const timestamp = Date.now();
const nonceStr = Math.random().toString(16).substr(2);

const urlStr = location.href;
console.log(urlStr);

// var url = new URL(urlStr);
// var code = url.searchParams.get("code");
// console.log('code:' + code);

const originParams = 'jsapi_ticket=' + ticket
    + '&noncestr=' + nonceStr
    + '&timestamp=' + timestamp
    + '&url=' + urlStr;

var shaObj = new jsSHA("SHA-1", "TEXT");
shaObj.update(originParams);
var signature = shaObj.getHash("HEX");

wx.config({
    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    appId: 'wxbf061a2f1e3dd772', // 必填，公众号的唯一标识
    timestamp: timestamp, // 必填，生成签名的时间戳
    nonceStr: nonceStr, // 必填，生成签名的随机串16位字符，随便写
    signature: signature,// 必填，签名
    jsApiList: [
        'checkJsApi',
        'chooseImage',
        'getLocation',
        'openLocation',
        'scanQRCode'
    ] // 必填，需要使用的JS接口列表
});

var app = new Vue({
    el: '#app',
    data: {
        message: 'Hello Vue!'
    },
    methods: {
        handleCheckInOut() {
            console.log("checkWXclick click");
        },
        checkJsApi() {
            console.log('checkJsApi click');
            wx.checkJsApi({
                jsApiList: ['chooseImage'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
                success: function (res) {
                    console.log(res);
                }, fail: function (error) {
                    console.error(error);
                }
            });
        },
        chooseImage() {
            console.log('chooseImage click');
            wx.chooseImage({
                count: 2, // 默认9
                sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
                sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
                success: function (res) {
                    console.log(res);
                }, fail: function (err) {
                    console.error(err);
                }
            });
        },
        getLocation() {
            console.log('getLocation click');
            wx.getLocation({
                type: 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
                success: function (res) {
                    console.log(res);
                    wx.openLocation({
                        latitude: res.latitude, // 纬度，浮点数，范围为90 ~ -90
                        longitude: res.longitude, // 经度，浮点数，范围为180 ~ -180。
                        name: '', // 位置名
                        address: '', // 地址详情说明
                        scale: 15, // 地图缩放级别,整形值,范围从1~28。默认为最大
                        infoUrl: '', // 在查看位置界面底部显示的超链接,可点击跳转
                        success: function (res) {
                            console.log(res);
                        },
                        fail: function (err) {
                            console.error(err);
                        }
                    });
                }, fail: function (err) {
                    console.error(err);
                }
            });
        },
        openLocation() {
            console.log('openLocation click');
            wx.openLocation({
                latitude: 0, // 纬度，浮点数，范围为90 ~ -90
                longitude: 0, // 经度，浮点数，范围为180 ~ -180。
                name: '', // 位置名
                address: '', // 地址详情说明
                scale: 1, // 地图缩放级别,整形值,范围从1~28。默认为最大
                infoUrl: '', // 在查看位置界面底部显示的超链接,可点击跳转
                success: function (res) {
                    console.log(res);
                },
                fail: function (err) {
                    console.error(err);
                }
            });
        },
        scanQRCode() {
            console.log('scanQRCode click');
            wx.scanQRCode({
                needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
                scanType: ["qrCode", "barCode"], // 可以指定扫二维码还是一维码，默认二者都有
                success: function (res) {
                    console.log(res);
                }, fail: function (err) {
                    console.error(err);
                }
            });
        },
    }
});