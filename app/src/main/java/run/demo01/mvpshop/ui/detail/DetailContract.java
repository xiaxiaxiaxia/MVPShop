package run.demo01.mvpshop.ui.detail;

import io.reactivex.rxjava3.core.Flowable;
import run.demo01.mvpshop.bean.BaseBean;
import run.demo01.mvpshop.bean.GoodsDetail;

public interface DetailContract {
    interface IDetailPresenter{
        void getGoodsDetail(int id);
    }

    interface IDetailView{
        void getDetailSuccess(GoodsDetail goodsDetail);
        void getDetailError(Throwable e);
        void showProgressBar();
        void hideProgressBar();
    }

    interface IDetailModel{
        Flowable<BaseBean<GoodsDetail>> getGoodsDetail(int id);
    }
}
