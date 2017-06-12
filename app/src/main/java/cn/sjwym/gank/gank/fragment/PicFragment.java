package cn.sjwym.gank.gank.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.sjwym.gank.gank.R;
import cn.sjwym.gank.gank.view.MyFragmentPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class PicFragment extends Fragment {

    private View view;
    private ArrayList<String> mTitleList = new ArrayList<>(4);
    private ArrayList<Fragment> mFragments = new ArrayList<>(4);
    private Context mContext;
    private ViewPager vpGank;
    private TabLayout tabGank;


    public PicFragment(Context context) {
        // Required empty public constructor
        mContext = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_pic, container, false);
        initFragmentList();
        initView();
        MyFragmentPagerAdapter myAdapter = new MyFragmentPagerAdapter(getChildFragmentManager(), mFragments, mTitleList);
        vpGank.setAdapter(myAdapter);
        // 左右预加载页面的个数
        vpGank.setOffscreenPageLimit(3);
        myAdapter.notifyDataSetChanged();
        tabGank.setTabMode(TabLayout.MODE_FIXED);
        tabGank.setupWithViewPager(vpGank);
        return view;

    }

    private void initFragmentList() {
        mTitleList.add("Grid");
        mTitleList.add("Staggered");
        mTitleList.add("line");
        //mTitleList.add("大安卓");
        mFragments.add(new GridFragment(mContext));
        mFragments.add(new StaggeredFragment(mContext));
        mFragments.add(new LineFragment(mContext));
    }

    private void initView(){
        vpGank = (ViewPager) view.findViewById(R.id.vp_gank);
        tabGank = (TabLayout) view.findViewById(R.id.tab_gank);
    }

}
