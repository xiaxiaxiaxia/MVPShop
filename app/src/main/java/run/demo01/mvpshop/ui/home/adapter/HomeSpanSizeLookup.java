package run.demo01.mvpshop.ui.home.adapter;

import androidx.recyclerview.widget.GridLayoutManager;

import java.util.List;

import run.demo01.mvpshop.bean.Goods;

public class HomeSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {
    private List<Goods> data;

    public  HomeSpanSizeLookup(List<Goods> data) {
        this.data = data;
    }
    @Override
    public int getSpanSize(int position) {
        return data.get(position).getSpanSize();
    }

    public void setData(List<Goods> goods) {
        this.data.clear();
        this.data.addAll(goods);
    }
}
