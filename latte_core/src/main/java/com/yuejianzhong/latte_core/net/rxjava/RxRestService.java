package com.yuejianzhong.latte_core.net.rxjava;

import java.util.Map;
import java.util.Observable;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
//import io.reactivex

public interface RxRestService {
    //Observable是一个可观察对象
    @GET
    io.reactivex.Observable<String> get(@Url String url, @QueryMap Map<String,Object> params);

    @FormUrlEncoded
    @POST
    io.reactivex.Observable<String> post(@Url String url, @FieldMap Map<String,Object> params);

    @POST
    io.reactivex.Observable<String>  postRaw(@Url String url, @Body RequestBody body);

    @FormUrlEncoded
    @PUT
    io.reactivex.Observable<String> put(@Url String url, @FieldMap Map<String,Object> params);

    @PUT
    io.reactivex.Observable<String> putRaw(@Url String url, @FieldMap Map<String,Object> params);

    @DELETE
    io.reactivex.Observable<String> delete(@Url String url, @FieldMap Map<String,Object> params);

    @Streaming  //一边下载，一边写入本地，避免内存写入过大
    @GET
    io.reactivex.Observable<ResponseBody> download(@Url String url, @QueryMap Map<String,Object> params);

    @Multipart
    @POST
    io.reactivex.Observable<String> upload(@Url String url, @Part MultipartBody.Part file);
}