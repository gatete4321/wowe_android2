package com.example.wowebackand.Retrofit;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MyPicasso
{
    private final static String imageurl="http://10.0.2.2:9001/download?filename=";

    private Context context;

    public MyPicasso(Context context) {
        this.context = context;
    }

    void putImage(String imageName, ImageView resource,boolean useFit){
        imageName=imageurl+imageName;
        if (useFit==true){
            Picasso.with(context).load(imageName).fit().into(resource);
        }
        else
        Picasso.with(context).load(imageName).into(resource);
    }
    void putImage(String imageurl,ImageView resource,int width,int height){
        imageurl=imageurl+imageurl;
        Picasso.with(context).load(imageurl).resize(width,height).into(resource);
    }
}
