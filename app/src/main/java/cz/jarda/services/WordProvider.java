package cz.jarda.services;
import android.util.Log;

import cz.jarda.models.WordType;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.IOException;

public class WordProvider
{
    private static final String BASE_URL = "https://api.api-ninjas.com/v1/";
    private static final String API_KEY = "tLXflHaWUniI/8pNjgjk2A==mgXgWl7DO5mzYLnb";

    public class WordResponse //Model for JSON
    {
        private String word;

        public String getWord()
        {
            return word;
        }
    }

    private interface ApiService
    {
        @GET("randomword")
        Call<WordResponse> getRandomWord(@Header("x-api-key") String ApiKey, @Query("type") WordType wordType);
    }


    public LiveData<String> getRandomWordAsync(WordType wordType)
    {
        Retrofit retrofit = new Retrofit.Builder() // Retrofit -> HTTP Client
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())  //Convert JSON -> Java object
                .build();

        MutableLiveData<String> liveData = new MutableLiveData<>();
        ApiService apiService = retrofit.create(ApiService.class);   //Implementation of API Interface
        Call<WordResponse> call = apiService.getRandomWord(API_KEY, wordType);      //Java object with request. Not use yet.

        call.enqueue(new Callback<WordResponse>() //Create an anonymous class that extends the Callback<T> Z retrofit class.
        {
            @Override
            public void onResponse(Call<WordResponse> call, Response<WordResponse> response)
            {
                if (response.isSuccessful())
                {
                    liveData.postValue(response.body().getWord());
                }
                else
                {
                    try
                    {
                        liveData.postValue(response.errorBody().string());
                    }
                    catch (IOException e)  //Cause string() can return IOException
                    {
                        Log.e(WordProvider.class.getSimpleName(), "Error parsing error body", e);
                    }
                }
            }

            @Override
            public void onFailure(Call<WordResponse> call, Throwable t)
            {
                liveData.postValue(null);
                Log.e(WordProvider.class.getSimpleName(), "Call failed", t);
            }
        });

        return liveData;
    }
}