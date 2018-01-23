package com.example.interviewpractice.http;


import com.example.interviewpractice.app.Constans;

/**
 * 描述：业务对象的基类
 */
public class BaseModel{
    //retrofit请求数据的管理类
    public RetrofitManager retrofitManager,zhRetrofitManager;

    public BaseModel() {
        retrofitManager = RetrofitManager.builder(Constans.EyepetizerBaseUrl);
        zhRetrofitManager=RetrofitManager.builder(Constans.ZhihuBaseURL);
    }
}
