package com.example.instagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.instagram.fragments.ProfileFragment;
import com.parse.ParseFile;

import java.util.Date;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    private static final String KEY_IMAGE = "profileImage";

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

    /*public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }*/

    /*public void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }*/

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvUserDisplay;
        private ImageView ivPostPhoto;
        private TextView tvPhotoDescription;
        private TextView tvTimestamp;
        private ImageView imgUser;
        private TextView tvSmallUser;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserDisplay = itemView.findViewById(R.id.tvUserDisplay);
            ivPostPhoto = itemView.findViewById(R.id.ivPostPhoto);
            tvPhotoDescription = itemView.findViewById(R.id.tvPhotoDescription);
            tvTimestamp = itemView.findViewById(R.id.tvTimestamp);
            tvSmallUser = itemView.findViewById(R.id.tvSmallUser);

            imgUser = itemView.findViewById(R.id.profileImage);
            imgUser.setBackgroundResource(R.drawable.profilepic);
            /*ParseUser currentUser = ParseUser.getCurrentUser();
            try {
                ParseFile img = currentUser.getParseFile("profileImage");
                if (img != null) {
                    Bitmap bmp = BitmapFactory.decodeStream(img.getDataStream());
                    imgUser.setImageBitmap(bmp);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }*/
            imgUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     AppCompatActivity activity = (AppCompatActivity) v.getContext();
                     Fragment fragment = new ProfileFragment();
                     activity.getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, fragment).commit();
                }
            });

            tvUserDisplay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Fragment fragment = new ProfileFragment();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, fragment).commit();
                }
            });

            itemView.setOnClickListener(this::onClick);
        }

        /*public void setImage(ParseFile parseFile) {
            ParseUser currentUser = ParseUser.getCurrentUser();
            currentUser.put(KEY_IMAGE, parseFile);
        }*/

        public void bind(Post post) {
            tvPhotoDescription.setText(post.getDescription());
            tvUserDisplay.setText(post.getUser().getUsername());
            tvSmallUser.setText(post.getUser().getUsername());
            Date createdAt = post.getCreatedAt();
            String timeAgo = Post.calculateTimeAgo(createdAt);
            tvTimestamp.setText(timeAgo);
            ParseFile image = post.getImageUrl();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(ivPostPhoto);
            }
        }

        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Post post = posts.get(position);
                Intent intent = new Intent(context, PostDetailsActivity.class);
                intent.putExtra("PARSE_OBJECT_EXTRA", post);
                context.startActivity(intent);
            }
        }
    }
}