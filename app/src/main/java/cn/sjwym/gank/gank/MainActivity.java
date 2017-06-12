package cn.sjwym.gank.gank;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import cn.sjwym.gank.gank.fragment.MusicFragment;
import cn.sjwym.gank.gank.fragment.PicFragment;
import cn.sjwym.gank.gank.utils.CommonUtils;
import cn.sjwym.gank.gank.view.MyFragmentPagerAdapter;
import cn.sjwym.gank.gank.view.statusbar.StatusBarUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener, NavigationView.OnNavigationItemSelectedListener {
    private ImageView llTitleGank;
    private ImageView llTitleOne;

    private NavigationView navView;
    private DrawerLayout drawerLayout;
    private ViewPager vpContent;
    private FrameLayout llTitleMenu;
    private Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initStatusView();
        initId();
        //沉浸式状态栏
//        StatusBarUtil.setColorNoTranslucentForDrawerLayout(MainActivity.this, drawerLayout,
//        CommonUtils.getColor(R.color.colorTheme));
        initContentFragment();
        initListener();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    //初始化状态栏
    private void initStatusView() {
        ViewGroup.LayoutParams layoutParams = findViewById(R.id.view_status).getLayoutParams();
        layoutParams.height = StatusBarUtil.getStatusBarHeight(this);
        findViewById(R.id.view_status).setLayoutParams(layoutParams);
    }

    //初始化view
    private void initId(){
        navView = (NavigationView) findViewById(R.id.nav_view);
        llTitleGank = (ImageView) findViewById(R.id.iv_title_gank);
        llTitleOne = (ImageView) findViewById(R.id.iv_title_one);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        vpContent = (ViewPager) findViewById(R.id.vp_content);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        llTitleMenu = (FrameLayout) findViewById(R.id.ll_title_menu);
    }

    private void initContentFragment() {
        ArrayList<Fragment> mFragmentList = new ArrayList<>();
        mFragmentList.add(new PicFragment(MainActivity.this));
        mFragmentList.add(new MusicFragment(MainActivity.this));
        // 注意使用的是：getSupportFragmentManager
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragmentList);
        vpContent.setAdapter(adapter);
        // 设置ViewPager最大缓存的页面个数(cpu消耗少)
        vpContent.setOffscreenPageLimit(2);
        vpContent.addOnPageChangeListener(this);
        llTitleGank.setSelected(true);
        vpContent.setCurrentItem(0);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }
    //初始化listener
    private void initListener() {
        llTitleGank.setOnClickListener(this);
        llTitleOne.setOnClickListener(this);
        llTitleMenu.setOnClickListener(this);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                llTitleGank.setSelected(true);
                llTitleOne.setSelected(false);
                break;
            case 1:
                llTitleOne.setSelected(true);
                llTitleGank.setSelected(false);
                break;
            case 2:
                llTitleOne.setSelected(false);
                llTitleGank.setSelected(false);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_title_menu:// 开启菜单
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.iv_title_gank:// 干货栏
                //if (vpContent.getCurrentItem() != 0) {//不然cpu会有损耗
                if (1==1){
                    llTitleGank.setSelected(true);
                    llTitleOne.setSelected(false);
                    vpContent.setCurrentItem(0);
                }
                break;
            case R.id.iv_title_one:// 音乐栏
                if (vpContent.getCurrentItem() != 1) {
                    llTitleOne.setSelected(true);
                    llTitleGank.setSelected(false);
                    vpContent.setCurrentItem(1);
                }
                break;
            default:
                break;
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.ll_nav_exit) {
            finish();//退出应用
        }

        //收起DrawerLayout
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
