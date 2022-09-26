package run.demo01.mvpshop.ui.home;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import run.demo01.mvpshop.bean.BaseBean;
import run.demo01.mvpshop.bean.Goods;

public interface HomeContract {
    interface IHomePresenter{
        void getGoodsList();
    }

    interface IHomeModel{
        Flowable<BaseBean<List<Goods>>> getGoodsList();
    }

    interface IHomeView{
        void getGoodsSuccess(List<Goods> goods);
        void getGoodsError(Throwable e);
    }
}
