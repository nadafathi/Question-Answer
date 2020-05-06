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
import android.util.Log;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class Video extends YouTubeBaseActivity {

    YouTubePlayerView youTubePlayerView;
    YouTubePlayer.OnInitializedListener onInitializedListener;

    private Button btn_q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);
        btn_q = (Button) findViewById(R.id.btn_q);


        onInitializedListener = new YouTubePlayer.OnInitializedListener(){


            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                youTubePlayer.loadVideo("5IxZW3mvuWo");

                youTubePlayer.play();
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        youTubePlayerView.initialize(YouTubeConfig.getApiKey(),onInitializedListener);
       /* btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                youTubePlayerView.initialize(YouTubeConfig.getApiKey(),onInitializedListener);
            }
        }); */
        btn_q.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Qusetion.class);
                startActivity(i);

            }

        });

    }





}
