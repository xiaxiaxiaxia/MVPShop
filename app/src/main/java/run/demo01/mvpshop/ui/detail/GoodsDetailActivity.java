package run.demo01.mvpshop.ui.detail;

import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import run.demo01.mvpshop.R;
import run.demo01.mvpshop.bean.GoodsDetail;
import run.demo01.mvpshop.ui.base.BaseActivity;

public class GoodsDetailActivity extends BaseActivity implements View.OnClickListener, DetailContract.IDetailView
{
    public static final String GOODS_ID = "goods_id";
    private Toolbar toolBar;
    private TextView detailContent;
    private DetailContract.IDetailPresenter detailPresenter;
    private ProgressDialog progressDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_detail;
    }

    @Override
    protected void initViews() {
        toolBar = findViewById(R.id.detail_toolbar);
        detailContent = findViewById(R.id.detail_content);

        //设置回退按钮事件
        toolBar.setNavigationOnClickListener(this);

        //获取商品数据
        Intent intent = getIntent();
        int id = intent.getIntExtra(GOODS_ID, 0);

        detailPresenter = new DetailPresenter(this);
        detailPresenter.getGoodsDetail(id);
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public void getDetailSuccess(GoodsDetail goodsDetail) {
        toolBar.setTitle(goodsDetail.getName());
        detailContent.setText(goodsDetail.getDescription());
    }

    @Override
    public void getDetailError(Throwable e) {

    }

    @Override
    public void showProgressBar() {
        progressDialog = new ProgressDialog(this);
        progressDialog.show();
    }

    @Override
    public void hideProgressBar() {
        progressDialog.dismiss();
    }
}
