package com.example.instagram;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    private TextView tvUserDisplay;
    private ImageView ivPostPhoto;
    private TextView tvPhotoDescription;

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
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
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserDisplay = itemView.findViewById(R.id.tvUserDisplay);
            ivPostPhoto = itemView.findViewById(R.id.ivPostPhoto);
            tvPhotoDescription = itemView.findViewById(R.id.tvPhotoDescription);
        }

        public void bind(Post post) {
            tvPhotoDescription.setText(post.getDescription());
            tvUserDisplay.setText(post.getUser().getUsername());
            ParseFile image = post.getImageUrl();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(ivPostPhoto);
            }
        }
    }
}