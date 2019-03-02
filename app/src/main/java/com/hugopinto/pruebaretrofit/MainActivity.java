package com.hugopinto.pruebaretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hugopinto.pruebaretrofit.Interfaz.JsonPlaceHolderApi;
import com.hugopinto.pruebaretrofit.Models.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.datos);
        getPosts();
    }

    private void getPosts(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Posts>> call = jsonPlaceHolderApi.getPosts();
        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if(!response.isSuccessful()){
                    tv.setText("Codigo: "+response.code());
                    return;
                }
                List<Posts> postsList = response.body();
                for(Posts posts:postsList){
                    String content ="";
                    content += "userId: "+ posts.getUserId()+"\n";
                    content += "id: "+ posts.getId()+"\n";
                    content += "title: "+ posts.getTitle()+"\n";;
                    content += "body: "+ posts.getBody()+"\n\n";
                    tv.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                tv.setText(t.getMessage());
            }
        });
    }
}
