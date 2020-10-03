package com.bintracking.data;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class EventAPI {

    private static final String key = "AIzaSyBxHMRCY249ZTt_OmMVS4i_OL1bqOyVfkQ";
    private static final String url = "https://www.googleapis.com/blogger/v3/blogs/34281122153343243/posts/";

    public static PostService postService = null;

    public static PostService getService()
    {
        if (postService == null)
        {
            Retrofit retrofit = new Retrofit.Builder()
                 .baseUrl(url)
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();

            postService = retrofit.create(PostService.class);
        }
        return postService;
    }


    public interface PostService {
        @GET("?key=" + key)
        Call<PostList> getPostList();
    }
}

