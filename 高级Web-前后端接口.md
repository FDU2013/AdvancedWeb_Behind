# 闲旦-前后端接口

### 功能概述

使用复旦uis注册、常规登录。

登录进入**主页**，主页可以选择两个房间，一个房间是在里面游览学习，一个房间是在里面答题获得积分。选择房间的页面顺便呈现个人信息and积分信息。

（PS.关于答题获得积分，如果时间不够的话，就不做成多人同时在线的房间了，直接做成一个网页端）

还有一个积分兑换界面，and查看自己的兑换记录，每个兑换完是一个兑换码。至于如何兑现，这个还在考虑，就先这样，不用完全做完。



****

### 设计风格（前后端交互）

接口调用使用Restful的风格。

所有的**response的body都是data+code+msg**。

code为200表示请求成功，使用data中的数据，如果code不是200，则弹出一个表示失败的弹窗，并显示msg的内容

除了登录以外，**所有的request**都需要在Header中携带auth=token，userID=userID。role不用携带，但是建议保存在session中，用于前端界面路由筛选。

request的Body如果携带多个参数，就把多个参数以结构体的方式在JSON中打包传送，如果只有一个参数，则直接以键值对的方式传输

**所有的请求都使用POST的方式**，不刻意区分delete、post、get、update

命名风格：ID全大写，基本使用驼峰命名法

****





### 接口文档（前后端）

#### 1.登录

>POST请求：/auth/login

header里面可以不携带东西

在RequestBody里面携带JSON类型的数据

```JSON
{
    "userID": "admin",
    "password": "admin"
}
```

成功样例的Response的Body

```JSON
{
    "code": 200,
    "msg": "登录成功",
    "data": {
        "userID": "admin",
        "role": "Admin",
        "token": "aa"
    }
}
```

失败样例的Response的Body

```json
{
    "code": 1000,
    "msg": "登录失败",
    "data": null
}
```



#### 2.注册

POST请求

> /auth/register

唯二不需要携带header中信息的接口之一

```json
{
    "stuNum":"20302010032",
    "userID":"1234zjx",
    "password":"1234",
    "name":"zjx",
    "email":"20302010032@fudan.edu.cn",
    "phone":"13906638321"
}
```

```JSON
{
    "code": 200,
    "msg": "注册成功",
    "data": "1234zjx"
}
```





****

### 个人信息相关接口/user

注意，登录过后所有的请求的header中都需要带上token和userID

#### 3.获取个人信息

> /user/info

header中携带好ID和token，Body中不需要参数,

注意，response里的==参数叫userID，而不是ID==

```JSON
{
    "code": 200,
    "msg": "获取个人信息成功",
    "data": {
        "stuNum":"20302010032",
        "phone": "13906638321",
        "name": "张佳洵",
        "userID": "1234zjx",
        "email": "20302010032@fudan.edu.cn",
        "score":100,//积分
        "todayScore":50,//今天获得的积分数，达到50分的时候要不变成红色？？？？
        "avatar":"www.aaa.com"//头像URL
    }
}
```





#### 4.获取可兑换物清单

> /user/getItems

不分页了，就是每一个可兑换物一个方形的框框，每行4个列下来.

或者直接模仿软件实践帖子的样子去分页也行。哪样好实现就哪样

如果分页的话请求体里面带上pageSize和pageNum。

```json
{
    "code": 200,
    "msg": null,
    "data": [{
        "itemID":1,
        "imageURL":"www.aaa.com",//图片URL
        "itemName": "篮球",
        "cost":100,//需要100积分兑换
        "description":"唱跳rap"
    	},
        {
		//...
        }]
}
```





#### 5.进行兑换

>/user/buyItem

请求体里面带上itemID

```json
1
```

```json
{
    "code": 200,
    "msg": "兑换成功",
    "data": 
}
```





#### 6.查看自己已经兑换过的商品

> /user/myItem

分页查询

```json
{
    "pageSize":10,
    "pageNum":1
}
```

```json
{
    "code": 200,
    "msg": "succ",
    "data": {
        "total": 1,
        "records": [
            {
                "tradeID": 1,//交易记录的ID
                "userID": "yuki",
                "itemID":1,
                "imageURL":"www.aaa.com",//兑换物品图片URL
                "itemName": "篮球",
                "cost":100,//需要100积分兑换
                "description":"唱跳rap",
                "code":"ASDFGHJ", //兑换码
                "time":"2023-01-01 11:00:00",//兑换时间
                "state":"待兑换"
            }
        ]
    }
}
```



### 答题相关的接口/question

答题前先选择一个topic，类似于从语数英中选择一个科目

每次答题的流程为：先获取题目信息->等待选择ABC->选择后反馈给后端->不管答题正确还是失败进入下一题

#### 7.随机获得一道题目的相关信息

> /question/getOne

请求头里面需要携带参数

```json
{
    "topic" : "画展",
    "previousIndex" : 0//刚开始做题的话携带0就好，previousIndex 表示刚刚做过的那道题是这个topic里面的第几题，希望不要连续出现相同的题目
}
```



```json
{
    "code": 200,
    "msg": "succ",
    "data": {
        "questionID": 1,//这个数字表示题目的编号
        "image" : "www.xxx.com",
        "index":1,
        "title":"以下答案正确的是：",
        "A":"",
        "B":"",
        "C":""
    }
}
```



#### 8.向后端提交选项

每次提交完选项，不管回答正确还是错误，都要重新向后端要一道题目来，并且根据data更新一下显示的积分信息

> /question/commitAnswer

```json
{
    "questionID":1,
    "choose" : "A"
}
```

```json
{
    "code": 200,
    "msg": "回答错误！", //不管是回答正确还是回答错误code都是200，只有msg会不一样，只有程序出现运行问题的时候code才会不是200,不管回答错误还是正确用某种方式把msg显示出来
    "data": {
        "right" : true;
        "score" : 110,  //这个110表示更新后的积分信息
        "todayScore" : 40 //今日答题积分,50表示已经达到上限
    }
}
```



### 管理员接口/admin

#### 9.分页获得所有用户的分数相关信息

> /admin/getAllUserInfo

```json
{
    "pageSize":10,
    "pageNum" :1
}
```

```json
{
    "code": 200,
    "msg": null, 
    "data": {
        "total" : 1,
        "records":[
            {
                "userID": "1234zjx",
                "score":100,//积分
                "todayScore":50,//今天获得的积分数，达到50分的时候要不变成红色？？？？
                "avatar":"www.aaa.com"//头像URL
            }
        ]
    }
}
```



#### 10.获得每个房间的人数

这个不是跟Springboot后端要的，这个得和app.js对应的nodejs后端要，得到的是键值对的形式

在前端界面里面开一个socket,进行通信，获取信息，注意这和常规的前后端通信不一样

```json
{
    "room1name" : 5,
    "room2name" :1
}
```













