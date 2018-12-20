package dsa.eetac.upc.edu.examen2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DibaAPI {
    String BASE_URL = "https://do.diba.cat/api/dataset/municipis/format/json/pag-ini/1/pag-fi/";

    @GET("pag-ini/{num1}/pag-fi/{num2}")
    Call<Cities> cities(@Path("num1") String num1, @Path("num2") String num2);
}
