package run.demo01.mvpshop.ui.detail;

import io.reactivex.rxjava3.core.Flowable;
import run.demo01.mvpshop.bean.BaseBean;
import run.demo01.mvpshop.bean.GoodsDetail;
import run.demo01.mvpshop.network.RetrofitClient;
import run.demo01.mvpshop.network.service.GoodsService;

public class DetailModel implements DetailContract.IDetailModel {
    @Override
    public Flowable<BaseBean<GoodsDetail>> getGoodsDetail(int id) {
        GoodsService goodsService = RetrofitClient.getInstance().getService(GoodsService.class);
        return goodsService.getGoodDetail(id);
    }
}
