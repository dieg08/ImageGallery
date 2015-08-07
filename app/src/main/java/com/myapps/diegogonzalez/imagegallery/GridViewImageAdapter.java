package com.myapps.diegogonzalez.imagegallery;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by Diego Gonzalez on 7/28/2015.
 */
public class GridViewImageAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<String> filePaths;
    private int imageWidth;

    public GridViewImageAdapter(Activity activity, ArrayList<String> filePaths, int imageWidth) {
        this.activity = activity;
        this.filePaths = filePaths;
        this.imageWidth = imageWidth;
    }

    @Override
    public int getCount() {
        return this.filePaths.size();
    }

    @Override
    public Object getItem(int position) {
        return filePaths.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(activity);
        } else {
            imageView = (ImageView) convertView;
        }

        Bitmap image = decodeFile(filePaths.get(position), imageWidth, imageWidth);

        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(imageWidth,
                imageWidth));
        imageView.setImageBitmap(image);

        imageView.setOnClickListener(new OnImageClickListener(position));

        return imageView;
    }

    class OnImageClickListener implements View.OnClickListener {
        int _postion;

        // constructor
        public OnImageClickListener(int position) {
            this._postion = position;
        }

        @Override
        public void onClick(View v) {
            // on selecting grid view image
            // launch full screen activity
            Intent i = new Intent(activity, FullScreenViewActivity.class);
            i.putExtra("position", _postion);
            activity.startActivity(i);
        }
    }

    public static Bitmap decodeFile(String filePath, int WIDTH, int HEIGHT) {
        try {
            File f = new File(filePath);

            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);

            final int REQUIRED_WIDTH = WIDTH;
            final int REQUIRED_HEIGHT = HEIGHT;
            int scale = 1;

            while (o.outWidth / scale / 2 >= REQUIRED_WIDTH && o.outHeight / scale / 2 >= REQUIRED_HEIGHT)
                scale *= 2;

            BitmapFactory.Options o2 = new BitmapFactory.Options();

            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
