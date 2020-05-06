package uniscripts.com.qanda.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteData extends SQLiteOpenHelper{
    private SQLiteDatabase db;

    public SQLiteData(Context context){
        super(context, "medical-db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("DROP TABLE  userInfo");
        db.execSQL("CREATE TABLE IF NOT EXISTS userInfo(flag INTEGER PRIMARY KEY AUTOINCREMENT," +
                "fullName varchar, Email varchar, Phone varchar)");
    }

    public void deleteData(){
        db.execSQL("Delete  from userInfo ");

        db.close(); // Closing database connection
    }

    public int getValue(){
        int result =0;
        db = this.getReadableDatabase();
        String selectQuery = "SELECT flag FROM userInfo ";
        Cursor cursor = db.rawQuery(selectQuery, null);

        while(cursor.moveToNext()) {
            result = cursor.getInt(0);
        }

        return result;
    }


    public String getEmail(){
        String result = null;
        db = this.getReadableDatabase();
        String selectQuery = "SELECT Email FROM userInfo ";
        Cursor cursor = db.rawQuery(selectQuery, null);

        while(cursor.moveToNext()) {
            result = cursor.getString(0);
        }

        return result;
    }


    public void insertValue( String fullName, String Email,String phone ){
        db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put("fullName", fullName);
        values.put("Email", Email);
        values.put("phone", phone);
        db.insert("userInfo", null, values);
        db.close(); // Closing database connection
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SQLiteData.class.getName(), "Upgrading database from version " + oldVersion + " to "+ newVersion + ", which will destroy all old data");

        onCreate(db);
    }
}
