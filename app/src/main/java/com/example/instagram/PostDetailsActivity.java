package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;
import com.parse.ParseObject;

import org.parceler.Parcels;

import java.util.Date;

public class PostDetailsActivity extends AppCompatActivity {

    Post post;

    TextView tvDetailsUser;
    ImageView ivDetailsPhoto;
    TextView tvDetailsCaption;
    TextView tvCreatedAt;
    ImageView ivProfileImage;
    TextView tvSmallUserDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        tvDetailsCaption = (TextView) findViewById(R.id.tvDetailsCaption);
        tvDetailsUser = (TextView) findViewById(R.id.tvDetailsUser);
        ivDetailsPhoto = (ImageView) findViewById(R.id.ivDetailsPhoto);
        tvCreatedAt = (TextView) findViewById(R.id.tvCreatedAt);
        tvSmallUserDetails = (TextView) findViewById(R.id.tvSmallUserDetails);

        Context context = ivDetailsPhoto.getContext();

        post = (Post) getIntent().getParcelableExtra("PARSE_OBJECT_EXTRA");

        tvDetailsUser.setText(post.getUser().getUsername());
        tvDetailsCaption.setText(post.getDescription());
        tvSmallUserDetails.setText(post.getUser().getUsername());

        ivProfileImage = findViewById(R.id.ivProfileImage);
        ivProfileImage.setBackgroundResource(R.drawable.profilepic);

        ParseFile image = post.getImageUrl();
        if (image != null) {
            Glide.with(context).load(image.getUrl()).into(ivDetailsPhoto);
        }

        Date createdAt = post.getCreatedAt();
        String timeAgo = Post.calculateTimeAgo(createdAt);
        tvCreatedAt.setText(timeAgo);
    }
}