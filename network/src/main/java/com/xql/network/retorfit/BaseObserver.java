package com.xql.network.retorfit;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * 创建Base抽象类实现Observer
 *
 * @param <T>
 * @CreateDate: 2022/5/26 13:52
 * @UpdateUser: RyanLiu
 */
public abstract class BaseObserver<T> implements Observer<T> {
    private static final String TAG = "sansuiban";

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        Log.e(TAG, "onSubscribe: ");
    }

    @Override
    public void onNext(@NonNull T response) {
//在这边对 基础数据 进行统一处理  举个例子：
//        if(response.getRes_code()==200){
//            onSuccess(response.getDemo());
//        }else{
//            onFailure(null,response.getErr_msg());
//        }
        onSuccess(response);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        onFailure(null, e.getMessage());
        Log.e(TAG, "Throwable: " + e.getMessage());
    }

    @Override
    public void onComplete() {
        Log.e(TAG, "onComplete: ");
    }

    public abstract void onSuccess(T response);

    public abstract void onFailure(Throwable e, String errorMsg);
}
