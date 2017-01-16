package com.example.silvanadorantes.listadepeliculas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by silvana on 07/08/16.
 */
public class DataBaseHelper extends SQLiteOpenHelper{

    public static final String NAME = "listapeliculas";
    public static final int VERSION = 1;
    private static DataBaseHelper mInstance;

    public static DataBaseHelper getInstance(Context context){
        if (mInstance == null){
            mInstance = new DataBaseHelper(context);
        }

        return mInstance;

    }

    public DataBaseHelper(Context context){
        super(context, NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(PeliculaTable.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + PeliculaTable.TABLE_NAME);

    }


    @Override
    public synchronized void close() {
        super.close();
    }
}
