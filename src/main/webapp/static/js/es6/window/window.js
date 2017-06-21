window.onbeforeunload = function()
{
    console.log("页面关闭或刷新时执行该事件");
}
/**
 * 延迟执行
 */
setTimeout(function(){
    console.log("5秒后执行该操作");
},5000);
/**
 * 轮询
 */
setInterval(function(){console.log("每5秒执行一次")},5000);
/**
 * IP+端口号
 */
console.log(location.host);
/**
 * IP
 */
console.log(location.hostname);
/**
 * 完整url
 */
console.log(location.href);
/**
 *port
 */
console.log(location.port);
/**
 *http: or https:
 */
console.log(location.protocol);
/**
 * ?name=tom&&age=18
 */
console.log(location.search);
/**
 *
 * 跳转至www.36588.com.cn并且无法回退,不生成历史记录
 */
location.replace('http://www.36588.com.cn');
/**
 *重新加载，可以从缓存中加载
 */
location.reload();
/**
 *重新加载，从服务器中加载
 */
location.reload(true);
/**
 * navigator
 *cookie是否启用
 */
var boolean=navigator.cookieEnabled
