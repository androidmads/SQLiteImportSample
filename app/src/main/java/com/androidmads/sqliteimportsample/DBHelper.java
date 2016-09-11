package com.androidmads.sqliteimportsample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBHelper extends SQLiteOpenHelper {
    Context context;
    String DB_PATH;
    String divider = "/";
    String DB_NAME;

    public DBHelper(Context context, String DB_NAME) {
        super(context, DB_NAME, null, 1);
        this.context = context;
        this.DB_NAME = DB_NAME;
        DB_PATH = divider + "data" + divider + "data" + divider + context.getPackageName() + divider + "databases/";
    }

    public boolean isDataBaseExists() {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }

    public void importDataBaseFromAssets() throws IOException {

        this.getReadableDatabase();

        InputStream myInput = context.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        Toast.makeText(context.getApplicationContext(), "Successfully Imported", Toast.LENGTH_SHORT).show();
        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    @Override
    public void onCreate(SQLiteDatabase arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
    }

}
