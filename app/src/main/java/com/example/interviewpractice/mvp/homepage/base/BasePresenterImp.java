package com.example.interviewpractice.mvp.homepage.base;

import com.example.interviewpractice.http.IBaseRequestCallBack;

/**
 * 描述：
 *  * 代理对象的基础实现 ：  一些基础的方法
 * @param <V> 视图接口对象(view) 具体业务各自继承自IBaseView
 * @param <T> 业务请求返回的具体对象
 */
public class BasePresenterImp<V extends IBaseView, T> implements IBaseRequestCallBack<T> {

    private IBaseView iBaseView = null;  //基类视图

    /**
     * @descriptoin	 构造方法
     * @param view 具体业务的视图接口对象
     */
    public BasePresenterImp(V view) {
        this.iBaseView = view;
    }

    /**
     * @descriptoin	请求之前显示progress
     */

    @Override
    public void requestError(Throwable throwable) {

    }

    /**
     * @descriptoin	请求成功获取成功之后的数据信息
     */
    @Override
    public void requestSuccess(T callBack) {
        iBaseView.loadDataSuccess(callBack);
    }


}
