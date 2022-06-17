package com.example.instagram;

import android.os.Parcelable;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.parceler.Parcel;

import java.util.Date;

@ParseClassName("Post")
public class Post extends ParseObject {

    public static final String KEY_DESCRIPTION = "Description";
    public static final String KEY_IMAGE = "Image";
    public static final String KEY_USER = "User";
    public static final String KEY_CREATED_AT = "createdAt";

    public static final String TAG = "Post";

    public Post() {
    }

    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }

    public void setDescription(String description) {
        put(KEY_DESCRIPTION, description);
    }

    public ParseFile getImageUrl() {
        return getParseFile(KEY_IMAGE);
    }

    public void setImage(ParseFile parseFile) {
        put(KEY_IMAGE, parseFile);
    }

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }

    public static String calculateTimeAgo(Date createdAt) {
        int SECOND_MILLIS = 1000;
        int MINUTE_MILLIS = 60 * SECOND_MILLIS;
        int HOUR_MILLIS = 60 * MINUTE_MILLIS;
        int DAY_MILLIS = 24 * HOUR_MILLIS;

        try {
            createdAt.getTime();
            long time = createdAt.getTime();
            long now = System.currentTimeMillis();

            final long diff = now - time;
            if (diff < MINUTE_MILLIS) {
                return "just now";
            } else if (diff < 50 * MINUTE_MILLIS) {
                if ((diff / MINUTE_MILLIS) == 1) {
                    return diff / MINUTE_MILLIS + " minute ago";
                } else {
                    return diff / MINUTE_MILLIS + " minutes ago";
                }
            } else if (diff < 24 * HOUR_MILLIS) {
                if ((diff / HOUR_MILLIS) == 1) {
                    return diff / HOUR_MILLIS + " hour ago";
                } else {
                    return diff / HOUR_MILLIS + " hours ago";
                }
            } else {
                if ((diff / DAY_MILLIS) == 1) {
                    return diff / DAY_MILLIS + " day ago";
                } else {
                    return diff / DAY_MILLIS + " days ago";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}