package com.xql.basic.viewmodel;

import androidx.lifecycle.ViewModel;

/**
 * @ClassName: BasicViewModel
 * @Description: 通用vm
 * @CreateDate: 2022/5/26 10:26
 * @UpdateUser: RyanLiu
 */

public class BaseViewModel extends ViewModel {
    protected final String TAG = "sansuiban";
    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
