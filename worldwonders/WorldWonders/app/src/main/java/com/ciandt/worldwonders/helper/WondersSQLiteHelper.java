package com.ciandt.worldwonders.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by pmachado on 8/24/15.
 */
public class WondersSQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "wonders.db";

    private static final String DATABASE_DIRECTORY = "data/data/com.ciandt.worldwonders/databases/";
    private static final String DATABASE_PATH = DATABASE_DIRECTORY + DATABASE_NAME;

    private static final String DATABASE_ORIGIN_DIRECTORY = "/assets/database/";
    private static final String DATABASE_ORIGIN_PATH = DATABASE_ORIGIN_DIRECTORY + DATABASE_NAME;

    private static final int DATABASE_VERSION = 1;

    public WondersSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //roda o script do banco
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //roda o script de update
    }

    public static boolean initialize() {
        boolean ok = true;
        if (!checkDatabase()) {
            try {
                File dir = new File(DATABASE_DIRECTORY);

                if (!dir.exists()) dir.mkdirs();

                File f_in = new File(DATABASE_ORIGIN_PATH);
                File f_out = new File(DATABASE_PATH);

                if (!f_out.exists()) copyFile(f_in, f_out);
            } catch (Exception e) {
                ok = false;
            }
        }
        return ok;
    }

    private static boolean checkDatabase() {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase(DATABASE_PATH, null,
                    SQLiteDatabase.OPEN_READONLY);
            checkDB.close();
        } catch (Exception e) {

        }
        return checkDB != null;
    }

    private static void copyFile(File sourceFile, File destFile) throws IOException {
        if(!destFile.exists()) {
            destFile.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;
        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();

            // previous code: destination.transferFrom(source, 0, source.size());
            // to avoid infinite loops, should be:
            long count = 0;
            long size = source.size();
            while((count += destination.transferFrom(source, count, size-count))<size);
        }
        finally {
            if(source != null) {
                source.close();
            }
            if(destination != null) {
                destination.close();
            }
        }
    }
}
