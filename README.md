## blog system

csdn blog:  https://blog.csdn.net/itzhongzi/category_9881181.html

### 用户模块

```
    功能梳理：
        1.用户注册时需提供用户名、用户密码、用户手号、昵称。
        2.用户登录时可以采用用户名或邮箱或手机号进行登录。
        3.用户可以发布博文、发表评论、回复，还可以添加其他用友。
```

- 用户注册：
  - api: /api/user/register     post
  - 参数： username password phone nickname
  - 同一个手机号只能注册一个，后台会自动检测，密码不能包空字符串，并且后台自动加密存入数据库
  - 返回数据

```json
{
    "code": 0,
    "description": "成功",
    "data": {
        "phone": "17862806854",
        "nickname": "以勒"
    }
}

```

- 用户登录:
  - api: /api/user/login        post 
  - 参数: phone password
  - 登录成功后，后台会返回```jwt```生成的```token```，```访问后续的接口都需要验证token的合法性```
  - 返回数据格式

```json
{
    "code": 0,
    "description": "success",
    "data": {
        "phone": "17862806857",
        "nickname": "以勒",
        "username": "lihongwei",
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsaWhvbmd3ZWkiLCJpZCI6NTYsInBhc3N3b3JkIjoiLTRuMzVvNGQxaWlxZjVqZGE4MDg2bDd2N2R0ODJmdTduIiwiaWF0IjoxNTg2OTExNjc0LCJleHAiOjE1ODc1MTY0NzR9.SWHz1aCVlhvBESbTHYno-VTTiA6mjdxxmWnRvslTaPw"
    }
}
```

### 文章

```
    功能梳理：
      1. 用户可以发表文章
      2. 每篇文章都有自己的点赞数，浏览量，评论
      3. 每篇文章都有自己相关的图文
```
- 发布文章
  - api: /api/article/publish     post
  - 参数： title content
  - 发布博文需要携带登录生成的token
  - 返回结果

```json
{
    "code": 0,
    "description": "文章插入成功",
    "data": null
}

```

- 图片上传：
  - api: /api/img/upload_img  post
  - 参数： img (MultipartFile类型)
  - 上传成功后图片会存放到七牛云，并将响应的图片名字存到数据库
  - 需要携带token
  - 返回数据格式

```json
{
    "code": 0,
    "description": "上传成功",
    "data": {
        "imageName": "ff065a3b7f5c40db905614dee23ce6b7png",
        "imagePath": "http://q8eezq1qm.bkt.clouddn.com/ff065a3b7f5c40db905614dee23ce6b7png?e=1587023135&token=N0Jk2mJqhLzI_6qkWuwnwxtvqDoKL7cziO_Z0ugf:Wis3Z6mOE8onDHj_oirTPPVUXIs="
    }
}
```

> 博客文章图片渲染思路：
>  给 img标签增加一个 data-img-name的标签，用来存储七牛云上图片的名字。 
> 然后前端遍历所有图片，用该名字从服务器获取链接，并把响应的链接替换上即可。
> 
> ```<img style="max-width: 200px;" src="./video_cover.png" data-img-name="" alt="">```



- 查询所有文章列表
  - api: /api/article/all     get
  - 参数：page size 
  - 查询所有的文章列表，支持分页,返回的数据格式如下
  - 返回结果如下：
```json
{
    "code": 0,
    "description": "success",
    "data": {
        "data": [
            {
                "articleId": 2,
                "userId": 56,
                "articleTitle": "ddd",
                "articleContent": "erere",
                "articleViews": 100,
                "articleCommentCount": 0,
                "articleDate": null,
                "articleLikeCount": 0
            },
            {
                "articleId": 3,
                "userId": 56,
                "articleTitle": "ddd",
                "articleContent": "erere",
                "articleViews": 0,
                "articleCommentCount": 0,
                "articleDate": null,
                "articleLikeCount": 0
            }
        ],
        "total_size": 6,
        "total_page": 3,
        "current_page": 1
    }
}
```

- 查询某个用户的文章列表
  - api: /api/article/user_article     get
  - 参数：page size 
  - 查询所有的文章列表，支持分页,返回的数据格式如下
  - 返回结果如下：

```json
{
    "code": 0,
    "description": "success",
    "data": {
        "data": [
            {
                "articleId": 2,
                "userId": 56,
                "articleTitle": "ddd",
                "articleContent": "erere",
                "articleViews": 100,
                "articleCommentCount": 0,
                "articleDate": null,
                "articleLikeCount": 0
            },
            {
                "articleId": 3,
                "userId": 56,
                "articleTitle": "ddd",
                "articleContent": "erere",
                "articleViews": 0,
                "articleCommentCount": 0,
                "articleDate": null,
                "articleLikeCount": 0
            }
        ],
        "total_size": 6,
        "total_page": 3,
        "current_page": 1
    }
}

```

- 查询文章详情
  - api: /api/article/get_article_by_id     get
  - 参数：article_id
  - 查询某一篇文章详情,返回的数据格式如下
  - 返回结果如下：
  
 ```json
{
    "code": 0,
    "description": "success",
    "data": {
        "articleId": 2,
        "userId": 56,
        "articleTitle": "ddd",
        "articleContent": "erere",
        "articleViews": 100,
        "articleCommentCount": 0,
        "articleDate": null,
        "articleLikeCount": 0
    }
}

```





