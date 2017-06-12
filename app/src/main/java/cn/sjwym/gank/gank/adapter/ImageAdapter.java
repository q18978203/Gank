package cn.sjwym.gank.gank.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import cn.sjwym.gank.gank.R;

/**
 * Created by lenovo on 2017/5/22.
 */



public class ImageAdapter extends PagerAdapter {
    private List<String> urls;
    private Context mContext;
    private ArrayList<ImageView> pagerData;

    public ImageAdapter(Context context, ArrayList<ImageView> data,List<String> urls){
        mContext = context;
        pagerData = data;
        this.urls = urls;
    }

    @Override
    public int getCount() {
        return pagerData.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        Log.i("PagerAdapter" , "method instantiateItem be calling" );
        View view = LayoutInflater.from(mContext).inflate(R.layout.popup_image , null);
        ImageView img = new ImageView(mContext);
        Picasso.with(mContext).load(urls.get(position)).into(img);
        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Log.i("PagerAdapter" , "method destroyItem be calling");
        container.removeView((View)object);
    }


}
