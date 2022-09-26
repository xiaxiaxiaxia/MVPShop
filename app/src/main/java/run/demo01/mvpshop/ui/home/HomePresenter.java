package run.demo01.mvpshop.ui.home;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import run.demo01.mvpshop.bean.BaseBean;
import run.demo01.mvpshop.bean.Goods;

public class HomePresenter implements HomeContract.IHomePresenter{

    private final HomeContract.IHomeModel homeModel;
    private final HomeContract.IHomeView homeView;

    public HomePresenter(HomeContract.IHomeView homeView)
    {
        this.homeView = homeView;
        homeModel = new HomeModel();
    }

    @Override
    public void getGoodsList() {
        homeModel.getGoodsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean<List<Goods>>>() {
                    //获取数据成功
                    @Override
                    public void accept(BaseBean<List<Goods>> listBaseBean) throws Throwable {
                        homeView.getGoodsSuccess(listBaseBean.getData());
                    }
                }, new Consumer<Throwable>() {
                    //获取数据失败
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        homeView.getGoodsError(throwable);
                    }
                });
    }
}
