package com.example.interviewpractice;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.example.interviewpractice.ui.baseView.BaseActivity;
import com.example.interviewpractice.ui.fragment.DiscoverFragment;
import com.example.interviewpractice.ui.fragment.ForumFragment;
import com.example.interviewpractice.ui.fragment.HomePageFragment;
import com.example.interviewpractice.ui.fragment.MineFragment;
import com.example.interviewpractice.utils.helper.BottomNavigationViewHelper;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayer;


public class MainActivity extends BaseActivity {
    @BindView(R.id.bottomnavgationview)
    BottomNavigationView mBottomNavigationBar;
    private Fragment mFragment,homepageFragemnt,discoverFragement,forumFragment,mineFragment;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verifyStoragePermissions(this);
        //测试
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
//        }
        QMUIStatusBarHelper.translucent(this); // 沉浸式状态栏
        ButterKnife.bind( this ) ;
        init();
        initmBottomNavigationBar();
        //取消动画效果
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationBar);
        //初始化添加fragment
        FragmentManager fragmentManage = getSupportFragmentManager();
        fragmentManage.beginTransaction().add(R.id.fg,homepageFragemnt).commit();
        mFragment=homepageFragemnt;
    }
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }
    }
    /**
     * BottomNavigationView的item点击切换Fragment
     */
    private void initmBottomNavigationBar() {
        mBottomNavigationBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homepage:
                        switchFragment(homepageFragemnt);
                        break;
                    case R.id.discover:
                        switchFragment(discoverFragement);
                    break;
                    case R.id.forum:
                        switchFragment(forumFragment);
                        break;
                    case R.id.mine:
                        switchFragment(mineFragment);
                        break;
                }
                return true;
            }
        });
        mBottomNavigationBar.getMenu().getItem(0).setChecked(true);
    }

    /**
     * 初始化布局页面
     */
    private void init() {
        homepageFragemnt=new HomePageFragment();
        discoverFragement=new DiscoverFragment();
        forumFragment=new ForumFragment();
        mineFragment=new MineFragment();
    }

    /** 判断当前显示的Fragment是不是切换的Fragment
     *  如果没有，则先把当前的Fragment隐藏，把切换的Fragment添加上
     *   如果已经添加过，则先把当前的Fragment隐藏，把切换的Fragment显示出来
     * @param fragment
     */
    private void switchFragment(Fragment fragment) {
        if (mFragment != fragment) {
            if (!fragment.isAdded()) {
                getSupportFragmentManager().beginTransaction().hide(mFragment).add(R.id.fg, fragment).commit();
            } else {
                getSupportFragmentManager().beginTransaction().hide(mFragment).show(fragment).commit();
            }
            mFragment = fragment;
        }
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        JZVideoPlayer.releaseAllVideos();
        super.onPause();
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//
//        return super.onKeyDown(keyCode, event);
//    }

    @Override
    protected int getContextViewId() {
        return R.id.qmuidemo;
    }
}
