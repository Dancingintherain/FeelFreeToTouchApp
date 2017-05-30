package de.htw_berlin.katharinapapke.feelfreetotouchapp.activities;

import android.app.Dialog;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
        //put visitor input into database if set
        putVisitorInputIntoDatabase();
        //sets view
        setContentView(R.layout.activity_visitor_comments_list);
        listView = (ListView) findViewById(R.id.visitor_comments_list_view);
        populateListView();
    }

    private void putVisitorInputIntoDatabase(){
        //puts visitor info into database only if intent has extras
        Bundle extras = getIntent().getExtras();
        if (extras != null)
            {
            //gets input values from visitor input dialog
            String comment = "'"+getIntent().getStringExtra("Comment")+"'";
            String exhibition = "'"+getIntent().getStringExtra("Exhibition")+"'";

            //creates new object Comments from visitor input in dialog and insert to db
            Comments newComment = new Comments(comment, exhibition);
            dbManager.insert(newComment);
            }
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
        listView.setAdapter(adapter);

        // OnCLickListener For List Items - open edit/delete custom dialog
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = parent.getItemAtPosition(position).toString();
                Log.d(TAG, "onItemClick: You Clicked on " + name);

                TextView idTextView = (TextView) view.findViewById(R.id.commentsList_Id);
                TextView visitorCommentTextView = (TextView) view.findViewById(R.id.commentsList_visitorcomment);
                TextView visitorCommentExhibitionTextView = (TextView) view.findViewById(R.id.commentsList_exhibitionComment);

                String _id = idTextView.getText().toString();
                String comment = visitorCommentTextView.getText().toString();
                String visitorCommentExhibition = visitorCommentExhibitionTextView.getText().toString();

                //Log.i(TAG, _id);
                Log.i(TAG, comment);
                Log.i(TAG, visitorCommentExhibition);

                //sets custom dialog
                final Dialog dialog = new Dialog(VisitorCommentsListActivity.this);

                //sets the view for the custom dialog
                dialog.setContentView(R.layout.dialog_edit_delete_inputvisitor);

                //set custom dialog components
                final EditText commentsEditField = (EditText) dialog.findViewById(R.id.comments_inputVisitor_edit_delete_dialog);
                final EditText exhibitioncommentsEditField = (EditText) dialog.findViewById(R.id.exhibiton_comments_inputVisitor_edit_delete_dialog);
                final EditText _idEditField = (EditText) dialog.findViewById(R.id.id_inputVisitor_edit_delete_dialog);
                ImageButton cancelButton = (ImageButton) dialog.findViewById(R.id.cancelButton_edit_delete_dialog);
                Button editButton = (Button) dialog.findViewById(R.id.editButton_edit_delete_dialog);
                Button deleteButton = (Button) dialog.findViewById(R.id.deleteButton_edit_delete_dialog);

                //sets visitor input text to edit text fields
                commentsEditField.setText(comment);
                exhibitioncommentsEditField.setText(visitorCommentExhibition);
                _idEditField.setText(_id);

                // if cancel button is clicked, go back to list view
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                // if delete button is clicked, delete the entry from database
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int _id = Integer.parseInt(_idEditField.getText().toString());
                        dbManager.delete(_id);
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });

                // if editButton is clicked, edit the text and save into database
                editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String editComment = commentsEditField.getText().toString();
                        String editExhbitionComment = exhibitioncommentsEditField.getText().toString();
                        int _id = Integer.parseInt(_idEditField.getText().toString());

                        Comments newEditedComment = new Comments(_id, editComment, editExhbitionComment);
                        dbManager.update(newEditedComment);
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                //opens dialog
                dialog.show();
                adapter.notifyDataSetChanged();
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
