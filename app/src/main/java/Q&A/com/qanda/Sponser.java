package uniscripts.com.qanda;

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

public class Sponser extends Activity {
    private Button btnWtch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponser);


        btnWtch = (Button) findViewById(R.id.btn_wtch);

        btnWtch.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Video.class);
                startActivity(i);

            }

        });
    }
}
