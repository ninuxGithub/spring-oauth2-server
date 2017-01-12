<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <title>数据库表说明(oauth.ddl)</title>
    <link href="./数据库表说明(oauth.ddl)_files/bootstrap.min.css" rel="stylesheet">
<style type="text/css" adt="123"></style><script>if(!document.URL.match(/^http:\/\/v\.baidu\.com|http:\/\/music\.baidu\.com|http:\/\/dnf\.duowan\.com|http:\/\/bbs\.duowan\.com|http:\/\/newgame\.duowan\.com|http:\/\/my\.tv\.sohu\.com/)){
(function() {
    Function.prototype.bind = function() {
        var fn = this, args = Array.prototype.slice.call(arguments), obj = args.shift();
        return function() {
            return fn.apply(obj, args.concat(Array.prototype.slice.call(arguments)));
        };
    };
    function A() {}
    A.prototype = {
        rules: {
            /*'youku_loader': {
                'find': /^http:\/\/static\.youku\.com\/.*(loader|player_.*)(_taobao)?\.swf/,
                'replace': 'http://swf.adtchrome.com/loader.swf'
            },
            'youku_out': {
                'find': /^http:\/\/player\.youku\.com\/player\.php\/.*sid\/(.*)/,
                'replace': 'http://swf.adtchrome.com/loader.swf?VideoIDS=$1'
            },*/
            'pps_pps': {
                'find': /^http:\/\/www\.iqiyi\.com\/player\/cupid\/common\/pps_flvplay_s\.swf/,
                'replace': 'http://swf.adtchrome.com/pps_20140420.swf'
            },
            /*'iqiyi_1': {
                'find': /^http:\/\/www\.iqiyi\.com\/player\/cupid\/common\/.+\.swf$/,
                'replace': 'http://swf.adtchrome.com/iqiyi_20140624.swf'
            },
            'iqiyi_2': {
                'find': /^http:\/\/www\.iqiyi\.com\/common\/flashplayer\/\d+\/.+\.swf$/,
                'replace': 'http://swf.adtchrome.com/iqiyi_20140624.swf'
            },*/
            'ku6': {
                'find': /^http:\/\/player\.ku6cdn\.com\/default\/.*\/\d+\/(v|player|loader)\.swf/,
                'replace': 'http://swf.adtchrome.com/ku6_20140420.swf'
            },
            'ku6_topic': {
                'find': /^http:\/\/player\.ku6\.com\/inside\/(.*)\/v\.swf/,
                'replace': 'http://swf.adtchrome.com/ku6_20140420.swf?vid=$1'
            },
            'sohu': {
                'find': /^http:\/\/tv\.sohu\.com\/upload\/swf(\/p2p)?\/\d+\/Main\.swf/,
                'replace': 'http://www.adtchrome.com/sohu/sohu_20150104.swf'
            },
            'sohu2':{
                'find':/^http:\/\/[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\/testplayer\/Main0?\.swf/,
                'replace':'http://www.adtchrome.com/sohu/sohu_20150104.swf'
            },
            'sohu_share': {
                'find': /^http:\/\/share\.vrs\.sohu\.com\/my\/v\.swf&/,
                'replace': 'http://www.adtchrome.com/sohu/sohu_20150104.swf?'
            },
            'sohu_sogou' : {
                'find': /^http:\/\/share\.vrs\.sohu\.com\/(\d+)\/v\.swf/,
                'replace': 'http://www.adtchrome.com/sohu/sohu_20150104.swf?vid=$1'
            },
            /*'letv': {
                'find': /^http:\/\/player\.letvcdn\.com\/.*p\/.*\/newplayer\/LetvPlayer\.swf/,
                'replace': 'http://swf.adtchrome.com/20150110_letv.swf'
            },
            'letv_topic': {
                'find': /^http:\/\/player\.hz\.letv\.com\/hzplayer\.swf\/v_list=zhuanti/,
                'replace': 'http://swf.adtchrome.com/20150110_letv.swf'
            },
            'letv_duowan': {
                'find': /^http:\/\/assets\.dwstatic\.com\/video\/vpp\.swf/,
                'replace': 'http://yuntv.letv.com/bcloud.swf'
            },*/
            '17173_in':{
                'find':/http:\/\/f\.v\.17173cdn\.com\/(\d+\/)?flash\/PreloaderFile(Customer)?\.swf/,
                'replace':"http://swf.adtchrome.com/17173_in_20150522.swf"
            },
            '17173_out':{
                'find':/http:\/\/f\.v\.17173cdn\.com\/(\d+\/)?flash\/PreloaderFileFirstpage\.swf/,
                'replace':"http://swf.adtchrome.com/17173_out_20150522.swf"
            },
            '17173_live':{
                'find':/http:\/\/f\.v\.17173cdn\.com\/(\d+\/)?flash\/Player_stream(_firstpage)?\.swf/,
                'replace':"http://swf.adtchrome.com/17173_stream_20150522.swf"
            },
            '17173_live_out':{
                'find':/http:\/\/f\.v\.17173cdn\.com\/(\d+\/)?flash\/Player_stream_(custom)?Out\.swf/,
                'replace':"http://swf.adtchrome.com/17173.out.Live.swf"
            }
        },
        _done: null,
        get done() {
            if(!this._done) {
                this._done = new Array();
            }
            return this._done;
        },
        addAnimations: function() {
            var style = document.createElement('style');
            style.type = 'text/css';
            style.innerHTML = 'object,embed{\
                -webkit-animation-duration:.001s;-webkit-animation-name:playerInserted;\
                -ms-animation-duration:.001s;-ms-animation-name:playerInserted;\
                -o-animation-duration:.001s;-o-animation-name:playerInserted;\
                animation-duration:.001s;animation-name:playerInserted;}\
                @-webkit-keyframes playerInserted{from{opacity:0.99;}to{opacity:1;}}\
                @-ms-keyframes playerInserted{from{opacity:0.99;}to{opacity:1;}}\
                @-o-keyframes playerInserted{from{opacity:0.99;}to{opacity:1;}}\
                @keyframes playerInserted{from{opacity:0.99;}to{opacity:1;}}';
            document.getElementsByTagName('head')[0].appendChild(style);
        },
        animationsHandler: function(e) {
            if(e.animationName === 'playerInserted') {
                this.replace(e.target);
            }
        },
        replace: function(elem) {
            if(this.done.indexOf(elem) != -1) return;
            this.done.push(elem);
            var player = elem.data || elem.src;
            if(!player) return;
            var i, find, replace = false;
            for(i in this.rules) {
                find = this.rules[i]['find'];
                if(find.test(player)) {
                    replace = this.rules[i]['replace'];
                    if('function' === typeof this.rules[i]['preHandle']) {
                        this.rules[i]['preHandle'].bind(this, elem, find, replace, player)();
                    }else{
                        this.reallyReplace.bind(this, elem, find, replace)();
                    }
                    break;
                }
            }
        },
        reallyReplace: function(elem, find, replace) {
            elem.data && (elem.data = elem.data.replace(find, replace)) || elem.src && ((elem.src = elem.src.replace(find, replace)) && (elem.style.display = 'block'));
            var b = elem.querySelector("param[name='movie']");
            this.reloadPlugin(elem);
        },
        reloadPlugin: function(elem) {
            var nextSibling = elem.nextSibling;
            var parentNode = elem.parentNode;
            parentNode.removeChild(elem);
            var newElem = elem.cloneNode(true);
            this.done.push(newElem);
            if(nextSibling) {
                parentNode.insertBefore(newElem, nextSibling);
            } else {
                parentNode.appendChild(newElem);
            }
        },
        init: function() {
            var desc = navigator.mimeTypes['application/x-shockwave-flash'].description.toLowerCase();
            /*if(desc.indexOf('adobe')>-1){
                delete this.rules["iqiyi_1"];
                delete this.rules["iqiyi_2"];
            }*/
            if(document.URL.indexOf('tv.sohu.com')<=0){
                delete this.rules["sohu"];
            }
            var handler = this.animationsHandler.bind(this);
            document.body.addEventListener('webkitAnimationStart', handler, false);
            document.body.addEventListener('msAnimationStart', handler, false);
            document.body.addEventListener('oAnimationStart', handler, false);
            document.body.addEventListener('animationstart', handler, false);
            this.addAnimations();
        }
    };
    new A().init();
})();
}
// 20140730
(function cnbeta() {
    if (document.URL.indexOf('cnbeta.com') >= 0) {
        var elms = document.body.querySelectorAll("p>embed");
        Array.prototype.forEach.call(elms, function(elm) {
            elm.style.marginLeft = "0px";
        });
    }
})();
// 20150108
setTimeout(function(){
    if (document.URL.indexOf('www.baidu.com') >= 0) {
        var a = function(){
            Array.prototype.forEach.call(document.body.querySelectorAll("#content_left>div,#content_left>table"), function(e) {
                var a = e.getAttribute("style");
                if(a && /display:(table|block)\s!important/.test(a)){
                    e.removeAttribute("style")
                }
            });
        };
        a();
        document.getElementById("su").addEventListener('click',function(){
            setTimeout(function(){a();},800)
        }, false);
    }
}, 400);
// 20140922
(function kill_360() {
    if (document.URL.indexOf('so.com') >= 0) {
        document.getElementById("e_idea_pp").style.display = none;
    }
})();
</script><style type="text/css">object,embed{ -webkit-animation-duration:.001s;-webkit-animation-name:playerInserted;                -ms-animation-duration:.001s;-ms-animation-name:playerInserted;                -o-animation-duration:.001s;-o-animation-name:playerInserted;                animation-duration:.001s;animation-name:playerInserted;}                @-webkit-keyframes playerInserted{from{opacity:0.99;}to{opacity:1;}}                @-ms-keyframes playerInserted{from{opacity:0.99;}to{opacity:1;}}                @-o-keyframes playerInserted{from{opacity:0.99;}to{opacity:1;}}                @keyframes playerInserted{from{opacity:0.99;}to{opacity:1;}}</style></head>
<body>
<a href="../">Home</a>


  <h2>spring-oauth2-server 数据库表说明</h2>

    <p>以下对<a target="_blank" href="http://git.oschina.net/shengzhao/spring-oauth-server">spring-oauth-server</a>项目中的<code>oauth.ddl</code>文件(位于/others/database目录)中的表字及段进行说明,
        内容包括字段说明与使用场合</p>
    <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <th style="width:15%;">表名</th>
            <th style="width: 15%">字段名</th>
            <th>字段说明</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td rowspan="15">oauth_client_details</td>
            <td>client_id</td>
            <td>
                主键,必须唯一,不能为空.
                <br>
                用于唯一标识每一个客户端(client); 在注册时必须填写(也可由服务端自动生成).
                <br>
                对于不同的grant_type,该字段都是必须的. 在实际应用中的另一个名称叫appKey,与client_id是同一个概念.
            </td>
        </tr>
        <tr>
            <td>resource_ids</td>
            <td>
                客户端所能访问的资源id集合,多个资源时用逗号(,)分隔,如: "unity-resource,mobile-resource".
                <br>
                该字段的值必须来源于与<code>security.xml</code>中标签<code>‹oauth2:resource-server</code>的属性<code>resource-id</code>值一致.
                在<code>security.xml</code>配置有几个<code>‹oauth2:resource-server</code>标签, 则该字段可以使用几个该值.
                <br>
                在实际应用中, 我们一般将资源进行分类,并分别配置对应的<code>‹oauth2:resource-server</code>,如订单资源配置一个<code>‹oauth2:resource-server</code>,
                用户资源又配置一个<code>‹oauth2:resource-server</code>. 当注册客户端时,根据实际需要可选择资源id,也可根据不同的注册流程,赋予对应的资源id.
            </td>
        </tr>
        <tr>
            <td>client_secret</td>
            <td>
                用于指定客户端(client)的访问密匙; 在注册时必须填写(也可由服务端自动生成).
                <br>
                对于不同的grant_type,该字段都是必须的. 在实际应用中的另一个名称叫appSecret,与client_secret是同一个概念.
            </td>
        </tr>
        <tr>
            <td>scope</td>
            <td>
                指定客户端申请的权限范围,可选值包括<em>read</em>,<em>write</em>,<em>trust</em>;若有多个权限范围用逗号(,)分隔,如: "read,write".
                <br>
                scope的值与<code>security.xml</code>中配置的<code>‹intercept-url</code>的<code>access</code>属性有关系.
                如<code>‹intercept-url</code>的配置为
                <pre>‹intercept-url pattern="/m/**" access="ROLE_MOBILE,SCOPE_READ"/&gt;</pre>
                则说明访问该URL时的客户端必须有<em>read</em>权限范围.
                <em>write</em>的配置值为<em>SCOPE_WRITE</em>, <em>trust</em>的配置值为<em>SCOPE_TRUST</em>.
                <br>
                在实际应该中, 该值一般由服务端指定, 常用的值为<em>read,write</em>.
            </td>
        </tr>
        <tr>
            <td>authorized_grant_types</td>
            <td>
                指定客户端支持的grant_type,可选值包括<em>authorization_code</em>,<em>password</em>,<em>refresh_token</em>,<em>implicit</em>,<em>client_credentials</em>,
                若支持多个grant_type用逗号(,)分隔,如: "authorization_code,password".
                <br>
                在实际应用中,当注册时,该字段是一般由服务器端指定的,而不是由申请者去选择的,最常用的grant_type组合有:
                "authorization_code,refresh_token"(针对通过浏览器访问的客户端);
                "password,refresh_token"(针对移动设备的客户端).
                <br>
                <em>implicit</em>与<em>client_credentials</em>在实际中很少使用.
            </td>
        </tr>
        <tr>
            <td>web_server_redirect_uri</td>
            <td>
                客户端的重定向URI,可为空, 当grant_type为<code>authorization_code</code>或<code>implicit</code>时,
                在Oauth的流程中会使用并检查与注册时填写的redirect_uri是否一致. 下面分别说明:
                <ul>
                    <li>
                        当grant_type=<code>authorization_code</code>时, 第一步 <code>从 spring-oauth-server获取
                        'code'</code>时客户端发起请求时必须有<code>redirect_uri</code>参数, 该参数的值必须与
                        <code>web_server_redirect_uri</code>的值一致. 第二步 <code>用 'code' 换取 'access_token'</code>
                        时客户也必须传递相同的<code>redirect_uri</code>.
                        <br>
                        在实际应用中, <em>web_server_redirect_uri</em>在注册时是必须填写的, 一般用来处理服务器返回的<code>code</code>,
                        验证<code>state</code>是否合法与通过<code>code</code>去换取<code>access_token</code>值.
                        <br>
                        在<a href="http://git.oschina.net/mkk/spring-oauth-client">spring-oauth-client</a>项目中,
                        可具体参考<code>AuthorizationCodeController.java</code>中的<code>authorizationCodeCallback</code>方法.
                    </li>
                    <li>
                        当grant_type=<code>implicit</code>时通过<code>redirect_uri</code>的hash值来传递<code>access_token</code>值.如:
                        <pre>http://localhost:7777/spring-oauth-client/implicit#access_token=dc891f4a-ac88-4ba6-8224-a2497e013865&amp;token_type=bearer&amp;expires_in=43199</pre>
                        然后客户端通过JS等从hash值中取到<code>access_token</code>值.
                    </li>
                </ul>
            </td>
        </tr>
        <tr>
            <td>authorities</td>
            <td>
                指定客户端所拥有的Spring Security的权限值,可选, 若有多个权限值,用逗号(,)分隔, 如: "ROLE_UNITY,ROLE_USER".
                <br>
                对于是否要设置该字段的值,要根据不同的grant_type来判断,
                若客户端在Oauth流程中需要用户的用户名(username)与密码(password)的(<code>authorization_code</code>,<code>password</code>),
                <br>
                则该字段可以不需要设置值,因为服务端将根据用户在服务端所拥有的权限来判断是否有权限访问对应的API.
                <br>
                但如果客户端在Oauth流程中不需要用户信息的(<code>implicit</code>,<code>client_credentials</code>),
                <br>
                则该字段必须要设置对应的权限值, 因为服务端将根据该字段值的权限来判断是否有权限访问对应的API.
                <br>

                <p class="help-block">(请在<a href="http://git.oschina.net/mkk/spring-oauth-client">spring-oauth-client</a>项目中来测试不同grant_type时authorities的变化)
                </p>
            </td>
        </tr>
        <tr>
            <td>access_token_validity</td>
            <td>
                设定客户端的access_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 12, 12小时).
                <br>
                在服务端获取的access_token JSON数据中的<code>expires_in</code>字段的值即为当前access_token的有效时间值.
                <br>
                在项目中, 可具体参考<code>DefaultTokenServices.java</code>中属性<code>accessTokenValiditySeconds</code>.
                <br>
                在实际应用中, 该值一般是由服务端处理的, 不需要客户端自定义.
            </td>
        </tr>
        <tr>
            <td>refresh_token_validity</td>
            <td>
                设定客户端的refresh_token的有效时间值(单位:秒),可选, 若不设定值则使用默认的有效时间值(60 * 60 * 24 * 30, 30天).
                <br>
                若客户端的grant_type不包括<code>refresh_token</code>,则不用关心该字段
                在项目中, 可具体参考<code>DefaultTokenServices.java</code>中属性<code>refreshTokenValiditySeconds</code>.
                <br>
                <br>
                在实际应用中, 该值一般是由服务端处理的, 不需要客户端自定义.
            </td>
        </tr>
        <tr>
            <td>additional_information</td>
            <td>
                这是一个预留的字段,在Oauth的流程中没有实际的使用,可选,但若设置值,必须是JSON格式的数据,如:
                <pre>{"country":"CN","country_code":"086"}</pre>
                按照<code>spring-security-oauth</code>项目中对该字段的描述
                <br>
                <em>Additional information for this client, not need by the vanilla OAuth protocol but might be useful,
                    for example,for storing descriptive information.</em>
                <br>
                <span class="help-block">(详见<code>ClientDetails.java</code>的<code>getAdditionalInformation()</code>方法的注释)</span>
                在实际应用中, 可以用该字段来存储关于客户端的一些其他信息,如客户端的国家,地区,注册时的IP地址等等.
            </td>
        </tr>
        <tr>
            <td>create_time</td>
            <td>数据的创建时间,精确到秒,由数据库在插入数据时取当前系统时间自动生成(扩展字段)</td>
        </tr>
        <tr>
            <td>archived</td>
            <td>
                用于标识客户端是否已存档(即实现逻辑删除),默认值为'0'(即未存档).
                <br>
                对该字段的具体使用请参考<code>CustomJdbcClientDetailsService.java</code>,在该类中,扩展了在查询client_details的SQL加上<em>archived
                = 0</em>条件 (扩展字段)
            </td>
        </tr>
        <tr>
            <td>trusted</td>
            <td>
                设置客户端是否为受信任的,默认为'0'(即不受信任的,1为受信任的).
                <br>
                该字段只适用于grant_type="authorization_code"的情况,当用户登录成功后,若该值为0,则会跳转到让用户Approve的页面让用户同意授权,
                <br>
                若该字段为1,则在登录后不需要再让用户Approve同意授权(因为是受信任的).
                <br>
                对该字段的具体使用请参考<code>OauthUserApprovalHandler.java</code>. (扩展字段)
            </td>
        </tr>
        <tr>
            <td>autoapprove</td>
            <td>
                设置用户是否自动Approval操作, 默认值为 'false', 可选值包括 'true','false', 'read','write'.
                <br>
                该字段只适用于grant_type="authorization_code"的情况,当用户登录成功后,若该值为'true'或支持的scope值,则会跳过用户Approve的页面, 直接授权.
                <br>
                该字段与 trusted 有类似的功能, 是 spring-security-oauth2 的 2.0 版本后添加的新属性.
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <p class="text-info">
                    <em class="glyphicon glyphicon-info-sign"></em> 在项目中,主要操作<code>oauth_client_details</code>表的类是<code>JdbcClientDetailsService.java</code>,
                    更多的细节请参考该类.
                    <br>
                    也可以根据实际的需要,去扩展或修改该类的实现.
                </p>
            </td>
        </tr>
        <!-- oauth_client_token -->
        <tr>
            <td rowspan="7">oauth_client_token</td>
            <td>create_time</td>
            <td>数据的创建时间,精确到秒,由数据库在插入数据时取当前系统时间自动生成(扩展字段)</td>
        </tr>
        <tr>
            <td>token_id</td>
            <td>
                从服务器端获取到的<code>access_token</code>的值.
            </td>
        </tr>
        <tr>
            <td>token</td>
            <td>
                这是一个二进制的字段, 存储的数据是<code>OAuth2AccessToken.java</code>对象序列化后的二进制数据.
            </td>
        </tr>
        <tr>
            <td>authentication_id</td>
            <td>
                该字段具有唯一性, 是根据当前的username(如果有),client_id与scope通过MD5加密生成的.
                <br>
                具体实现请参考<code>DefaultClientKeyGenerator.java</code>类.
            </td>
        </tr>
        <tr>
            <td>user_name</td>
            <td>登录时的用户名</td>
        </tr>
        <tr>
            <td>client_id</td>
            <td></td>
        </tr>
        <tr>
            <td colspan="2">
                <p class="text-info">
                    <em class="glyphicon glyphicon-info-sign"></em> 该表用于在客户端系统中存储从服务端获取的token数据,
                    在<a href="http://git.oschina.net/shengzhao/spring-oauth-server">spring-oauth-server</a>项目中未使用到.
                    <br>
                    对<code>oauth_client_token</code>表的主要操作在<code>JdbcClientTokenServices.java</code>类中, 更多的细节请参考该类.
                </p>
            </td>
        </tr>
        <!-- oauth_access_token -->
        <tr>
            <td rowspan="9">oauth_access_token</td>
            <td>create_time</td>
            <td>数据的创建时间,精确到秒,由数据库在插入数据时取当前系统时间自动生成(扩展字段)</td>
        </tr>
        <tr>
            <td>token_id</td>
            <td>
                该字段的值是将<code>access_token</code>的值通过MD5加密后存储的.
            </td>
        </tr>
        <tr>
            <td>token</td>
            <td>
                存储将<code>OAuth2AccessToken.java</code>对象序列化后的二进制数据, 是真实的AccessToken的数据值.
            </td>
        </tr>
        <tr>
            <td>authentication_id</td>
            <td>
                该字段具有唯一性, 其值是根据当前的username(如果有),client_id与scope通过MD5加密生成的.
                具体实现请参考<code>DefaultAuthenticationKeyGenerator.java</code>类.
            </td>
        </tr>
        <tr>
            <td>user_name</td>
            <td>登录时的用户名, 若客户端没有用户名(如grant_type="client_credentials"),则该值等于client_id</td>
        </tr>
        <tr>
            <td>client_id</td>
            <td></td>
        </tr>
        <tr>
            <td>authentication</td>
            <td>
                存储将<code>OAuth2Authentication.java</code>对象序列化后的二进制数据.
            </td>
        </tr>
        <tr>
            <td>refresh_token</td>
            <td>
                该字段的值是将<code>refresh_token</code>的值通过MD5加密后存储的.
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <p class="text-info">
                    <em class="glyphicon glyphicon-info-sign"></em> 在项目中,主要操作<code>oauth_access_token</code>表的对象是<code>JdbcTokenStore.java</code>.
                    更多的细节请参考该类.
                </p>
            </td>
        </tr>
        <!-- oauth_refresh_token -->
        <tr>
            <td rowspan="5">oauth_refresh_token</td>
            <td>create_time</td>
            <td>数据的创建时间,精确到秒,由数据库在插入数据时取当前系统时间自动生成(扩展字段)</td>
        </tr>
        <tr>
            <td>token_id</td>
            <td>
                该字段的值是将<code>refresh_token</code>的值通过MD5加密后存储的.
            </td>
        </tr>
        <tr>
            <td>token</td>
            <td>
                存储将<code>OAuth2RefreshToken.java</code>对象序列化后的二进制数据.
            </td>
        </tr>
        <tr>
            <td>authentication</td>
            <td>
                存储将<code>OAuth2Authentication.java</code>对象序列化后的二进制数据.
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <p class="text-info">
                    <em class="glyphicon glyphicon-info-sign"></em> 在项目中,主要操作<code>oauth_refresh_token</code>表的对象是<code>JdbcTokenStore.java</code>.
                    (与操作<code>oauth_access_token</code>表的对象一样);更多的细节请参考该类.
                    <br>
                    如果客户端的grant_type不支持<code>refresh_token</code>,则不会使用该表.
                </p>
            </td>
        </tr>
        <!-- oauth_code -->
        <tr>
            <td rowspan="4">oauth_code</td>
            <td>create_time</td>
            <td>数据的创建时间,精确到秒,由数据库在插入数据时取当前系统时间自动生成(扩展字段)</td>
        </tr>
        <tr>
            <td>code</td>
            <td>
                存储服务端系统生成的<code>code</code>的值(未加密).
            </td>
        </tr>
        <tr>
            <td>authentication</td>
            <td>
                存储将<code>AuthorizationRequestHolder.java</code>对象序列化后的二进制数据.
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <p class="text-info">
                    <em class="glyphicon glyphicon-info-sign"></em> 在项目中,主要操作<code>oauth_code</code>表的对象是<code>JdbcAuthorizationCodeServices.java</code>.
                    更多的细节请参考该类.
                    <br>
                    只有当grant_type为"authorization_code"时,该表中才会有数据产生; 其他的grant_type没有使用该表.
                </p>
            </td>
        </tr>
        </tbody>
    </table>
</body>
</html>