function myTrim(e) {
    return e.replace(/^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g, "")
}

function HTMLDeCode(e) {
    var t = "";
    return 0 == e.length ? "" : (t = e.replace(/&amp;/g, "&"), t = t.replace(/&lt;/g, "<"), t = t.replace(/&gt;/g, ">"), t = t.replace(/&nbsp;/g, " "), t = t.replace(/'/g, "'"), t = t.replace(/&quot;/g, '"'), t = t.replace(/<br>/g, "\n"))
}

function getUrl() {
    var Address, TypeID, SoftLinkID, downAdress = downAdressObj();
    if (Address = myTrim(downAdress.Address), TypeID = downAdress.TypeID, SoftLinkID = downAdress.TypeID, SoftID = downAdress.SoftID, Address = decodeURIComponent(Address), Address.indexOf("http:") >= 0 || Address.indexOf("ftp:") >= 0 || Address.indexOf("https:") >= 0) return document.write("<li><a href='" + Address + "' target='_blank' onmousedown='softCountNew(" + SoftID + ",0)'>直接点击下载 </a></li>"), !0;
    for (var sList = eval("AddressList.siteId_" + TypeID), DownLoadName = sList.split("||")[0], DownLoadURL = sList.split("||")[1], DownLoadNameList = DownLoadName.split(","), DownLoadURLList = DownLoadURL.split(","), DownTitle, DownAlt, DownURL, n = 0; n < DownLoadNameList.length; n++) DownURL = DownLoadURLList[n], DownURL += Address, DownLoadNameList[n].indexOf("#") >= 0 ? (DownTitle = DownLoadNameList[n].split("#")(0), DownAlt = DownLoadNameList[n].split("#")(1)) : (DownTitle = DownLoadNameList[n], DownAlt = DownLoadNameList[n]), document.write('<li class="address_like"><a href=' + DownURL + ' target="_blank" onmousedown="softCountNew(' + SoftID + "," + SoftLinkID + ')">' + HTMLDeCode(DownTitle) + "</a></li>")
}

function getUrl2() {
    var Address, TypeID, SoftLinkID;
    if (Address = myTrim(_downInfo.Address), TypeID = _downInfo.TypeID, SoftLinkID = _downInfo.SoftLinkID, SoftID = _downInfo.SoftID, "73" != TypeID) for (var sList = eval("AddressList.siteId_" + TypeID), DownLoadName = sList.split("||")[0], DownLoadURL = sList.split("||")[1], DownLoadNameList = DownLoadName.split(","), DownLoadURLList = DownLoadURL.split(","), DownTitle, DownAlt, DownURL, n = 0; n < DownLoadNameList.length; n++) {
        if (DownURL = DownLoadURLList[n], Address.indexOf("http:") >= 0 || Address.indexOf("ftp:") >= 0 || Address.indexOf("https:") >= 0 || Address.indexOf("thunder:") >= 0 ? DownURL = Address : DownURL += Address, DownLoadNameList[n].indexOf("#") >= 0 ? (DownTitle = DownLoadNameList[n].split("#")(0), DownAlt = DownLoadNameList[n].split("#")(1)) : (DownTitle = DownLoadNameList[n], DownAlt = DownLoadNameList[n]), document.getElementsByTagName("h1").length > 0) {
            var fname = document.getElementsByTagName("h1")[0].innerHTML;
            fname = fname.split(/(\s|\(|<)/)[0], fname = fname.substring(0, 20)
        } else fname = "setup";
        if (excludeTypeID = ",41,63,43,57,45,40,56,61,49,1,58,46,65,68,70,71,52,53,74,77,78,79,81,83,32,84,72,", 0 == n && excludeTypeID.indexOf("," + TypeID + ",") < 0) {
            var xid = 30;
            document.referrer.indexOf("so.com") >= 0 && (xid = 48);
            var miniUrl = "http://pcdown.dun.gsxzq.com/download/" + fname + "_" + xid + "@" + SoftID + ".exe";
            document.write("<h3 style='margin:5px 0 -5px 0'>需下载高速下载器:</h3><div id=\"gaosuxiazai\" style='height:75px;'><li class=\"address_like\"><a onmousedown='softCountOther(" + SoftID + "," + SoftLinkID + ")' href='" + miniUrl + "'>电信高速下载</a></li><li class=\"address_like\"><a onmousedown='softCountOther(" + SoftID + "," + SoftLinkID + ")' href='" + miniUrl + "'>联通高速下载</a></li><li class=\"address_like\"><a onmousedown='softCountOther(" + SoftID + "," + SoftLinkID + ")' href='" + miniUrl + "'>电信高速下载</a></li><li class=\"address_like\"><a onmousedown='softCountOther(" + SoftID + "," + SoftLinkID + ")' href='" + miniUrl + "'>联通高速下载</a></li></div><h3 style='margin:0px 0 -5px 0;border-top:1px solid #999;padding-top:5px;width:255px;'>普通下载地址:</h3>")
        }
        if (document.write('<li class="address_like"><a href="' + DownURL + '" onmousedown="softCountNew(' + SoftID + "," + SoftLinkID + ')">' + HTMLDeCode(DownTitle) + "</a></li>"), 5 == n) break
    }
}

function addDownList() {
    "undefined" == typeof _downInfo ? getUrl() : getUrl2();
    var e = document.getElementsByClassName("ul_Address");
    if (e.length > 0 && e[0].getElementsByTagName("li").length > 0) for (var t = document.getElementsByClassName("bdaddr"), n = 0; n < t.length; n++) t[n].style.display = "none"
}

function softCount(e, t) {
    (new Image).src = "http://tongji.pc6.com/statistics.php?rid=" + e + "&aid=" + t
}

function softCountOther(e, t) {
    (new Image).src = "http://tongji.pc6.com/statistics.php?rid=" + e + "&aid=" + t, softCountNew(e, 0)
}

function softCountNew(e, t) {
    var n, o, s, d;
    "undefined" != typeof detail ? (o = detail.cname, s = detail.sname, d = void 0 === detail.cid ? 99999 : detail.cid) : (n = $("#fast-nav").find("a"), o = n[n.length - 1], o = $(o).text(), null != o && void 0 != o && "" != o ? (s = $("#fast-nav").text().split("→"), null != (s = $.trim(s[s.length - 1]).split(" ")[0]) && void 0 != s && "" != s || (s = "未知资源名称")) : (o = "未知分类名称", s = "未知资源名称"), d = 99999), (new Image).src = "https://stat-api.20hn.cn/jf.gif?web_id=5&id=" + e + "&cate_id=" + d + "&cate=" + o + "&title=" + s + "&device=0&encode=gbk", e > 0 && t > 0 && softCount(e, t)
}

if (void 0 === AddressList) var AddressList = {};
var downAdressObj = function () {
    var e = document.getElementsByTagName("script")[document.getElementsByTagName("script").length - 1].src,
        t = e.split("?");
    if (alert(e), "string" == typeof t[1]) {
        t = t[1].split("&");
        var n = {};
        for (var o in t) {
            var s = t[o].split("=");
            n[s[0]] = s[1]
        }
        return n
    }
    return {}
};
"function" != typeof document.getElementsByClassName && (document.getElementsByClassName = function () {
    for (var e = [], t = new RegExp("(^| )" + arguments[0] + "( |$)"), n = document.body.getElementsByTagName("*"), o = 0, s = n.length; o < s; o++) t.test(n[o].className) && e.push(n[o]);
    return e
});