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
public class MusicFragment extends Fragment {

    private View view;
    private ArrayList<String> mTitleList = new ArrayList<>(4);
    private ArrayList<Fragment> mFragments = new ArrayList<>(4);
    private Context mContext;
    private ViewPager vpMusic;
    private TabLayout tabMusic;


    public MusicFragment(Context context) {
        // Required empty public constructor
        mContext = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_music, container, false);
        initFragmentList();
        initView();
        MyFragmentPagerAdapter myAdapter = new MyFragmentPagerAdapter(getChildFragmentManager(), mFragments, mTitleList);
        vpMusic.setAdapter(myAdapter);
        vpMusic.setOffscreenPageLimit(3);
        myAdapter.notifyDataSetChanged();
        tabMusic.setTabMode(TabLayout.MODE_FIXED);
        tabMusic.setupWithViewPager(vpMusic);
        return view;
    }

    private void initFragmentList() {
        mTitleList.add("本地音乐");
        mTitleList.add("在线音乐");
        mFragments.add(new LocalMusicFragment());
        mFragments.add(new SongListFragment());
    }

    private void initView(){
        vpMusic = (ViewPager) view.findViewById(R.id.vp_music);
        tabMusic = (TabLayout) view.findViewById(R.id.tab_music);
    }

}
