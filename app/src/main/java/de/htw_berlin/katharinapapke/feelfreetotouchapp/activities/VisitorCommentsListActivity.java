package de.htw_berlin.katharinapapke.feelfreetotouchapp.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import de.htw_berlin.katharinapapke.feelfreetotouchapp.R;
import de.htw_berlin.katharinapapke.feelfreetotouchapp.managment.DBHandler;
import de.htw_berlin.katharinapapke.feelfreetotouchapp.managment.DBManager;
import de.htw_berlin.katharinapapke.feelfreetotouchapp.models.Comments;

public class VisitorCommentsListActivity extends AppCompatActivity {

    //LogCat
    private static final String TAG =  VisitorCommentsListActivity.class.getSimpleName();

    private ListView listView;
    private Context context;
    private DBManager dbManager;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.menutoolbar);
        setSupportActionBar(toolbar);

        //create and opens database
        dbManager = new DBManager(this);
        dbManager.open();

        //gets input values from visitor input dialog
        String comment = getIntent().getStringExtra("Comment");
        String exhibition = getIntent().getStringExtra("Exhibition");

        //creates new object Comments from visitor input in dialog and insert to db
        Comments newComment = new Comments(comment, exhibition);
        dbManager.insert(newComment);

        //sets view
        setContentView(R.layout.activity_visitor_comments_list);
        listView = (ListView) findViewById(R.id.visitor_comments_list_view);
        populateListView();
    }

    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView");
        final String[] from = new String[] { DBHandler.ID,
                DBHandler.KEY_COMMENT, DBHandler.KEY_VISITOR_AGE};
        final int[] to = new int[] { R.id.commentsList_Id, R.id.commentsList_visitorcomment, R.id.commentsList_exhibitionComment};

        listView = (ListView) findViewById(R.id.visitor_comments_list_view);

        //when empty list shows line here
        listView.setEmptyView(findViewById(R.id.empty));

        Cursor cursor = dbManager.fetch();
        adapter = new SimpleCursorAdapter(this, R.layout.activity_visitor_commentsitem, cursor, from, to, 0);
        //adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListener For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView idTextView = (TextView) view.findViewById(R.id.commentsList_Id);
                TextView visitorCommentTextView = (TextView) view.findViewById(R.id.commentsList_visitorcomment);
                TextView visitorCommentExhibitionTextView = (TextView) view.findViewById(R.id.commentsList_exhibitionComment);

                String _id = idTextView.getText().toString();
                String comment = visitorCommentTextView.getText().toString();
                String visitorCommentExhibition = visitorCommentExhibitionTextView.getText().toString();

                final Dialog dialog = new Dialog(context);
                //sets the view for the custom dialog
                dialog.setContentView(R.layout.dialog_custom);
                //opens dialog
                dialog.show();
            }
        });
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
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
                Intent intent = new Intent(VisitorCommentsListActivity.this, MainActivity.class);
                startActivity(intent);
                return true;

            //If icon artistInfo get clicked the artistInfo activity starts
            case R.id.action_artistInfo:;
                Intent artistInfoIntent = new Intent(VisitorCommentsListActivity.this, MainActivity.class);
                startActivity(artistInfoIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    }
