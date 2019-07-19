package com.yuejianzhong.latte_core.net;

import android.content.Context;

import com.yuejianzhong.latte_core.net.callback.IError;
import com.yuejianzhong.latte_core.net.callback.IFailure;
import com.yuejianzhong.latte_core.net.callback.IRequest;
import com.yuejianzhong.latte_core.net.callback.ISuccess;
import com.yuejianzhong.latte_core.net.callback.RequestCallbacks;
import com.yuejianzhong.latte_core.net.download.DownloadHandler;
import com.yuejianzhong.latte_core.ui.loader.LatteLoader;
import com.yuejianzhong.latte_core.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class RestClient {
    private final String URL;
    private final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final ISuccess SUCCESS;
    private final String DOWNLOAD_DIR;
    private final RequestBody BODY;
    private LoaderStyle LOADER_STYLE;
    private final File FILE;
    private final Context CONTEXT;
    private final String EXTENSION;

    private final String NAME;

    public RestClient(String url, Map<String, Object> params, IRequest request,
                      IFailure failure, IError error, ISuccess success, RequestBody body,
                      File file, Context context, LoaderStyle loaderStyle,
                      String downloadDir,String extension,String name) {
        this.URL = url;
        this.PARAMS.putAll(params);
        this.REQUEST = request;
        this.FAILURE = failure;
        this.ERROR = error;
        this.SUCCESS = success;
        this.BODY = body;
        this.LOADER_STYLE = loaderStyle;
        this.CONTEXT = context;
        this.FILE = file;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }


    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        if (LOADER_STYLE != null) {
//            FragmentLoader.showLoading(CONTEXT, LOADER_STYLE);
            LatteLoader.showLoading(CONTEXT,LOADER_STYLE);
        }

        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL, BODY);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = service.putRaw(URL, BODY);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = RestCreator.getRestService().upload(URL, body);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            default:
                break;
        }

        if (call != null) {
            //异步执行
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(
                REQUEST,
                FAILURE,
                ERROR,
                SUCCESS,
                LOADER_STYLE
        );
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        if (BODY == null) {
            request(HttpMethod.POST);
        }else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.POST);
        }

    }

    public final void put() {
        if (BODY == null) {
            request(HttpMethod.PUT);
        }else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.PUT);
        }
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    public final void download() {
        new DownloadHandler(URL, REQUEST, DOWNLOAD_DIR, NAME, EXTENSION, SUCCESS, FAILURE, ERROR).
                handleDownload();
    }
}
