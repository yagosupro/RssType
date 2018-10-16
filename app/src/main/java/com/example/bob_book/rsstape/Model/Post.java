package com.example.bob_book.rsstape.Model;

/**
 * Created by Roman Parkhomenko on 10/15/2018.
 * Sibers company
 * yagosupro@gmail.com
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Post{
    @SerializedName("userId")
    @Expose
    public Integer userId;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("body")
    @Expose
    public String body;
}
