package de.htw_berlin.katharinapapke.feelfreetotouchapp.activities;

import android.content.Intent;
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
                Snackbar.make(view, "This will be a link to the recent exhibition/Audiotape starts", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
