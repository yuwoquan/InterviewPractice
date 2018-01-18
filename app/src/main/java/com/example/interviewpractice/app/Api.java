package com.example.interviewpractice.app;

import com.example.interviewpractice.enity.BannerBean;
import com.example.interviewpractice.enity.PgcBean;
import com.example.interviewpractice.enity.RankListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 鱼握拳 on 2017/12/12.
 */

public interface Api {
    //开眼baseUrl http://baobab.kaiyanapp.com/api/;
    /**
     * 首页banner
     * @return
     */
//    @Headers("Cache-Control: public, max-age=86400")
    @GET("v2/feed")
    Observable<BannerBean> getMessage();

    @GET("v3/ranklist")
    Observable<RankListBean> getRankList(@Query("strategy") String strategy,
                                         @Query("start")int start,
                                         @Query("num") int num);
    @GET("v4/discovery/hot")
    Observable<RankListBean> getSelected( @Query("start")int start,
                                          @Query("num") int num);

    @GET("v4/pgcs/all?start=10&num=10")
    Observable<PgcBean> getPgc();
}
