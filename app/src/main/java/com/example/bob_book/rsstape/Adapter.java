package com.example.bob_book.rsstape;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.bob_book.rsstape.Model.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Roman Parkhomenko on 10/15/2018.
 * Sibers company
 * yagosupro@gmail.com
 */
public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    List<Post> postsList = new ArrayList<>();

    Adapter(List<Post> postsList) {
        this.postsList.addAll(postsList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolderFactory.create(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolderFactory.PostViewHolder postViewHolder = (ViewHolderFactory.PostViewHolder) holder;
        postViewHolder.bind(postsList.get(position));
    }

    public void updatePostsList(List<Post> postsList){
        this.postsList.clear();
        this.postsList.addAll(postsList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }
}
