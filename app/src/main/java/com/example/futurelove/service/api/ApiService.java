package com.example.futurelove.service.api;

import com.example.futurelove.model.Comment;
import com.example.futurelove.model.CommentDto;
import com.example.futurelove.model.EventHomeDto;
import com.example.futurelove.model.ResponsePairingDto;

import java.util.List;
import java.util.Map;

import retrofit2.Call;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
//    Gson gson = new GsonBuilder()
//            .setDateFormat("yyyy-MM-dd HH:mm:ss")
//            .create();
//
//        Interceptor interceptor = chain -> {
//            Request request = chain.request();
//            Request.Builder  builder = request.newBuilder();
//            return chain.proceed(builder.build());
//        };
//
//        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder().addInterceptor(interceptor)
//                .callTimeout(50000,TimeUnit.MILLISECONDS) // Timeout cho toàn bộ cuộc gọi (bao gồm cả kết nối và đọc/phản hồi)
//        .connectTimeout(50000, TimeUnit.MILLISECONDS);
//        ApiService apiService = new Retrofit.Builder()
//            .baseUrl(Server.DOMAIN)
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .client(okBuilder.build())
//            .build()
//            .create(ApiService.class);




    @GET(Server.URI_PAIRING)
    Call<ResponsePairingDto> postEvent(@HeaderMap Map<String, String> headers);
    @GET(Server.URI_LIST_EVENT_HOME+"{page}")
    Call<List<List<EventHomeDto>>> getListAllEventHome(@Path("page") long id);
    @GET(Server.URI_LIST_EVENT_TIMELINE+ "{id}")
    Call<List<EventHomeDto>> getListEventDetail(@Path("id") long id);

    @GET(Server.URI_LIST_COMMENT_BY_EVENT_ID +"{id}")
    Call<List<Comment>> getListCommentByEventId(@Path("id") long id);

    @GET(Server.URI_LIST_COMMENT_NEW +"{id}")
    Call<CommentDto> getListCommentNew(@Path("id") int id);

    @POST(Server.URI_POST_COMMENT)
    Call<Object> postComment(@HeaderMap Map<String, String> headers);

    @FormUrlEncoded
    @POST(Server.URI_POST_COMMENT)
    Call<Object> postDataComment(@Field("noi_dung_cmt") String content,
                          @Field("device_cmt") String device,
                          @Field("id_toan_bo_su_kien") String idSummary,
                          @Field("ipComment") String ip,
                          @Field("imageattach") String imagEattach);





}

