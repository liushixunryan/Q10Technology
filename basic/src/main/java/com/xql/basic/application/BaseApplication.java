package com.xql.basic.application;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.baidu.techain.ac.TH;
import com.xql.arouter.ARouter;
import com.xql.basic.constans.Constans;
import com.xql.common.FixDexUtil;

/**
 * @Author: lsx
 * @Date: 2022/6/10
 * @Description: 全局Application
 */

public class BaseApplication extends Application {
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 配置一个全局context
         */
        context = getApplicationContext();
        /**
         * Arouter
         */
        //arouter注册
        ARouter.getInstance().init(this);
        /**
         *MultiDex
         */
        // Dex文件超出限制
        MultiDex.install(this);
        /**
         * 热修复
         */
        //开启热修复
        FixDexUtil.startRepair(getApplicationContext());
        /**
         *百度智能云消息推送
         */
        //同意协议(不同意不让用) true 同意 false 不同意
        TH.setAgreePolicy(getApplicationContext(), true);
        //初始化推送
        TH.init(getApplicationContext(), Constans.APPKEY, Constans.SECKEY, 100028, 100019);
    }
}
