package com.example.instagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.Date;
import java.util.List;

public class GridRecyclerAdapter extends RecyclerView.Adapter<GridRecyclerAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public GridRecyclerAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public GridRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_profile, parent, false);
        return new GridRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivProfileImages;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImages = itemView.findViewById(R.id.ivProfileImages);

            //itemView.setOnClickListener(this::onClick);
        }

        public void bind(Post post) {
            ParseFile image = post.getImageUrl();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(ivProfileImages);
            }
        }
    }
}