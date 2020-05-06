package uniscripts.com.qanda;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.bumptech.glide.Glide;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uniscripts.com.qanda.DB.MyFixedValues;
import uniscripts.com.qanda.DB.SQLiteData;
import uniscripts.com.qanda.DB.SendDataToDB;
import uniscripts.com.qanda.utils.AppController;

import static uniscripts.com.qanda.utils.AppController.TAG;

public class Qusetion extends Activity {
    Button sbmit;
    ImageView imageView;
    String todayDate;
    String path;
    private ArrayList<String> arrayList;
    List<NameValuePair> nameValuePairs;
    String answer;
    private Spinner inputAnswer;
    SQLiteData db;
    String email;
    private List<QuestionModel> questionModel;
    //-------1-----Added this base Url
    private String baseUrl = "http://yourapilink.com/?post_date=";
    private String questionUrl;
    private ImageView questionImage;
    String[] answers;
    Spinner answerSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qusetion);
        questionImage = findViewById(R.id.questionImage);
        db = new SQLiteData(this);
        email = db.getEmail();

        // Answer
        answerSpinner = (Spinner) findViewById(R.id.answer);

        inputAnswer = (Spinner) findViewById(R.id.answer);
        imageView = (ImageView) findViewById(R.id.questionImage);
        arrayList = new ArrayList<String>();

//        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");

        //------2------Change the date format
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        todayDate = formatter.format(date);

        //------3------The new Url is this
        questionUrl = baseUrl + todayDate;

        System.out.println("Date: " + todayDate);
        path = MyFixedValues.URL + todayDate + ".jpg";
        System.out.println(path);

        //-------4-----The Spinner that loads answer is shifted in the loadQuestion() method


        //-------5-----Commented this Previous code for fetching Image which was getting nothing
        // DISPLAY IMAGE
//        AsyncTaskLoadImage loadImage = new AsyncTaskLoadImage();
//        loadImage.execute();

        sbmit = (Button) findViewById(R.id.btn_sbmit);

        sbmit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                answer = inputAnswer.getSelectedItem().toString().trim();

                BackTaskQuestion btQ = new BackTaskQuestion();
                btQ.execute();
            }

        });


        //------5------Added this new Method that uses Volley to parse JSON and get
        //------------Image of the question along with the Answers
        loadQuestion();
    }

    public class AsyncTaskLoadImage extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                URL url = new URL(path);
                System.out.println(path);
                InputStream is = url.openConnection().getInputStream();
                Bitmap bitMap = BitmapFactory.decodeStream(is);
                return bitMap;

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            imageView.setImageBitmap(result);
        }
    }

    private void loadQuestion() {
        Log.v("Question", "Url = " + questionUrl);
        questionModel = new ArrayList<>();
        String tag_json_arry = "json_array_req";
        JsonArrayRequest arrayRequest = new JsonArrayRequest(questionUrl,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject object = response.getJSONObject(0);
                            Log.v("Question", object.toString());
                            String Q_ID = object.getString("Q_ID");
                            String Question = object.getString("Question");
                            String Q_image = object.getString("Q_image");
                            String Answer1 = object.getString("Answer1");
                            String Answer2 = object.getString("Answer2");
                            String Answer3 = object.getString("Answer3");
                            String Answer4 = object.getString("Answer4");

                            //------6------here the answers are populated in spinner
                            answers = new String[]{Answer1, Answer2, Answer3, Answer4};
                            ArrayAdapter<String> answerAdapter = new ArrayAdapter<String>(Qusetion.this,
                                    android.R.layout.simple_list_item_1, answers);

                            answerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            answerSpinner.setAdapter(answerAdapter);

                            QuestionModel obj = new QuestionModel(Q_ID, Question, Q_image, Answer1, Answer2, Answer3, Answer4);
                            questionModel.add(obj);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        setImage();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        });
        AppController.getInstance().addToRequestQueue(arrayRequest, tag_json_arry);
    }

    private class BackTaskQuestion extends AsyncTask<Void, Void, Void> {

        protected Void doInBackground(Void... params) {

            nameValuePairs = new ArrayList<NameValuePair>(2);

            nameValuePairs.add(new BasicNameValuePair("answer", answer));
            nameValuePairs.add(new BasicNameValuePair("email", email));

            int dbResult = new SendDataToDB(nameValuePairs, "setUserAnswer.php").sendDataToDB();

            System.out.println("JSON Code: " + dbResult);

            if (dbResult == 0) {
                Intent i = new Intent(getApplicationContext(), ThankYou.class);
                startActivity(i);
                finish();
            } else {
                System.out.println("else ");
                Log.e("Error", "Failed - DB Issue");

                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Error!!", Toast.LENGTH_LONG).show();
                    }
                });
            }
            return null;
        }
    }

    private void setImage() {
        Glide.with(getApplicationContext()).load(questionModel.get(0).getQ_image()).into(questionImage);
    }
}

