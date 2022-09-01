package com.xql.basic.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.xql.basic.viewmodel.BaseViewModel;
import com.xql.loading.LoadingDialog;
import com.xql.loading.TipDialog;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @ClassName: BasicFragment
 * @Description: fragment通用类
 * @CreateDate: 2022/5/26 10:54
 * @UpdateUser: RyanLiu
 */

public abstract class BaseFragment<B extends ViewDataBinding, VM extends BaseViewModel> extends Fragment {
    //获取TAG的fragment名称
    protected final String TAG = "sansuiban";
    protected B mBinding;
    protected VM mViewModel;
    public Context context;
    // 等待窗对象
    private LoadingDialog loadingDialog;
    // 提示窗对象
    private TipDialog tipDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, layoutId(), container, false);
        mBinding.setLifecycleOwner(getActivity());
        //初始化各种dialog
        initDialog();
        //创建ViewModel。
        createViewModel();
        initData(context);
        return mBinding.getRoot();
    }

    /**
     * 绑定viewmodel
     */
    public void createViewModel() {
        if (mViewModel == null) {
            Class modelClass;
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[1];
                Log.i(TAG, "createViewModel: " + modelClass.toString());
            } else {
                //如果没有指定泛型参数，则默认使用BaseViewModel
                modelClass = BaseViewModel.class;
            }
            mViewModel = (VM) new ViewModelProvider(getActivity(), new ViewModelProvider.NewInstanceFactory()).get(modelClass);
        }
    }

    /**
     * 初始化各种Dialog
     */
    private void initDialog() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(context);
        }

        if (tipDialog == null) {
            tipDialog = new TipDialog(context);
        }
    }

    /**
     * 显示等待Dialog
     */
    public void showLoading() {
        if (loadingDialog != null && !loadingDialog.isShowing())
            loadingDialog.show();
    }

    /**
     * 显示等待Dialog，可自定义显示内容
     */
    public LoadingDialog showLoading(String msg) {
        if (loadingDialog != null && !loadingDialog.isShowing())
            loadingDialog.setMessage(msg).show();
        return loadingDialog;
    }

    /**
     * 显示提示类型Dialog
     */
    public TipDialog showTipDialog(String msg, boolean isShowCancel, TipDialog.SubmitListener l) {

        if (tipDialog != null && !tipDialog.isShowing()) {

            tipDialog.setMessage(msg).isShowCancel(isShowCancel).setSubmitListener(l).show();
        } else if (tipDialog != null && tipDialog.isShowing()) {
            tipDialog.setMessage(msg);
        }
        return tipDialog;
    }

    /**
     * 隐藏等待Dialog
     */
    public void hideLoading() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    /**
     * 隐藏提示类型Dialog
     */
    public void hideTipDialog() {
        if (tipDialog != null && tipDialog.isShowing()) {
            tipDialog.dismiss();
        }
    }

    /**
     * 初始化布局
     *
     * @return 布局id
     */
    protected abstract int layoutId();

    /**
     * 初始化，绑定数据
     *
     * @param context 上下文
     */
    protected abstract void initData(Context context);

    /**
     * 保证同一按钮在1秒内只响应一次点击事件
     */
    public abstract static class OnSingleClickListener implements View.OnClickListener {
        //两次点击按钮的最小间隔，目前为1000
        private static final int MIN_CLICK_DELAY_TIME = 1000;
        private long lastClickTime;

        public abstract void onSingleClick(View view);

        @Override
        public void onClick(View v) {
            long curClickTime = System.currentTimeMillis();
            if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
                lastClickTime = curClickTime;
                onSingleClick(v);
            }
        }
    }

    /**
     * 同一按钮在短时间内可重复响应点击事件
     */
    public abstract static class OnMultiClickListener implements View.OnClickListener {
        public abstract void onMultiClick(View view);

        @Override
        public void onClick(View v) {
            onMultiClick(v);
        }
    }

}
