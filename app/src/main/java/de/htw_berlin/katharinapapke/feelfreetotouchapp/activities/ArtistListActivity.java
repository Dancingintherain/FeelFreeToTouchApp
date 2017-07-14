package de.htw_berlin.katharinapapke.feelfreetotouchapp.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import de.htw_berlin.katharinapapke.feelfreetotouchapp.R;
import de.htw_berlin.katharinapapke.feelfreetotouchapp.dummy.DummyContent;
import de.htw_berlin.katharinapapke.feelfreetotouchapp.fragments.ArtistListItemFragment;

public class ArtistListActivity extends AppCompatActivity implements ArtistListItemFragment.OnListFragmentInteractionListener {

    static final int REQUEST_IMAGE_CAPTURE = 1;

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
                //creates a PackageManager
                PackageManager pm = getApplicationContext().getPackageManager();
                //checks, if the device has a camera
                if (pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
                    //opens camera via delegating camera action to a camera application
                    dispatchTakePictureIntent();
                    //shows snackbar
                    Snackbar.make(view, "Take your own picture of the object", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else{
                    Snackbar.make(view, "You can ask the museum staff for more information.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

            }
        });
        //sets back-icon next to toolbar title
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    //method to open camera application on device
    public void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
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

            //If icon artistInfo gets clicked the artistInfo activity starts
            case R.id.action_artistInfo:;
                Intent intent = new Intent(ArtistListActivity.this, ArtistInfoActivity.class);
                startActivity(intent);
                return true;
            //If back icon gets clicked
            case android.R.id.home:
                onBackPressed();
                finish();
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
