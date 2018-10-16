package com.example.bob_book.rsstape;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.bob_book.rsstape.Api.PostsApi;
import com.example.bob_book.rsstape.Model.Post;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private List<Post> postsList = new ArrayList<>();
    private PostsApi api;
    private Gson gson;
    private Call<List<Post>> postsApiCall;
    String TAG = "serviceBob";
    private LinearLayoutManager llm;
    private int numberPage = 1;
    private Adapter adapter;
    private Boolean isLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv);
        llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        setApi();
        initializeAdapter();
        loadData(numberPage);
    }

    private void loadData(int page) {
        postsApiCall = api.getPosts(page);
        postsApiCall.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                Log.i(TAG, "response ready");
                if (response.isSuccessful() && !postsApiCall.isCanceled()) {
                    Log.i(TAG, "response Succesful");
                    postsList.addAll(response.body());
                    adapter.updatePostsList(postsList);
                    Log.i(TAG, "postListSize: " + postsList.size());
                    isLoaded = false;
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.i(TAG, "response Failed");
                Log.i(TAG, t.toString());
            }
        });
    }


    private void setApi() {
        api = PostsApi.Factory.createPostsApi();
        gson = new Gson();
    }

    private void initializeAdapter() {
        adapter = new Adapter(postsList);
        rv.setAdapter(adapter);

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d(TAG, "Scroll");
                if (dy > 0) {
                    int lastVisibleItemPosition = llm.findLastVisibleItemPosition();
                    Log.d(TAG, "lastVisibleItemPosition: " + lastVisibleItemPosition);
                    Log.d(TAG, "postsList.size(): " + postsList.size());
                    if ((lastVisibleItemPosition > postsList.size() - 2) && !isLoaded) { // ArrayList<NewsModel> list;
                        isLoaded = true;
                        numberPage++; //номер страницы откуда нужно парсить, перваоначальное значение было 1.
                        Log.i(TAG, "number page: " + numberPage);
                        loadData(numberPage); //парсинг и сохранение новостей со следующей страницы
                    }
                }
            }
        });
    }


}
