package com.myapps.diegogonzalez.imagegallery;

import android.content.Context;
import android.graphics.Point;
import android.os.Environment;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Created by Diego Gonzalez on 7/28/2015.
 */
public class Utils {
    private Context context;
    public static final List<String> FILE_EXTN = Arrays.asList("jpg", "jpeg",
            "png");

    // Constructor
    public Utils(Context context) {
        this.context = context;
    }

    public ArrayList<String> getFilePaths() {
        ArrayList<String> filePaths = new ArrayList<String>();

        String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        //File directory = new File(baseDir + File.separator + "DCIM" + File.separator + "100MEDIA");
        File directory = new File(baseDir + File.separator + "Pictures");

        if (isExternalStorageReadable()) {
            // check for directory
            if (directory.isDirectory()) {
                // getting list of file paths
                File[] listFiles = directory.listFiles();
                if (listFiles.length > 0) {

                    for (int i = 0; i < listFiles.length; i++) {
                        // get file path
                        String filePath = listFiles[i].getAbsolutePath();

                        // check for supported file extension
                        if (isSupportedFile(filePath)) {
                            filePaths.add(filePath);
                        }
                    }
                } else {
                    Toast.makeText(
                            context,
                            "Error: There are no files",
                            Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(
                        context,
                        "Error: Directory " + directory.getPath() + " isn't a directory",
                        Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(
                    context,
                    "Error: External Storage is currently not available",
                    Toast.LENGTH_LONG).show();
        }
        return filePaths;
    }

    private boolean isSupportedFile(String filePath) {
        String ext = filePath.substring((filePath.lastIndexOf(".") + 1),
                filePath.length());
        if (FILE_EXTN.contains(ext.toLowerCase(Locale.getDefault())))
            return true;
        else
            return false;
    }

    public int getScreenWidth() {
        int columnWidth;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        final Point point = new Point();
        try {
            display.getSize(point);
        } catch (java.lang.NoSuchMethodError ignore) {
            point.x = display.getWidth();
            point.y = display.getHeight();
        }
        columnWidth =  point.x;
        return  columnWidth;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
}


