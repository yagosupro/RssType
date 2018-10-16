package com.example.bob_book.rsstape.Api;

import com.example.bob_book.rsstape.Model.Post;
import java.util.List;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Roman Parkhomenko on 10/15/2018.
 * Sibers company
 * yagosupro@gmail.com
 */
public interface PostsApi {

    String BASE_URL = "https://jsonplaceholder.typicode.com/";

    @GET("posts")
    Call<List<Post>> getPosts(@Query("_page") int page);

    class Factory {
        private static Retrofit retrofit;

        public static PostsApi createPostsApi() {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

            }
            return retrofit.create(PostsApi.class);
        }
    }
}

