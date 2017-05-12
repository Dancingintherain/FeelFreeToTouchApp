package de.htw_berlin.katharinapapke.feelfreetotouchapp.activities;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

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
        dbManager = new DBManager(this);
        dbManager.open();
        Comments comment = new Comments("Hallo", "Hallo");
        dbManager.insert(comment);
        setContentView(R.layout.activity_visitor_comments_list);
        listView = (ListView) findViewById(R.id.visitor_comments_list_view);
        populateListView();

    }

    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView");
/*
        //get the data and append to a list
        Cursor cursor = dbManager.fetch();
        ArrayList<String> listData = new ArrayList<>();
        while(cursor.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            listData.add(cursor.getString(1));
        }
        //create the list adapter and set the adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(adapter);
*/
        final String[] from = new String[] { DBHandler.ID,
                DBHandler.KEY_COMMENT, DBHandler.KEY_VISITOR_AGE};

        final int[] to = new int[] { R.id.commentsList_Id, R.id.commentsList_visitorcomment, R.id.commentsList_age };

        listView = (ListView) findViewById(R.id.visitor_comments_list_view);
        //listView.setEmptyView(findViewById(R.id.empty));

        Cursor cursor = dbManager.fetch();
        adapter = new SimpleCursorAdapter(this, R.layout.activity_visitor_commentsitem, cursor, from, to, 0);
        //adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);
    }


        /*@Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
            TextView idTextView = (TextView) view.findViewById(R.id.id);
            TextView titleTextView = (TextView) view.findViewById(R.id.title);
            TextView descTextView = (TextView) view.findViewById(R.id.desc);

            String id = idTextView.getText().toString();
            String title = titleTextView.getText().toString();
            String desc = descTextView.getText().toString();

            Intent modify_intent = new Intent(getApplicationContext(), ModifyCountryActivity.class);
            modify_intent.putExtra("title", title);
            modify_intent.putExtra("desc", desc);
            modify_intent.putExtra("id", id);

            startActivity(modify_intent);
        }*/
    }
