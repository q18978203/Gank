package cn.sjwym.gank.gank;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    private boolean animationEnd;
    private boolean isIn;
    private ImageView imageViewDefult;
    private ImageView imageView;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        imageViewDefult.setImageDrawable(getDrawable(R.drawable.img_transition_default));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imageViewDefult.setVisibility(View.GONE);
            }
        }, 3000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toMainActivity();
            }
        }, 1000);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMainActivity();
            }
        });
    }
    private void initView(){
        imageViewDefult = (ImageView) findViewById(R.id.iv_defult_pic);
        imageView = (ImageView) findViewById(R.id.iv_pic);
        textView = (TextView) findViewById(R.id.tv_jump);

    }


    /**
     * 实现监听跳转效果
     */
    private Animation.AnimationListener animationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationEnd(Animation animation) {
            animationEnd();
        }

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    };

    private void animationEnd() {
        synchronized (SplashActivity.this) {
            if (!animationEnd) {
                animationEnd = true;
                imageView.clearAnimation();
                toMainActivity();
            }
        }
    }

    private void toMainActivity() {
        if (isIn) {
            return;
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
        finish();
        isIn = true;
    }
}
