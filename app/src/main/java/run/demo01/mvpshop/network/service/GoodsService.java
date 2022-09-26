package run.demo01.mvpshop.network.service;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import run.demo01.mvpshop.bean.BaseBean;
import run.demo01.mvpshop.bean.Goods;
import run.demo01.mvpshop.bean.GoodsDetail;

public interface GoodsService {
    @GET("goods_list")
    Flowable<BaseBean<List<Goods>>> getGoods();

    @GET("goods_detail")
    Flowable<BaseBean<GoodsDetail>> getGoodDetail(@Query("goodsId")int goodsId);
}
