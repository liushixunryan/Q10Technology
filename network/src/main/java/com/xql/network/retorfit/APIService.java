package com.xql.network.retorfit;

import cn.gulu.model.prequest.PLoginBean;
import cn.gulu.model.request.LoginBean;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @ClassName: APIService
 * @Description: 接口参数
 * @CreateDate: 2022/5/26 13:52
 * @UpdateUser: RyanLiu
 */

public interface APIService {
    //    @GET("getAll?type=today&key=243cfbed804a8bd6904fb059871b2a41&consName={page}")
    //    Observable<>
    //    @POST("pi.User.getPratique.hf")
    //    @FormUrlEncoded
    //    Call<Object> getPratique(@Field("AppType") String AppType, @Field("SystemID") String SystemID, @Field("LoginKey") String LoginKey);
    //
    //    @POST("pi.User.getPratique.hf")
    //    Call<BaseResult<MassageBean>> getPratique(@Body CurrencyBean currencyBean);
    //
        @POST("pi.User.getDoTuLogin.hf")
        Observable<LoginBean> getLogin(@Body PLoginBean pLoginBean);
}