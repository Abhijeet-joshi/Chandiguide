package com.rishiraj.chandiguide;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DatabaseConn extends SQLiteOpenHelper {

    static String dbName = "Chandiguide.sqlite";
    String dbPath = "";
    SQLiteDatabase mainDB;

    public DatabaseConn(@Nullable Context context) {
        super(context, dbName, null,1);
        dbPath = "/data/data/"+context.getPackageName()+"/databases";
    }

    public synchronized static com.rishiraj.chandiguide.DatabaseConn getDB(Context context){
            return new com.rishiraj.chandiguide.DatabaseConn(context);
    }

    /*check() -- true --> Open()

        false
        |
        create()
        |
        Open()

     */


    public boolean checkDB(){
        SQLiteDatabase database = null;
        try{
            database = SQLiteDatabase.openDatabase(dbPath+"/"+dbName,null, SQLiteDatabase.OPEN_READONLY);
        }catch (Exception e){
            database = null;
        }

        return database!=null;
    }

        public void createDB(Context context){

        this.close();
        this.getReadableDatabase();

        try{
            //input for file copy
            InputStream fin = context.getAssets().open(dbName);

            //output for file paste
            String filePath = dbPath+"/"+dbName;

            //file creation
            FileOutputStream fos =  new FileOutputStream(filePath);

            //Read from fin and write in fos

            byte[] bytes = new byte[1024]; //1kb
            int len;

            while ((len = fin.read(bytes))>0){
                fos.write(bytes,0,len);
            }

            fos.flush();
            fos.close();
            fin.close();

        }catch (IOException e){
            e.printStackTrace();
        }

        }

        public void openDB(){
            mainDB = SQLiteDatabase.openDatabase(dbPath+"/"+dbName,null, SQLiteDatabase.OPEN_READWRITE);
        }

        //Query -----------------------------------------------

        public ArrayList<model> getQues(){

            Cursor cursor = mainDB.rawQuery("select * from 'Chandiguide'",null);

            ArrayList<model> arrData = new ArrayList<>();

            while (cursor.moveToNext()){

                model model = new model();

                model.sno = cursor.getInt(0);
                model.image = cursor.getBlob(1);
                model.title = cursor.getString(2);
                model.desc = cursor.getString(3);
                model.address = cursor.getString(4);

                arrData.add(model);
            }

            return arrData;

        }





    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
