package uniscripts.com.qanda.DB;

import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
public class GetDataFromDB {
    HttpPost httppost;
    //HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    String phpFile;

    public GetDataFromDB(List<NameValuePair> nameValuePairs, String phpFile){
        this.nameValuePairs = nameValuePairs;
        this.phpFile = phpFile;
    }

    public String getDataFromDB(){
        String result="";
        String URL = MyFixedValues.URL;

        try{

            httpclient=new DefaultHttpClient();
            httppost= new HttpPost(URL+phpFile);

            if(nameValuePairs != null) {
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
                //response=httpclient.execute(httppost); //not needed

                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                final String response = httpclient.execute(httppost, responseHandler);

                result = response;
            }
            else if(nameValuePairs == null){
                InputStream is=null;
                HttpResponse response=httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                // Get our response as a String.
                is = entity.getContent();

                BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf-8"));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    result+=line;
                }
                is.close();
            }

            else {
                result = "No Result";
                Log.e("Error : ", "Unknown value for nameValuePairs");
            }

        }
        catch(Exception e) {
            Log.e("Exception : ", e.toString());
        }

        return result;
    }
}
