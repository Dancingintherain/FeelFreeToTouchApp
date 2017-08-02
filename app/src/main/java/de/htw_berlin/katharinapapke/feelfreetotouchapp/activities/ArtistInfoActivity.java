package de.htw_berlin.katharinapapke.feelfreetotouchapp.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import de.htw_berlin.katharinapapke.feelfreetotouchapp.R;

public class ArtistInfoActivity extends AppCompatActivity {

    private TextView aboutArtistText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.menutoolbar);
        aboutArtistText = (TextView) findViewById(R.id.textAboutArtist);
        aboutArtistText.setMovementMethod(new ScrollingMovementMethod());
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create a new MediaPlayer
                final MediaPlayer song= MediaPlayer.create(ArtistInfoActivity.this,R.raw.aintthatakickintheheadwithraylowe);
                //starts the audio file with infos from the artist
                song.start();
                //creates new Snackbar
                Snackbar bar = Snackbar.make(view, "Listen what the artist has to say.", Snackbar.LENGTH_INDEFINITE)
                        //creates action which will stop the audio file
                        .setAction("Stop Tape", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                song.stop();
                            }
                        })
                        ;
                bar.show();
            }
        });
        //sets Back-Icon next to Toolbar Title
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //sets MenuItem artistInfo invisible
        MenuItem actionArtistInfo = menu.findItem(R.id.action_artistInfo);
        actionArtistInfo.setVisible(false);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        switch(item.getItemId()) {

            //if icon artistList get clicked the artistList Activity starts
            case R.id.action_artistList:;
                Intent intent = new Intent(ArtistInfoActivity.this, ArtistListActivity.class);
                startActivity(intent);
                //finish();
                return true;
            case android.R.id.home:
                Intent intent2 = new Intent(ArtistInfoActivity.this, MainActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
                //finish();
                /*onBackPressed();
                finish();*/
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
