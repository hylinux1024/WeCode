## 这是一款可以查阅Github上的热门趋势的APP

随时查阅当前Github上的热门趋势。使用Material Design设计风格，和流行的MVP+Retrofit+RxJava框架。数据抓取自[https://github.com/trending](https://github.com/trending)

### Features

- Material Design设计风格
- MVP结构
- 使用Retrofit网络请求
- 多种内置皮肤可以切换
- 可订阅常见编程语言
- 支持搜索

### Demo

![demo](http://pp.myapp.com/ma_pic2/0/shot_52402110_1_1487174558/550)

![demo2](http://pp.myapp.com/ma_pic2/0/shot_52402110_2_1487174558/550)

![demo3](http://pp.myapp.com/ma_pic2/0/shot_52402110_3_1487174558/550)

![demo4](http://pp.myapp.com/ma_pic2/0/shot_52402110_4_1487174558/550)

### Thanks

感谢以下开源协议

```groovy

    compile 'com.android.support:appcompat-v7:25.0.1'
    compile 'com.android.support:recyclerview-v7:25.0.1'
    compile 'com.android.support:cardview-v7:25.0.1'
    //design
    compile 'com.android.support:design:25.0.1'
    //custom tabs
    compile 'com.android.support:customtabs:25.0.1'
    compile project(':customtabs')

    //编译RxJava
    compile 'io.reactivex:rxjava:1.1.6'
    //编译RxAndroid
    compile 'io.reactivex:rxandroid:1.2.1'
    //编译Retrofit及其相关库，包括Gson
    compile 'com.squareup.okhttp3:okhttp:3.3.1'
    compile 'com.squareup.retrofit2:retrofit:2.1.0'
    compile 'com.squareup.retrofit2:converter-gson:2.1.0'
    compile 'com.squareup.retrofit2:adapter-rxjava:2.1.0'
    compile 'com.squareup.okhttp3:logging-interceptor:3.3.1'
    //view injector
    compile 'com.jakewharton:butterknife:8.4.0'
    annotationProcessor 'com.jakewharton:butterknife-compiler:8.4.0'
    //glide
    compile 'com.github.bumptech.glide:glide:3.7.0'
    //TagGroup
    compile 'me.gujun.android.taggroup:library:1.4@aar'
    //EventBus
    compile 'org.greenrobot:eventbus:3.0.0'
    compile 'org.greenrobot:greendao:3.2.0'
```
### App下载
[App](http://sj.qq.com/myapp/detail.htm?apkName=net.angrycode.wehub)
