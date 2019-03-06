package com.bawei.thirdparty;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ImageView icon,share;
    private TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        icon=findViewById(R.id.icon);
        share=findViewById(R.id.share);
        name=findViewById(R.id.name);
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMShareAPI umShareAPI = UMShareAPI.get(MainActivity.this);
                umShareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                        String screen_name = map.get("screen_name");
                        String profile_image_url = map.get("profile_image_url");
                        name.setText(screen_name);
                        Glide.with(MainActivity.this).load(profile_image_url).into(icon);
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {

                    }
                });
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMImage umImage = new UMImage(MainActivity.this,R.drawable.umeng_socialize_qq);
                new ShareAction(MainActivity.this)
                        //分享的标题
                        .withText("你好")
                        //分享的图片
                        .withMedia(umImage)
                        //分享到哪
                        .setDisplayList(SHARE_MEDIA.QZONE,SHARE_MEDIA.QQ)
                        //设置回调
                        .setCallback(shareListener)
                        .open();
            }
        });
    }
    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         *
         */
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }
        /**
         * @descrption 分享成功的回调
         *
         */
        @Override
        public void onResult(SHARE_MEDIA share_media) {

        }
        /**
         * @descrption 分享失败的回调
         *
         */
        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {

        }
        /**
         * @descrption 分享取消的回调
         *
         */
        @Override
        public void onCancel(SHARE_MEDIA share_media) {

        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
