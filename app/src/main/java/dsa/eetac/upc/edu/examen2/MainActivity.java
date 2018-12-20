package dsa.eetac.upc.edu.examen2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DibaAPI APIservice;
    ProgressBar pb1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        createAPIService();
        getCities();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void createAPIService() {
        Gson gson = new GsonBuilder()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIservice.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APIservice = retrofit.create(DibaAPI.class);
    }
    private void getCities(){
        DibaAPI dibaAPI = APIservice;
        Call <Cities> callCitiesList = dibaAPI.cities("1","11");
        callCitiesList.enqueue(new Callback<Cities>() {
            @Override
            public void onResponse(Call<Cities> call, Response<Cities> response) {
                int statusCode = response.code();
                if(response.isSuccessful()){
                    Cities cities = response.body();

                    recyclerView.setAdapter(new CitiesRecyclerViewAdapter(cities.getElements()));
                }
            }

            @Override
            public void onFailure(Call<Cities> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    retrofit2.Callback<List<Element>> Callback = new Callback<List<Element>>() {
        @Override
        public void onResponse(Call<List<Element>> call, Response<List<Element>> response) {
            if (response.isSuccessful()) {
                List<Element> data = new ArrayList<>();
                data.addAll(response.body());
                recyclerView.setAdapter(new CitiesRecyclerViewAdapter(data));
                //List<Track> Tracks = response.body();
                //ArrayAdapter<Track> arrayAdapter = new ArrayAdapter<Track>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, Tracks);
                //TracksSpinner.setAdapter(arrayAdapter);
            } else {
                Log.d("TracksCallback", "Code: " + response.code() + " Message: " + response.message());
            }
        }

        @Override
        public void onFailure(Call<List<Element>> call, Throwable t) {
            t.printStackTrace();
        }
    };
}
