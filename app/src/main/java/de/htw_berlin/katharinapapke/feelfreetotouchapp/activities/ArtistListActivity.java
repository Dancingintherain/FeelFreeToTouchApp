package de.htw_berlin.katharinapapke.feelfreetotouchapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import de.htw_berlin.katharinapapke.feelfreetotouchapp.fragments.ArtistListItemFragment;
import de.htw_berlin.katharinapapke.feelfreetotouchapp.R;
import de.htw_berlin.katharinapapke.feelfreetotouchapp.dummy.DummyContent;

public class ArtistListActivity extends AppCompatActivity implements ArtistListItemFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.menutoolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.list_floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "This will be a link to the recent exhibition/Camera starts", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //sets back-icon next to toolbar title
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //sets MenuItem artistList invisible
        MenuItem actionArtistList = menu.findItem(R.id.action_artistList);
        actionArtistList.setVisible(false);
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

            //If icon artistInfo get clicked the artistInfo activity starts
            case R.id.action_artistInfo:;
                Intent intent = new Intent(ArtistListActivity.this, ArtistInfoActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onListFragmentInteraction(DummyContent.ArtistListItem item) {
        Toast.makeText(this, item.id, Toast.LENGTH_SHORT).show();
        /*Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(EXTRA_MESSAGE, item.details);
        startActivity(intent);
*/
    }
}
