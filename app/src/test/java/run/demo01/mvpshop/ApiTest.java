package run.demo01.mvpshop;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import io.reactivex.rxjava3.functions.Consumer;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import run.demo01.mvpshop.bean.BaseBean;
import run.demo01.mvpshop.bean.Goods;
import run.demo01.mvpshop.network.RetrofitClient;
import run.demo01.mvpshop.network.service.GoodsService;

public class ApiTest {
    @Test
    public void goodsListTest()
    {
        GoodsService goodsService = RetrofitClient.getInstance().getService(GoodsService.class);
        goodsService.getGoods().subscribe(new Consumer<BaseBean<List<Goods>>>() {
            @Override
            public void accept(BaseBean<List<Goods>> listBaseBean) throws Throwable {
                System.out.println(listBaseBean);
            }
        });
        while(true){

        }
    }

    @Test
    public void retrofitTest()
    {
        try {
            Response response = new OkHttpClient.Builder().build().newCall(new Request.Builder().url("http://edu-lance.github.io/goods_list").build()).execute();
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
