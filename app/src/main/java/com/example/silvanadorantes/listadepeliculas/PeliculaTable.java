package com.example.silvanadorantes.listadepeliculas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silvana on 07/08/16.
 */
public class PeliculaTable {
    private static String TAG = "PeliculaTable";

    public static String TABLE_NAME = "pelicula";
    private static String COLUMN_ID_PELICULA = "idpelicula";
    private static String COLUMN_NOMBRE_PELICULA = "nombrepelicula";
    private static String COLUMN_ESTATUS = "estatus";


    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
            + COLUMN_ID_PELICULA + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NOMBRE_PELICULA + " TEXT, "
            + COLUMN_ESTATUS + " TEXT);";

    private DataBaseHelper mHelper;
    private SQLiteDatabase mDataBase;
    private Cursor mCursor;
    private Pelicula mPelicula = new Pelicula();

    public PeliculaTable(Context context){
        mHelper = DataBaseHelper.getInstance(context);
        mDataBase = mHelper.getWritableDatabase();
    }

    public void insertPelicula(String nombrePelicula, String estatus){

        mDataBase = mHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(COLUMN_ID_PELICULA, idPelicula);
        contentValues.put(COLUMN_NOMBRE_PELICULA, nombrePelicula);
        contentValues.put(COLUMN_ESTATUS, estatus);
        mDataBase.insert(TABLE_NAME, null, contentValues);
        mDataBase.close();
    }

    public Pelicula SearchPelicula(){
        mDataBase = mHelper.getReadableDatabase();
        String tiraSQL = " SELECT * FROM " + TABLE_NAME;
        mCursor = mDataBase.rawQuery(tiraSQL, null);
        if (mCursor.moveToFirst()){
            mPelicula.setIdPelicula(mCursor.getInt(0));
            mPelicula.setNombrePelicula(mCursor.getString(1));
            mPelicula.setEstatus(mCursor.getString(2));
            mCursor.close();
            } else {
            mPelicula = null;
        }
        mDataBase.close();
        return mPelicula;
    }

    public Pelicula SearchMoviebyId(int idPelicula) {
        mDataBase = mHelper.getReadableDatabase();
        String tiraSQL = " SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID_PELICULA + " = " + idPelicula;
        Log.e(TAG, tiraSQL);
        mCursor = mDataBase.rawQuery(tiraSQL, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
            mPelicula.setIdPelicula(mCursor.getInt(0));
            mPelicula.setNombrePelicula(mCursor.getString(1));
            mPelicula.setEstatus(mCursor.getString(2));
            mCursor.close();
        } else {
            mPelicula = null;
        }
        mDataBase.close();
        return mPelicula;
    }

    public List<Pelicula> getListaPeliculas() {

        List<Pelicula> listapeliculas = new ArrayList<>();
        String tiraSQL = "SELECT  * FROM " + TABLE_NAME;

        Log.e(TAG, tiraSQL);

        mDataBase = mHelper.getWritableDatabase();
        mCursor = mDataBase.rawQuery(tiraSQL, null);

        try {

            if (mCursor.moveToFirst()) {

                do {
                    Pelicula pelicula = new Pelicula();
                    pelicula.setIdPelicula(mCursor.getInt(0));
                    pelicula.setNombrePelicula(mCursor.getString(1));
                    pelicula.setEstatus(mCursor.getString(2));


                    // adding to movie a list
                    listapeliculas.add(pelicula);



                } while ( mCursor.moveToNext());



            }

        } catch (Exception e){
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (mCursor != null && !mCursor.isClosed()){
                mCursor.close();
            }
        }

        mDataBase.close();
        return listapeliculas;
    }

    public void deletepelicula(int id){
        mDataBase = mHelper.getWritableDatabase();
        String[] condicion = new String[]{String.valueOf(id)};
        mDataBase.delete(TABLE_NAME, COLUMN_ID_PELICULA + " = ?", condicion);
        mDataBase.close();

    }


    public Cursor getAllPeliculas(){
        mDataBase = mHelper.getWritableDatabase();
        //Selecciona todas las filas de la tabla pelicula
        String tiraSQl = " SELECT * FROM " + TABLE_NAME;
        return mDataBase.rawQuery(tiraSQl, null);
    }


    public int getPeliculaCount(){
        String tiraSQL = "SELECT * FROM " + TABLE_NAME;
        mDataBase = mHelper.getReadableDatabase();
        mCursor = mDataBase.rawQuery(tiraSQL, null);
        return mCursor.getCount();

    }

    public void destroyTable(){
        mDataBase.execSQL(" DROP TABLE IF NOT EXISTS " + TABLE_NAME);
    }


}
