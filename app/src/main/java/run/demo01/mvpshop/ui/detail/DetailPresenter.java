package run.demo01.mvpshop.ui.detail;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import run.demo01.mvpshop.bean.BaseBean;
import run.demo01.mvpshop.bean.GoodsDetail;

public class DetailPresenter implements DetailContract.IDetailPresenter {
    private final DetailContract.IDetailView detailView;
    private final DetailContract.IDetailModel detalModel;

    public DetailPresenter(DetailContract.IDetailView detailView)
    {
        this.detailView = detailView;
        this.detalModel = new DetailModel();
    }
    @Override
    public void getGoodsDetail(int id) {
        detailView.showProgressBar();
        detalModel.getGoodsDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean<GoodsDetail>>() {
                    @Override
                    public void accept(BaseBean<GoodsDetail> goodsDetailBaseBean) throws Throwable {
                        detailView.getDetailSuccess(goodsDetailBaseBean.getData());
                        detailView.hideProgressBar();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        detailView.getDetailError(throwable);
                        detailView.hideProgressBar();
                    }
                });
    }
}
