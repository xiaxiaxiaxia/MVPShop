package run.demo01.mvpshop.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import run.demo01.mvpshop.R;
import run.demo01.mvpshop.ui.base.BaseActivity;
import run.demo01.mvpshop.ui.car.CarFragment;
import run.demo01.mvpshop.ui.home.HomeFragment;
import run.demo01.mvpshop.ui.mine.MineFragment;

public class MainActivity extends BaseActivity {

    private FrameLayout contentArea;
    private BottomNavigationView bottomNavigationView;
    private Fragment[] fragments;
    private int nowFragment = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        contentArea = findViewById(R.id.content_area);
        bottomNavigationView = findViewById(R.id.bnv);

        //初始化Fragment
        fragments = new Fragment[]{
                new HomeFragment(),
                new CarFragment(),
                new MineFragment()
        };
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_area, fragments[0])
                .add(R.id.content_area, fragments[1])
                .add(R.id.content_area, fragments[2])
                .hide(fragments[1])
                .hide(fragments[2])
                .commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);//自己加监听需要自己去设置选中
                int itemId = item.getItemId();
                switch (itemId) {
                    case R.id.bnv_home:
                        switchFragment(0);
                        break;
                    case R.id.bnv_car:
                        switchFragment(1);
                        break;
                    case R.id.bnv_mine:
                        switchFragment(2);
                        break;
                }
                return false;
            }
        });

    }

    private void switchFragment(int to) {
        if (nowFragment == to) {
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[nowFragment]);
        if (fragments[to].isAdded()) {
            transaction.show(fragments[to]);
        } else {
            transaction.add(R.id.content_area, fragments[to]);
        }
        transaction.commit();
        nowFragment = to;
    }
}