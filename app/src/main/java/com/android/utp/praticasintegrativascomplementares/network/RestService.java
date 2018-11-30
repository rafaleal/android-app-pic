package com.android.utp.praticasintegrativascomplementares.network;

import com.android.utp.praticasintegrativascomplementares.models.pic.GetPICResponse;
import com.android.utp.praticasintegrativascomplementares.models.ubs.GetUBSResponse;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

public interface RestService {

    @Multipart
    @POST("/ubs/get_ubs_por_distrito.php")
    Call<GetUBSResponse> getUbs(@PartMap Map<String, RequestBody> params);

    @POST("/pic/get_pics.php")
    Call<GetPICResponse> getPics();
}
