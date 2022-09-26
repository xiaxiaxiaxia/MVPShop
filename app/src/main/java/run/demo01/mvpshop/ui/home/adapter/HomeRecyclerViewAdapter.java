package run.demo01.mvpshop.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.List;

import run.demo01.mvpshop.R;
import run.demo01.mvpshop.bean.Goods;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private LayoutInflater inflater;
    private RecyclerView recyclerView;
    private Context context;
    private List<Goods> data;
    private OnItemClickListener onItemClickListener;

    public HomeRecyclerViewAdapter(RecyclerView recyclerView,Context context, List<Goods> goods) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.data = goods;
        inflater = LayoutInflater.from(context);
    }

    /**
     * 刷新时商品列表改变调用的方法
     *
     * @param data
     */
    public void setData(List<Goods> data) {
        if (data == null)
            return;
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        Goods goods = this.data.get(position);
        if (goods.getBanners() != null) {
            //Banner信息
            return R.layout.home_recycler_banner;
        } else if (goods.getImageUrl() == null && goods.getText() != null) {
            //显示文字
            return R.layout.home_recycler_text;
        } else if (goods.getText() == null && goods.getImageUrl() != null) {
            //显示图片
            return R.layout.home_recycler_image;
        } else {
            //文字加图片
            return R.layout.home_recycler_image_and_text;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(viewType, parent, false);
        view.setOnClickListener(this);
        if (viewType == R.layout.home_recycler_image_and_text) {
            return new MultiViewHolder(view);
        } else {
            return new SingleViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        Goods goods = this.data.get(position);
        switch (viewType) {
            case R.layout.home_recycler_image_and_text:
                MultiViewHolder multiViewHolder = (MultiViewHolder) holder;
                //加载图片
                Glide.with(holder.itemView)
                        .load(goods.getImageUrl())
                        .apply(RequestOptions.centerCropTransform())
                        .into(multiViewHolder.iv);
                //设置文字
                multiViewHolder.tv.setText(goods.getText());
                break;
            case R.layout.home_recycler_text:
                ((TextView)holder.itemView).setText(goods.getText());
                break;
            case R.layout.home_recycler_image:
                Glide.with(holder.itemView)
                        .load(goods.getImageUrl())
                        .apply(RequestOptions.centerCropTransform())
                        .into((ImageView) holder.itemView);
                break;
            case R.layout.home_recycler_banner:
                ((Banner) holder.itemView).setAdapter(new BannerImageAdapter<String>(goods.getBanners()) {
                            @Override
                            public void onBindView(BannerImageHolder holder, String data, int position, int size) {
                                //加载图片
                                Glide.with(holder.itemView)
                                        .load(data)
                                        .apply(RequestOptions.centerCropTransform())
                                        .into(holder.imageView);
                            }
                        })
                        .addBannerLifecycleObserver((LifecycleOwner) context)
                        //指示器（小圆点）
                        .setIndicator(new CircleIndicator(context));
                break;
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener == null)
            return;
        int position = recyclerView.getChildAdapterPosition(v);
        onItemClickListener.onItemClick(data.get(position));
    }

    /**
     * 点击事件接口
     */
    public interface OnItemClickListener{
        void onItemClick(Goods goods);
    }


    @Override
    public int getItemCount() {
        if (data != null)
            return data.size();
        return 0;
    }

    class SingleViewHolder extends RecyclerView.ViewHolder {
        public SingleViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    class MultiViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView iv;

        public MultiViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.text);
            iv = itemView.findViewById(R.id.image);
        }
    }
}
