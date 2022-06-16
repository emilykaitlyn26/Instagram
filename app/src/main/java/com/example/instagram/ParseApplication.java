package com.example.instagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this).applicationId("wGFwSGLQfrr058a8jljxhieIm2wYGNknL5JucOos").clientKey("1ovXHK2mkCHtyp0a0Fj9vImoueaISLni8krndddf").server("https://parseapi.back4app.com").build());
    }
}
