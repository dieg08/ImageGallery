package com.myapps.diegogonzalez.imagegallery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class FullScreenViewActivity extends Activity {

    private ArrayList<String> filePaths;
    private int position;
    private ImageAdapter adapter;
    private Utils util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_view);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        util = new Utils(this);

        this.position = b.getInt("position");
        this.filePaths = util.getFilePaths();
        this.adapter = new ImageAdapter(this, filePaths, position);
        ViewPager pager = (ViewPager) findViewById(R.id.pager);

        pager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_full_screen_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
