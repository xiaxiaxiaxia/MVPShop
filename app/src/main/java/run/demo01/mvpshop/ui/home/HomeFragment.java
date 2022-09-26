package run.demo01.mvpshop.ui.home;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import run.demo01.mvpshop.R;
import run.demo01.mvpshop.bean.Goods;
import run.demo01.mvpshop.ui.base.BaseFragment;
import run.demo01.mvpshop.ui.detail.GoodsDetailActivity;
import run.demo01.mvpshop.ui.home.adapter.HomeRecyclerViewAdapter;
import run.demo01.mvpshop.ui.home.adapter.HomeSpanSizeLookup;

public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, HomeContract.IHomeView, HomeRecyclerViewAdapter.OnItemClickListener {
    private RecyclerView recycleView;
    private SwipeRefreshLayout swipRefresh;
    private HomeRecyclerViewAdapter adapter;
    private HomeContract.IHomePresenter homePresenter;
    private HomeSpanSizeLookup spanSizeLookup;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews() {
        swipRefresh = find(R.id.home_swiprefresh);
        swipRefresh.setOnRefreshListener(this);
        recycleView = find(R.id.home_recycleview);

        //测试商品数据
        List<Goods> goods = new ArrayList<>();

        //设置布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        spanSizeLookup = new HomeSpanSizeLookup(goods);
        gridLayoutManager.setSpanSizeLookup(spanSizeLookup);
        recycleView.setLayoutManager(gridLayoutManager);

        //设置适配器
        adapter = new HomeRecyclerViewAdapter(recycleView,getContext(), goods);
        recycleView.setAdapter(adapter);

        adapter.setOnItemClickListener(this);

        //设置MVP接口
        homePresenter = new HomePresenter(this);
        homePresenter.getGoodsList();
    }


    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        homePresenter.getGoodsList();
    }

    @Override
    public void getGoodsSuccess(List<Goods> goods) {
        spanSizeLookup.setData(goods);
        adapter.setData(goods);
        swipRefresh.setRefreshing(false);
    }

    @Override
    public void getGoodsError(Throwable e) {

    }

    /**
     * 商品列表点击事件
     * @param goods
     */
    @Override
    public void onItemClick(Goods goods) {
        Intent it = new Intent(getContext(), GoodsDetailActivity.class);
        it.putExtra(GoodsDetailActivity.GOODS_ID,goods.getGoodsId());
        startActivity(it);
    }
}
