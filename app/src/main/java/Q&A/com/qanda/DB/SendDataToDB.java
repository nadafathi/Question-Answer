package uniscripts.com.qanda.DB;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
public class SendDataToDB {
    List<NameValuePair> nameValuePairs;
    String phpFile;

    public SendDataToDB(List<NameValuePair> nameValuePairs, String phpFile){
        this.nameValuePairs = nameValuePairs;
        this.phpFile = phpFile;
    }

    public int sendDataToDB(){
        String result = "No Result";
        String URL = MyFixedValues.URL;
        int code = 5;
        InputStream is = null;
        String line=null;

        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(URL + phpFile);
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            Log.e("pass 1", "connection success ");
        }

        catch (Exception e) {
            Log.e("Fail 1: ", e.toString());
        }

        //convert response to string
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
            Log.e("pass 2", "connection success");
        }

        catch (Exception e) {
            Log.e("Fail 2", e.toString());
        }

        //parse json data
        try {

            JSONObject json_data = new JSONObject(result);
            code = (json_data.getInt("code"));
            System.out.println("JASON CODE TRY");
        }
        catch (Exception e) {
            Log.e("Fail 3", e.toString());
            System.out.println("JASON CODE CATCH");
        }

        return code;
    }
}
