package com.myapps.diegogonzalez.imagegallery;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by Diego Gonzalez on 7/29/2015.
 */
public class ImageAdapter extends PagerAdapter {

    private Activity activity;
    private ArrayList<String> filePaths;
    private LayoutInflater inflater;
    private int _position;
    private boolean isFirst;

    public ImageAdapter(Activity activity, ArrayList<String> filePaths, int position) {
        this.activity = activity;
        this.filePaths = filePaths;
        this._position = position;
        this.isFirst = true;
    }

    @Override
    public int getCount() {
        return filePaths.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){
        ImageView imgDisplay;
        Button btn_close;

        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.fullscreen_interface, container, false);

        imgDisplay = (ImageView) viewLayout.findViewById(R.id.imgDisplay);
        btn_close = (Button) viewLayout.findViewById(R.id.btnClose);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap;
        if (isFirst) {
            bitmap = BitmapFactory.decodeFile(filePaths.get(_position), options);
            isFirst = false;
        } else {
            bitmap = BitmapFactory.decodeFile(filePaths.get(position), options);
        }
        imgDisplay.setImageBitmap(bitmap);

        // close button click event
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });

        ((ViewPager) container).addView(viewLayout);

        return viewLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);

    }

}
