package run.demo01.mvpshop.ui.home;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import run.demo01.mvpshop.bean.BaseBean;
import run.demo01.mvpshop.bean.Goods;
import run.demo01.mvpshop.network.RetrofitClient;
import run.demo01.mvpshop.network.service.GoodsService;

public class HomeModel implements HomeContract.IHomeModel{
    /**
     * 获取商品列表
     * @return
     */
    @Override
    public Flowable<BaseBean<List<Goods>>> getGoodsList() {
        return RetrofitClient.getInstance().getService(GoodsService.class).getGoods();
    }
}
