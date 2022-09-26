package run.demo01.mvpshop.ui.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import run.demo01.mvpshop.R;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.Theme_MVPShop);
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initViews();
    }
    protected abstract int getLayoutId();
    protected abstract void initViews();
}
