package cn.sjwym.gank.gank.app;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.example.http.HttpUtils;

import cn.sjwym.gank.gank.utils.DebugUtil;

/**
 * Created by lenovo on 2017/5/18.
 */

public class gankApplication extends Application {
    private  static  gankApplication gankApplication;
    public static gankApplication getInstance(){
        return gankApplication;
    }

    @SuppressWarnings("unused")
    @Override
    public void onCreate() {
        super.onCreate();
        gankApplication = this;
        HttpUtils.getInstance().init(this, DebugUtil.DEBUG);

        initTextSize();
    }


    /**
     * 使其系统更改字体大小无效
     */
    private void initTextSize() {
        Resources res = getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
    }
}
