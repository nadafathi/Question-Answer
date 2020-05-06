package uniscripts.com.qanda;

import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import uniscripts.com.qanda.DB.SendDataToDB;
import uniscripts.com.qanda.DB.SQLiteData;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.content.Context;

public class Registeration extends Activity {
    String fullName,
            age,
            gender,
            status,
            occupation,
            income,
            city,
            nationality,
            phone,
            email,
    et_reg_password;

    private Button btnRegister;

    private EditText inputfullName;
    private Spinner inputAge;
    private Spinner inputGender;
    private Spinner inputStatus;
    private Spinner inputOccupation;
    private Spinner inputIncome;
    private Spinner inputCity;
    private Spinner inputNationality;
    private EditText inputPhone;
    private EditText inputEmail;
    private EditText inputpassword;

    private ProgressDialog pDialog;
    List<NameValuePair> nameValuePairs;
    SQLiteData saveUserInfo;
    //TelephonyManager TelephonyMgr = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
    //String m_deviceId = TelephonyMgr.getDeviceId();
    //String m_androidId = Secure.getString(getContentResolver(), Secure.ANDROID_ID);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        saveUserInfo = new SQLiteData(this);
        inputfullName = (EditText) findViewById(R.id.fullName);
        inputAge = (Spinner) findViewById(R.id.age);
        inputGender = (Spinner) findViewById(R.id.gender);
        inputStatus = (Spinner) findViewById(R.id.status);
        inputOccupation = (Spinner) findViewById(R.id.occupation);
        inputIncome = (Spinner) findViewById(R.id.income);
        inputCity = (Spinner) findViewById(R.id.city);
        inputNationality = (Spinner) findViewById(R.id.nationality);
        inputPhone = (EditText) findViewById(R.id.phone);
        inputEmail = (EditText) findViewById(R.id.email);
        inputpassword = (EditText) findViewById(R.id.et_reg_password);

        btnRegister = (Button) findViewById(R.id.btn_register);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // age
        Spinner ageSpinner = (Spinner) findViewById(R.id.age);
        ArrayAdapter<String> ageAdapter = new ArrayAdapter<String>(Registeration.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Age));
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ageSpinner.setAdapter(ageAdapter);

        // Gender
        Spinner genderSpinner = (Spinner) findViewById(R.id.gender);
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(Registeration.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Gender));
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderAdapter);

        // Status
        Spinner statusSpinner = (Spinner) findViewById(R.id.status);
        ArrayAdapter<String> statusAdapter = new ArrayAdapter<String>(Registeration.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Status));
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(statusAdapter);

        // Job
        Spinner jobSpinner = (Spinner) findViewById(R.id.occupation);
        ArrayAdapter<String> jobAdapter = new ArrayAdapter<String>(Registeration.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Job));
        jobAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jobSpinner.setAdapter(jobAdapter);


        // Nationality
        Spinner NationSpinner = (Spinner) findViewById(R.id.nationality);
        ArrayAdapter<String> NationAdapter = new ArrayAdapter<String>(Registeration.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Nationality));
        NationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        NationSpinner.setAdapter(NationAdapter);

        // City
        Spinner citySpinner = (Spinner) findViewById(R.id.city);
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(Registeration.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.City));
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(cityAdapter);

        // Income
        Spinner incomeSpinner = (Spinner) findViewById(R.id.income);
        ArrayAdapter<String> incomeAdapter = new ArrayAdapter<String>(Registeration.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Income));
        incomeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        incomeSpinner.setAdapter(incomeAdapter);


        // Login button Click Event
        btnRegister.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                fullName = inputfullName.getText().toString().trim();
                age = inputAge.getSelectedItem().toString().trim();
                gender = inputGender.getSelectedItem().toString().trim();
                status = inputStatus.getSelectedItem().toString().trim();
                occupation = inputOccupation.getSelectedItem().toString().trim();
                income = inputIncome.getSelectedItem().toString().trim();
                city = inputCity.getSelectedItem().toString();
                nationality = inputNationality.getSelectedItem().toString().trim();
                phone = inputPhone.getText().toString().trim();
                email = inputEmail.getText().toString(). trim();
                et_reg_password = inputpassword.getText().toString();

                saveUserInfo.insertValue(fullName,email,phone);
                BackTaskRegister bt = new BackTaskRegister();
                bt.execute();
            }

        });
    }
    private class BackTaskRegister extends AsyncTask<Void,Void,Void> {

        protected Void doInBackground(Void...params) {


            nameValuePairs = new ArrayList<NameValuePair>(2);

            nameValuePairs.add(new BasicNameValuePair("fullName",fullName));
            nameValuePairs.add(new BasicNameValuePair("age",age));
            nameValuePairs.add(new BasicNameValuePair("gender",gender));
            nameValuePairs.add(new BasicNameValuePair("status",status));
            nameValuePairs.add(new BasicNameValuePair("occupation",occupation));
            nameValuePairs.add(new BasicNameValuePair("income",income));
            nameValuePairs.add(new BasicNameValuePair("city",city));
            nameValuePairs.add(new BasicNameValuePair("nationality",nationality));
            nameValuePairs.add(new BasicNameValuePair("phone",phone));
            nameValuePairs.add(new BasicNameValuePair("email",email));
            nameValuePairs.add(new BasicNameValuePair("password",et_reg_password));



            int dbResult = new SendDataToDB(nameValuePairs, "setUserRegister.php").sendDataToDB();

            System.out.println("JSON Code: "+dbResult);

            if(dbResult == 0){
                Intent i = new Intent(getApplicationContext(), Sponser.class);
                startActivity(i);
                finish();
            }

            else{
                Log.e("Error", "Regsitration Failed - DB Issue");

                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(getApplicationContext(), "RegistrationError!!", Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }
    }


    }

