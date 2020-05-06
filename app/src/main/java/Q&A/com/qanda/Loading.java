package uniscripts.com.qanda;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.*;
import android.content.*;
import android.content.ContentValues;
import android.util.Log;
import java.lang.String;

import uniscripts.com.qanda.DB.SQLiteData;

public class Loading extends Activity {
    SQLiteData db;
    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        db = new SQLiteData(this);
      


        result = db.getValue();

        if (result>0){
            Intent s = new Intent(getApplicationContext(), Sponser.class);
            startActivity(s);
        }
        else {
            System.out.println("wrong");
            Intent r = new Intent(getApplicationContext(), Registeration.class);
            startActivity(r);
        }
    }


}
