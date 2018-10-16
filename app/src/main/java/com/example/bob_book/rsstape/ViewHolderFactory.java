package com.example.bob_book.rsstape;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bob_book.rsstape.Model.Post;

/**
 * Created by Roman Parkhomenko on 10/15/2018.
 * Sibers company
 * yagosupro@gmail.com
 */
public class ViewHolderFactory {

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView body;

        public PostViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);
        }

        public void bind(Post post) {
            title.setText(post.title);
            body.setText(post.body);
        }
    }

    public static RecyclerView.ViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolderFactory.PostViewHolder(view);
    }

}
