webpackJsonp([1],{"/3d0":function(e,t,n){t=e.exports=n("rP7Y")(!1),t.push([e.i,".text-center{text-align:center}.text-right{text-align:right}.cur.info{padding-top:20px;padding-left:60px;font-size:65px}.cur.info .time{font-size:60px;margin-top:-23px}.cur.temp{font-size:120px}.cur.city{text-align:right;font-size:40px}.cur.weather{font-size:20px}.cur.weather .icon{font-size:30px}.future .icon{font-size:45px}.future.high,.future .low{margin:0;line-height:30px}",""]),e.exports=e.exports.toString()},0:function(e,t,n){e.exports=n("cDNt")},ESdF:function(e,t){e.exports='<div class="row">\n  <div class="key col-xs-4 col-xs-offset-8 col-xs-pull-4"\n  (click)="doKeyUp()" [ngClass]="{border: _keyUp}"\n  >{{_keyUp?\'\u4e0a\':\'\'}}</div>\n  <div class="key col-xs-4"\n  (click)="doKeyLeft()" [ngClass]="{border: _keyLeft}"\n  >{{_keyLeft?\'\u5de6\':\'\'}}</div>\n  <div class="key col-xs-4"\n  (click)="doKeyEnter()" [ngClass]="{border: _keyEnter}"\n  >{{_keyEnter?\'O\':\'\'}}</div>\n  <div class="key col-xs-4"\n  (click)="doKeyRight()" [ngClass]="{border: _keyRight}"\n  >{{_keyRight?\'\u53f3\':\'\'}}</div>\n  <div class="key col-xs-4 col-xs-push-4"\n  (click)="doKeyDown()" [ngClass]="{border: _keyDown}"\n  >{{_keyDown?\'\u4e0b\':\'\'}}</div>\n</div>\n'},Gvr5:function(e,t){e.exports='<div (click)="do()">{{text}}</div>\n'},HXAl:function(e,t){e.exports='<div class="text-center">\n    <h1>WiFi List</h1>\n</div>\n<div class="list">\n  <ul class="row">\n    <li *ngFor="let item of list; index as i" class="item" id="{{i}}"\n      [ngClass]="{active: current===i}"\n    >\n      <span class="col-xs-1">{{item.isConnected?\'Y\':\'\'}}</span>\n      <span class="col-xs-8">{{item.ssid}}</span>\n      <span class="col-xs-1" *ngIf="item.connecting">{{item.connecting?\'...\':\'\'}}</span>\n      <span class="col-xs-1 iconfont" *ngIf="!item.connecting"\n        [ngClass]="{\'icon-lock\': item.encryt}"\n      ></span>\n      <span class="col-xs-2">{{item.power}}</span>\n    </li>\n  </ul>\n</div>\n<app-control-ten\n  [keyUp]="keyUp" [keyDown]="keyDown" [keyEnter]="keyEnter"\n></app-control-ten>\n<app-bottom-key class="abs bottom left border" [text]="\'\u540e\u9000\'" (click)="back()"></app-bottom-key>\n'},Xa4K:function(e,t,n){t=e.exports=n("rP7Y")(!1),t.push([e.i,":host{width:70px;height:70px;line-height:70px;font-size:24px;text-align:center;cursor:pointer;display:block}:host>*{width:100%;height:100%}",""]),e.exports=e.exports.toString()},cDNt:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var o=n("/oeL"),i=n("Qa4U"),r=n("fc+i"),c=n("CPp0"),s=this&&this.__decorate||function(e,t,n,o){var i,r=arguments.length,c=r<3?t:null===o?o=Object.getOwnPropertyDescriptor(t,n):o;if("object"==typeof Reflect&&"function"==typeof Reflect.decorate)c=Reflect.decorate(e,t,n,o);else for(var s=e.length-1;s>=0;s--)(i=e[s])&&(c=(r<3?i(c):r>3?i(t,n,c):i(t,n))||c);return r>3&&c&&Object.defineProperty(t,n,c),c},p=function(){function e(){this.wifiList=[],this.wifiMode=!1}return e.prototype.ngOnInit=function(){for(var e=this,t=0;t<50;t++)this.wifiList.push({ssid:faker.name.findName(),isConnected:!1,power:+(""+100*Math.random()).replace(/\.\d+/,""),encryt:faker.random.boolean()});this.wiifChange=function(t){e.wifiMode=t}},e.prototype.wiifChange=function(e){},e}();p=s([Object(o.n)({selector:"app-root",template:n("efyd"),styles:[n("hxJY")]})],p);var a=this&&this.__decorate||function(e,t,n,o){var i,r=arguments.length,c=r<3?t:null===o?o=Object.getOwnPropertyDescriptor(t,n):o;if("object"==typeof Reflect&&"function"==typeof Reflect.decorate)c=Reflect.decorate(e,t,n,o);else for(var s=e.length-1;s>=0;s--)(i=e[s])&&(c=(r<3?i(c):r>3?i(t,n,c):i(t,n))||c);return r>3&&c&&Object.defineProperty(t,n,c),c},l=this&&this.__metadata||function(e,t){if("object"==typeof Reflect&&"function"==typeof Reflect.metadata)return Reflect.metadata(e,t)},f=function(){function e(){this.current=0,this.selectItemChange=new o.w,this.mode=new o.w}return e.prototype.scrollItem=function(){var e=document.getElementById(this.current+""),t=e.parentElement.parentElement,n=this.current<=3?0:70*(this.current-3);t.scrollTo(0,n)},e.prototype.keyUp=function(){},e.prototype.keyDown=function(){},e.prototype.keyEnter=function(){},e.prototype.back=function(){this.mode.emit(!1)},e.prototype.ngOnInit=function(){},e.prototype.ngAfterViewInit=function(){var e=this;this.keyUp=function(){0!==e.current&&(e.current--,e.scrollItem())},this.keyDown=function(){e.current+1!==e.list.length&&(e.current++,e.scrollItem())};var t=!1;this.keyEnter=function(){if(!t){var n=e.list[e.current];if(!n.isConnected)if(e.selectItemChange.emit(null),n.encryt)e.selectItemChange.emit(n);else for(var o=0,i=Object.keys(e.list);o<i.length;o++){var r=i[o];!function(o){e.list[o].isConnected=!1,+o===e.current&&(t=!0,e.list[o].connecting=!0,setTimeout(function(){e.list[o].connecting=!1,e.list[o].isConnected=!0,e.selectItemChange.emit(n),e.back()},5e3))}(r)}}}},e}();a([Object(o.E)(),l("design:type",Array)],f.prototype,"list",void 0),a([Object(o.E)(),l("design:type",Object)],f.prototype,"selectItem",void 0),a([Object(o.R)(),l("design:type",Object)],f.prototype,"selectItemChange",void 0),a([Object(o.R)(),l("design:type",Object)],f.prototype,"mode",void 0),f=a([Object(o.n)({selector:"app-wifilist",template:n("HXAl"),styles:[n("h/Zw")]}),l("design:paramtypes",[])],f);var d=this&&this.__decorate||function(e,t,n,o){var i,r=arguments.length,c=r<3?t:null===o?o=Object.getOwnPropertyDescriptor(t,n):o;if("object"==typeof Reflect&&"function"==typeof Reflect.decorate)c=Reflect.decorate(e,t,n,o);else for(var s=e.length-1;s>=0;s--)(i=e[s])&&(c=(r<3?i(c):r>3?i(t,n,c):i(t,n))||c);return r>3&&c&&Object.defineProperty(t,n,c),c},y=this&&this.__metadata||function(e,t){if("object"==typeof Reflect&&"function"==typeof Reflect.metadata)return Reflect.metadata(e,t)},h=function(){function e(){this.selectItemChange=new o.w,this.mode=new o.w,this.isConnecting=!1}return e.prototype.ngOnInit=function(){var e=this;this.back=function(){e.selectItem=null,e.selectItemChange.emit(e.selectItem)},this.connect=function(){e.isConnecting||(e.isConnecting=!0,setTimeout(function(){e.selectItem.isConnected=!0,e.selectItemChange.emit(e.selectItem),e.mode.emit(!1)},5e3))}},e.prototype.back=function(){},e.prototype.connect=function(){},e.prototype.getWords=function(e){this.words=e},e}();d([Object(o.E)(),y("design:type",Object)],h.prototype,"selectItem",void 0),d([Object(o.R)(),y("design:type",Object)],h.prototype,"selectItemChange",void 0),d([Object(o.R)(),y("design:type",Object)],h.prototype,"mode",void 0),h=d([Object(o.n)({selector:"app-wifiset",template:n("xDvW"),styles:[n("jryH")]}),y("design:paramtypes",[])],h);var u=this&&this.__decorate||function(e,t,n,o){var i,r=arguments.length,c=r<3?t:null===o?o=Object.getOwnPropertyDescriptor(t,n):o;if("object"==typeof Reflect&&"function"==typeof Reflect.decorate)c=Reflect.decorate(e,t,n,o);else for(var s=e.length-1;s>=0;s--)(i=e[s])&&(c=(r<3?i(c):r>3?i(t,n,c):i(t,n))||c);return r>3&&c&&Object.defineProperty(t,n,c),c},g=this&&this.__metadata||function(e,t){if("object"==typeof Reflect&&"function"==typeof Reflect.metadata)return Reflect.metadata(e,t)},m=function(){function e(){}return Object.defineProperty(e.prototype,"keyUp",{set:function(e){this._keyUp=e},enumerable:!0,configurable:!0}),Object.defineProperty(e.prototype,"keyLeft",{set:function(e){this._keyLeft=e},enumerable:!0,configurable:!0}),Object.defineProperty(e.prototype,"keyEnter",{set:function(e){this._keyEnter=e},enumerable:!0,configurable:!0}),Object.defineProperty(e.prototype,"keyRight",{set:function(e){this._keyRight=e},enumerable:!0,configurable:!0}),Object.defineProperty(e.prototype,"keyDown",{set:function(e){this._keyDown=e},enumerable:!0,configurable:!0}),e.prototype.doKeyUp=function(){this._keyUp&&this._keyUp()},e.prototype.doKeyLeft=function(){this._keyLeft&&this._keyLeft()},e.prototype.doKeyEnter=function(){this._keyEnter&&this._keyEnter()},e.prototype.doKeyRight=function(){this._keyRight&&this._keyRight()},e.prototype.doKeyDown=function(){this._keyDown&&this._keyDown()},e}();u([Object(o.E)(),g("design:type",Object),g("design:paramtypes",[Object])],m.prototype,"keyUp",null),u([Object(o.E)(),g("design:type",Object),g("design:paramtypes",[Object])],m.prototype,"keyLeft",null),u([Object(o.E)(),g("design:type",Object),g("design:paramtypes",[Object])],m.prototype,"keyEnter",null),u([Object(o.E)(),g("design:type",Object),g("design:paramtypes",[Object])],m.prototype,"keyRight",null),u([Object(o.E)(),g("design:type",Object),g("design:paramtypes",[Object])],m.prototype,"keyDown",null),m=u([Object(o.n)({selector:"app-control-ten",template:n("ESdF"),styles:[n("lv2+")]}),g("design:paramtypes",[])],m);var x=this&&this.__decorate||function(e,t,n,o){var i,r=arguments.length,c=r<3?t:null===o?o=Object.getOwnPropertyDescriptor(t,n):o;if("object"==typeof Reflect&&"function"==typeof Reflect.decorate)c=Reflect.decorate(e,t,n,o);else for(var s=e.length-1;s>=0;s--)(i=e[s])&&(c=(r<3?i(c):r>3?i(t,n,c):i(t,n))||c);return r>3&&c&&Object.defineProperty(t,n,c),c},b=this&&this.__metadata||function(e,t){if("object"==typeof Reflect&&"function"==typeof Reflect.metadata)return Reflect.metadata(e,t)},w=function(){function e(e){this.http=e,this.mode=new o.w,this.curTemp=Math.floor(10*Math.random())+10,this.disMode=0,this.curDate=Date.now()}return e.prototype.ngOnInit=function(){var e=this;setInterval(function(){++e.disMode>=2&&(e.disMode=0)},3e3),setInterval(function(){e.curDate=Date.now()},1e3),this.selectItem&&this.http.get("//weixin.jirengu.com/weather").subscribe(function(t){var n=t.json().weather[0];e.remoteWeather=n,e.remoteFutureWeather=n.future.slice(1,4)})},e.prototype.getWeatherIconClass=function(e){return"\u9634"===e?"icon-yin":"\u6674"===e?"icon-qing":"\u591a\u4e91"===e?"icon-duoyun":-1!==e.indexOf("\u96e8")?"icon-yu":""},e.prototype.wifi=function(){this.mode.emit(!0)},e}();x([Object(o.E)(),b("design:type",Object)],w.prototype,"selectItem",void 0),x([Object(o.R)(),b("design:type",Object)],w.prototype,"mode",void 0),w=x([Object(o.n)({selector:"app-weather",template:n("h/Hl"),styles:[n("/3d0")]}),b("design:paramtypes",["function"==typeof(k=void 0!==c.a&&c.a)&&k||Object])],w);var k,v=this&&this.__decorate||function(e,t,n,o){var i,r=arguments.length,c=r<3?t:null===o?o=Object.getOwnPropertyDescriptor(t,n):o;if("object"==typeof Reflect&&"function"==typeof Reflect.decorate)c=Reflect.decorate(e,t,n,o);else for(var s=e.length-1;s>=0;s--)(i=e[s])&&(c=(r<3?i(c):r>3?i(t,n,c):i(t,n))||c);return r>3&&c&&Object.defineProperty(t,n,c),c},j=this&&this.__metadata||function(e,t){if("object"==typeof Reflect&&"function"==typeof Reflect.metadata)return Reflect.metadata(e,t)},O=function(){function e(){this.x=0,this.y=0,this._words="",this.words=new o.w,this.keywords=[],this.lowerWords=[["q","w","e","r","t","y","u","i","o","p"],["a","s","d","f","g","h","j","k","l","delete"],["","z","x","c","v","b","n","m","",""],["ABC","&123","space"]],this.upperWords=[["Q","W","E","R","T","Y","U","I","O","P"],["A","S","D","F","G","H","J","K","L","delete"],["","Z","X","C","V","B","N","M","",""],["abc","&123","space"]],this.numberWords=["1234567890".split(""),'-/:;()\xa5&@"'.split(""),["",""].concat(".,?!'".split("")).concat(["","","delete"]),["ABC","abc","space"]]}return e.prototype.ngOnInit=function(){for(var e=this,t=[this.lowerWords,this.upperWords,this.numberWords],n=0,o=t;n<o.length;n++){var i=o[n],r=this.actionBtnText?[this.actionBtnText]:[],c=i[i.length-1];i[i.length-1]=c.concat(r)}this.keywords=this.lowerWords;var s=function(){var t=e.keywords[e.y][e.x];"string"!=typeof t&&(t||(e.x=e.keywords[e.y].length-1))};this.keyUp=function(){0!==e.y&&(e.y--,s())},this.keyDown=function(){e.y!==e.keywords.length-1&&(e.y++,s())},this.keyLeft=function(){if(0===e.x)return void(e.x=e.keywords[e.y].length-1);e.x--},this.keyRight=function(){if(e.x>=e.keywords[e.y].length-1)return void(e.x=0);e.x++},this.keyEnter=function(){var t=e.keywords[e.y][e.x];switch(t){case"space":e._words+=" ";break;case"delete":if(0===e._words.length)break;e._words=e._words.slice(0,e._words.length-1);break;case e.actionBtnText:e.actionBtnFn&&e.actionBtnFn();break;case"ABC":e.keywords=e.upperWords;break;case"abc":e.keywords=e.lowerWords;break;case"&123":e.keywords=e.numberWords;break;default:e._words+=t}e.words.emit(e._words)}},e.prototype.keyUp=function(){},e.prototype.keyDown=function(){},e.prototype.keyLeft=function(){},e.prototype.keyRight=function(){},e.prototype.keyEnter=function(){},e}();v([Object(o.R)(),j("design:type",Object)],O.prototype,"words",void 0),v([Object(o.E)(),j("design:type",Object)],O.prototype,"actionBtnText",void 0),v([Object(o.E)(),j("design:type",Object)],O.prototype,"actionBtnFn",void 0),O=v([Object(o.n)({selector:"app-keyboard",template:n("qZnf"),styles:[n("v2bu")]}),j("design:paramtypes",[])],O);var _=this&&this.__decorate||function(e,t,n,o){var i,r=arguments.length,c=r<3?t:null===o?o=Object.getOwnPropertyDescriptor(t,n):o;if("object"==typeof Reflect&&"function"==typeof Reflect.decorate)c=Reflect.decorate(e,t,n,o);else for(var s=e.length-1;s>=0;s--)(i=e[s])&&(c=(r<3?i(c):r>3?i(t,n,c):i(t,n))||c);return r>3&&c&&Object.defineProperty(t,n,c),c},R=this&&this.__metadata||function(e,t){if("object"==typeof Reflect&&"function"==typeof Reflect.metadata)return Reflect.metadata(e,t)},I=function(){function e(){}return e.prototype.ngOnInit=function(){},e.prototype.do=function(){this.doClick&&this.doClick()},e}();_([Object(o.E)(),R("design:type",String)],I.prototype,"text",void 0),_([Object(o.E)(),R("design:type",Object)],I.prototype,"doClick",void 0),I=_([Object(o.n)({selector:"app-bottom-key",template:n("Gvr5"),styles:[n("Xa4K")]}),R("design:paramtypes",[])],I);var C=this&&this.__decorate||function(e,t,n,o){var i,r=arguments.length,c=r<3?t:null===o?o=Object.getOwnPropertyDescriptor(t,n):o;if("object"==typeof Reflect&&"function"==typeof Reflect.decorate)c=Reflect.decorate(e,t,n,o);else for(var s=e.length-1;s>=0;s--)(i=e[s])&&(c=(r<3?i(c):r>3?i(t,n,c):i(t,n))||c);return r>3&&c&&Object.defineProperty(t,n,c),c},D=function(){function e(){}return e}();D=C([Object(o.L)({declarations:[p,f,h,m,w,O,I],imports:[r.a,c.b],providers:[],bootstrap:[p]})],D),{production:!0}.production&&Object(o._19)(),Object(i.a)().bootstrapModule(D).catch(function(e){return console.log(e)})},efyd:function(e,t){e.exports='<app-wifilist [(selectItem)]="wifiContent" [list]="wifiList"\n  (mode)="wiifChange($event)"\n  *ngIf="wifiMode&&!wifiContent"\n></app-wifilist>\n<app-wifiset [(selectItem)]="wifiContent" (mode)="wiifChange($event)"\n  *ngIf="wifiMode&&wifiContent&&!wifiContent.isConnected"\n></app-wifiset>\n<app-weather [selectItem]="wifiContent" (mode)="wiifChange($event)"\n  *ngIf="!wifiMode"\n></app-weather>\n'},gFIY:function(e,t){function n(e){return Promise.resolve().then(function(){throw new Error("Cannot find module '"+e+"'.")})}n.keys=function(){return[]},n.resolve=n,e.exports=n,n.id="gFIY"},"h/Hl":function(e,t){e.exports='\x3c!-- <div *ngIf="disMode===0">\n  <p class="cur weather iconfont icon-weather">{{curWeather}}</p>\n  <p class="cur temp">{{curTemp}}\u2103</p>\n</div>\n<div *ngIf="disMode===1">\n  <p class="cur time">{{curTime}}</p>\n  <p class="cur date">{{curDate}}</p>\n</div> --\x3e\n<div class="cur info">\n  <p *ngIf="remoteWeather">\n    {{remoteWeather.future[0].date}} {{remoteWeather.future[0].day}}\n  </p>\n  <p *ngIf="!remoteWeather">{{curDate | date:\'yyyy-MM-dd\'}}</p>\n  <p class="time">{{curDate | date:\'HH:mm:ss\'}}</p>\n</div>\n<div class="row" style="padding: 0 35px;padding-top:170px;">\n  <div class="col-xs-12" style="font-size: 18px;margin-bottom: -25px;">\n    \u5ba4\u5185\u6e29\u5ea6\n  </div>\n  <div class="col-xs-6 cur temp">{{curTemp}}\u2103</div>\n  <div class="col-xs-6 cur city" *ngIf="remoteWeather">\n    <p>{{remoteWeather.city_name}}</p>\n    <p style="font-size:30px;">{{remoteWeather.last_update | date:\'HH:mm\'}} \u53d1\u5e03</p>\n  </div>\n</div>\n<div class="cur weather" *ngIf="remoteWeather" style="padding: 0 35px;">\n  <i class="iconfont icon"\n    [ngClass]="getWeatherIconClass(remoteWeather.now.text)"\n  ></i>\n  <span>{{remoteWeather.now.text}} {{remoteWeather.future[0].low}}~{{remoteWeather.future[0].high}}\u2103</span>\n</div>\n<hr *ngIf="remoteWeather">\n<div class="row future" *ngIf="remoteWeather" style="padding: 0 35px;">\n  <div class="col-xs-4" *ngFor="let f of remoteWeather.future.slice(1, 4)">\n    <p class="text-center">{{f.day}}</p>\n    <div class="col-xs-6 iconfont icon"\n      [ngClass]="getWeatherIconClass(f.text.split(\'/\')[0])">\n    </div>\n    <div class="col-xs-6 text-right">\n      <p class="low">{{f.low}}\u2103</p>\n      <p class="high">{{f.high}}\u2103</p>\n    </div>\n    <p class="col-xs-12 text-center">{{f.text.split(\'/\')[0]}}</p>\n  </div>\n</div>\n<app-bottom-key class="abs bottom left iconfont icon-wifi"\n  (click)="wifi()"\n></app-bottom-key>\n\n'},"h/Zw":function(e,t,n){t=e.exports=n("rP7Y")(!1),t.push([e.i,":host{margin-top:-20px;display:block}.text-center{text-align:center}.list{margin-right:35px;margin-left:35px;margin-bottom:20px;height:500px;border:1px solid #efefef;overflow-y:scroll}ul{padding:0}.row{margin:0}.item{height:70px;font-size:30px;line-height:70px;list-style-type:none}.item.active{border-bottom:1px solid #eee;border-top:1px solid #eee}.item>*{overflow:hidden}",""]),e.exports=e.exports.toString()},hxJY:function(e,t,n){t=e.exports=n("rP7Y")(!1),t.push([e.i,":host{width:600px;height:800px;background-color:#000;color:#fff;display:block}",""]),e.exports=e.exports.toString()},jryH:function(e,t,n){t=e.exports=n("rP7Y")(!1),t.push([e.i,":host{font-size:30px}.text-right{text-align:right}.row{height:70px;line-height:70px}.password{margin-top:10px}.button,.password{height:50px;line-height:50px}.button{text-align:center;font-size:26px}app-keyboard{position:absolute;top:355px;width:600px;display:block}",""]),e.exports=e.exports.toString()},"lv2+":function(e,t,n){t=e.exports=n("rP7Y")(!1),t.push([e.i,":host{margin-left:auto;margin-right:auto;display:block;width:230px;height:230px}.row{margin:0}.key{height:70px;font-size:40px;line-height:70px;cursor:pointer;text-align:center}",""]),e.exports=e.exports.toString()},qZnf:function(e,t){e.exports="<div>\n  <div class=\"key-group row\" *ngFor=\"let group of keywords; index as j\"\n  >\n    <div class=\"col-xs-1\"></div>\n    <div class=\"key col-xs-1 bottom-border\"\n      *ngFor=\"let word of group; index as i\"\n      [ngClass]=\"{'col-xs-1': word!=='space'&&word!==actionBtnText, 'col-xs-6': word==='space'&&-1!==group.indexOf(actionBtnText), 'col-xs-8': word==='space'&&-1===group.indexOf(actionBtnText), 'col-xs-2': word===actionBtnText, 'shorter':word!=='space'&&word.length>1, 'no-border': word.length===0, 'active': i===x&&j===y}\"\n    >\n      {{word==='delete'?'\u2190':word}}\n    </div>\n  </div>\n</div>\n<app-control-ten\n  [keyUp]=\"keyUp\" [keyDown]=\"keyDown\" [keyLeft]=\"keyLeft\" [keyRight]=\"keyRight\"\n  [keyEnter]=\"keyEnter\"\n></app-control-ten>\n"},v2bu:function(e,t,n){t=e.exports=n("rP7Y")(!1),t.push([e.i,".key-group{margin-bottom:10px}.key{padding:0;text-align:center;height:45px;line-height:45px}.key.no-border{border-width:0}.key.shorter{font-size:20px}.key.active{color:#000;background-color:#fff}",""]),e.exports=e.exports.toString()},xDvW:function(e,t){e.exports='<div class="row">\n  <div class="col-xs-3 col-xs-offset-1 text-right">SSID</div>\n  <div class="col-xs-7">{{selectItem.ssid}}</div>\n</div>\n<div class="row">\n  <div class="col-xs-3 col-xs-offset-1 text-right">Password</div>\n  <div class="col-xs-7 password bottom-border">{{words}}</div>\n</div>\n<app-keyboard (words)="getWords($event)"\n  [actionBtnText]="\'\u8fde\u63a5\'" [actionBtnFn]="connect"\n></app-keyboard>\n<app-bottom-key class="abs bottom left border" [text]="\'\u540e\u9000\'"\n  [doClick]="back"\n></app-bottom-key>\n\x3c!-- <app-bottom-key class="right" [text]="\'\u8fde\u63a5\'"\n  [doClick]="connect"\n  *ngIf="words&&words.length>=8"\n></app-bottom-key> --\x3e\n'}},[0]);