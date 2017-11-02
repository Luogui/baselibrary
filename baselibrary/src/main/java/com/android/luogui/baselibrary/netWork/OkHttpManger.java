//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.android.luogui.baselibrary.netWork;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.android.luogui.baselibrary.netWork.bean.NetElement;
import com.android.luogui.baselibrary.util.LogUtil;
import com.facebook.stetho.okhttp.StethoInterceptor;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.Request.Builder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class OkHttpManger {

    private OkHttpClient mOkHttpClient = new OkHttpClient();
    private static OkHttpManger instance;
    private Handler mDelivery;
    private Map<Object, List<Call>> callListMap = new ConcurrentHashMap();

    private OkHttpManger() {
        this.mOkHttpClient.networkInterceptors().add(new StethoInterceptor());
        this.mDelivery = new Handler(Looper.getMainLooper());
    }

    public static OkHttpManger getInstance() {
        if(instance == null) {
            Class var0 = OkHttpManger.class;
            synchronized(OkHttpManger.class) {
                if(instance == null) {
                    instance = new OkHttpManger();
                }
            }
        }

        return instance;
    }

    public OkHttpBuilder getBuilder() {
        return new OkHttpBuilder();
    }

    private void saveCall(Object key, Call call) {
        if(null != key) {
            List callList = (List)this.callListMap.get(key);
            if(null == callList) {
                LinkedList callList1 = new LinkedList();
                callList1.add(call);
                this.callListMap.put(key, callList1);
            } else {
                callList.add(call);
            }
        }

    }

    private void removeCall(Object key, Call call) {
        if(null != key) {
            List callList = (List)this.callListMap.get(key);
            if(null != callList) {
                callList.remove(call);
            }
        }

    }

    public void cancelCall(Object key) {
        if(null != key) {
            List callList = (List)this.callListMap.get(key);
            if(null != callList) {
                Iterator var3 = callList.iterator();

                while(var3.hasNext()) {
                    Call c = (Call)var3.next();
                    c.cancel();
                }
            }

            this.callListMap.remove(key);
        }

    }

    public void executeGet(String url, OkHttpBuilder builder, ResultCallback callback) {
        StringBuilder urlWithParam = new StringBuilder();
        urlWithParam.append(url);
        urlWithParam.append("?");
        Iterator var5 = builder.elementList.iterator();

        while(var5.hasNext()) {
            NetElement element = (NetElement)var5.next();
            urlWithParam.append(element.parseValue());
        }

        urlWithParam.deleteCharAt(urlWithParam.length() - 1);
        this.enqueueGet(urlWithParam.toString(), callback);
    }

    public void executeJsonPost(String url, OkHttpBuilder httpBuilder, ResultCallback callback) {
        try {
            JSONObject e = new JSONObject();
            StringBuilder stringBuilder = new StringBuilder();
            Iterator result = httpBuilder.elementList.iterator();

            while(result.hasNext()) {
                NetElement body = (NetElement)result.next();
                body.parseJSON(e);
                stringBuilder.append(body.toString() + "  \n");
            }

            LogUtil.v("executeStart", stringBuilder.toString());
            String result1 = e.toString();
            result1 = httpBuilder.encrppt(result1);
            RequestBody body1 = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), result1);
            Builder builder = new Builder();
            builder.url(url);
            builder.post(body1);
            if(httpBuilder.heads != null) {
                Iterator var9 = httpBuilder.heads.keySet().iterator();

                while(var9.hasNext()) {
                    String key = (String)var9.next();
                    Iterator var11 = ((List)httpBuilder.heads.get(key)).iterator();

                    while(var11.hasNext()) {
                        String value = (String)var11.next();
                        builder.addHeader(key, value);
                    }
                }
            }

            builder.tag(httpBuilder.getBindParam());
            this.enqueuePost(builder.build(), callback);
        } catch (JSONException var13) {
            LogUtil.t(var13);
        }

    }

    public void enqueueFormPost(String url, OkHttpBuilder okHttpBuilder, ResultCallback callback) {
        MultipartBuilder multipartBuilder = (new MultipartBuilder()).type(MultipartBuilder.FORM);
        StringBuilder stringBuilder = new StringBuilder();
        Iterator builder = okHttpBuilder.elementList.iterator();

        while(builder.hasNext()) {
            NetElement element = (NetElement)builder.next();
            element.parseFormat(multipartBuilder);
            stringBuilder.append(element.toString() + "  \n");
        }

        LogUtil.v("executeStart", stringBuilder.toString());
        Builder builder1 = (new Builder()).url(url).post(multipartBuilder.build());
        if(okHttpBuilder.heads != null) {
            Iterator element1 = okHttpBuilder.heads.keySet().iterator();

            while(element1.hasNext()) {
                String key = (String)element1.next();
                Iterator var9 = ((List)okHttpBuilder.heads.get(key)).iterator();

                while(var9.hasNext()) {
                    String value = (String)var9.next();
                    builder1.addHeader(key, value);
                }
            }
        }

        builder1.tag(okHttpBuilder.getBindParam());
        this.enqueuePost(builder1.build(), callback);
    }

    public String executePostFile(String url, File file) throws IOException {
        RequestBody body = RequestBody.create(MediaType.parse(this.guessMimeType(file.getName())), file);
        RequestBody requestBody = (new MultipartBuilder()).type(MultipartBuilder.FORM).addPart(Headers.of(new String[]{"Content-Disposition", "form-data; name=\"attach\";filename=\"" + file.getName() + "\";"}), body).build();
        Request request = (new Builder()).url(url).post(requestBody).build();
        Response response = this.mOkHttpClient.newCall(request).execute();
        return response.body().string();
    }

    public void enqueueDownloadFile(String url, Callback callback) throws IOException {
        Request request = (new Builder()).url(url).build();
        this.mOkHttpClient.newCall(request).enqueue(callback);
    }

    private String guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if(contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }

        return contentTypeFor;
    }

    public void enqueueGet(String url, ResultCallback callback) {
        Builder builder = new Builder();
        builder.url(url);
        this.enqueuePost(builder.build(), callback);
    }

    public Response executeGet(String url) throws IOException {
        Builder builder = new Builder();
        builder.url(url);
        Response response = this.mOkHttpClient.newCall(builder.build()).execute();
        return response;
    }

    public void enqueueFilePost(final String url, @NonNull final FileCallBack callback) {
        Builder builder = new Builder();
        builder.url(url);
        this.mOkHttpClient.newCall(builder.build()).enqueue(new Callback() {
            public void onFailure(Request request, IOException e) {
                callback.onFailure(request, e);
            }

            public void onResponse(Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                int po = url.lastIndexOf("/");
                String fileName = url.substring(po + 1);
                String file_dir = OkHttpManger.this.getFilePath();
                File dir = new File(file_dir);
                if(!dir.exists()) {
                    dir.mkdirs();
                }

                File file = new File(file_dir + fileName);
                if(!file.exists()) {
                    file.delete();
                }

                file.createNewFile();
                FileOutputStream fos = new FileOutputStream(file);
                byte[] bytes = new byte[4096];
                boolean n = true;
                int n1;
                if((n1 = bufferedInputStream.read(bytes)) != -1) {
                    fos.write(bytes, 0, n1);
                }

                fos.close();
                bufferedInputStream.close();
                inputStream.close();
                callback.onFileSave(file.getAbsolutePath());
            }
        });
    }

    private String getFilePath() {
        String file_dir = "";
        boolean isRootDirExist = Environment.getExternalStorageDirectory().exists();
        if(isRootDirExist) {
            file_dir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/apk/";
        } else {
            file_dir = "/data/data/com.facebook.stetho.okhttp/files/apk/";
        }

        return file_dir;
    }

    private void enqueuePost(Request req, final ResultCallback callback) {
        final Call call = this.mOkHttpClient.newCall(req);
        final Object key = req.tag();
        if(req != key) {
            this.saveCall(key, call);
        }

        call.enqueue(new Callback() {
            public void onFailure(Request request, IOException e) {
                OkHttpManger.this.sendFailedStringCallback(request, e, callback);
                OkHttpManger.this.removeCall(key, call);
            }

            public void onResponse(Response response) throws IOException {
                OkHttpManger.this.sendSuccessResultCallback(response.body().string(), callback);
                OkHttpManger.this.removeCall(key, call);
            }
        });
    }

    private void sendSuccessResultCallback(final String object, final ResultCallback callback) {
        this.mDelivery.post(new Runnable() {
            public void run() {
                if(callback != null) {
                    callback.onSuccess(object);
                }

            }
        });
    }

    private void sendFailedStringCallback(final Request request, final Exception e, final ResultCallback callback) {
        this.mDelivery.post(new Runnable() {
            public void run() {
                if(callback != null) {
                    callback.onFailure(request, e);
                }

            }
        });
    }

    public abstract static class FileCallBack {
        public FileCallBack() {
        }

        public abstract void onFileSave(String var1);

        public abstract void onFailure(Request var1, Exception var2);
    }

    public abstract static class ResultCallback {
        public ResultCallback() {
        }

        public abstract void onFailure(Request var1, Exception var2);

        public abstract void onSuccess(String var1);
    }
}
