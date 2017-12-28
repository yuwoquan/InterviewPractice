package com.example.interviewpractice.presenter;

import android.content.Context;

import com.example.interviewpractice.base.BasePresenterImp;
import com.example.interviewpractice.enity.BannerBean;
import com.example.interviewpractice.model.BannerModelImp;
import com.example.interviewpractice.view.BannerView;

/**
 * Created by Administrator on 2017/12/26.
 */

public class BannerPresenterImp  extends BasePresenterImp<BannerView,BannerBean> implements BannerPresenter{
    private Context context=null;
    private BannerModelImp bannerModelImp=null;
    /**
     * @param view 具体业务的视图接口对象
     * @descriptoin 构造方法
     */
    public BannerPresenterImp(BannerView view,Context context) {
        super(view);
        this.context=context;
        this.bannerModelImp= new BannerModelImp(context);
    }


    @Override
    public void loadBanner() {
       bannerModelImp.loadBanner(this);
    }



    @Override
    public void onUnsubscribe() {
        bannerModelImp.onUnsubscribe();
    }

    @Override
    public void categoryTabPes() {

    }

}
