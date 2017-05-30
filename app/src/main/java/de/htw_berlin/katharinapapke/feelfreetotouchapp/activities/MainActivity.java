package de.htw_berlin.katharinapapke.feelfreetotouchapp.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import de.htw_berlin.katharinapapke.feelfreetotouchapp.R;

public class
MainActivity extends AppCompatActivity {

    private ImageView artObjectPicture;
    private ImageButton makeToastButton;
    private ImageButton flipPictureButton;
    private ImageButton showInputDialogButton;
    private ImageButton flipToArtistPageButton;
    private Direction direction = Direction.HORIZONTAL;
    final Context context = this;

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set toolbar with icons
        Toolbar toolbar = (Toolbar) findViewById(R.id.menutoolbar);
        setSupportActionBar(toolbar);

        //get view elements
        artObjectPicture = (ImageView) findViewById(R.id.art_object_picture);
        makeToastButton = (ImageButton) findViewById(R.id.imageButtonMain_makeToast);
        flipPictureButton = (ImageButton) findViewById(R.id.imageButtonMain_flipPicture);
        showInputDialogButton = (ImageButton) findViewById(R.id.imageButtonMain_makeComment);
        flipToArtistPageButton =(ImageButton) findViewById(R.id.flipToNextPage);

        //show Toast-Test when Button is pressed
        makeToastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeToastButton.setImageResource(R.drawable.ic_info_black_24dp);
                toastMessage("This art object was all about creating new context with letters");
            }
        });

        //flip to next page with artist infos
        flipToArtistPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(MainActivity.this, ArtistInfoActivity.class);
                startActivity(intent);
            }
        });

        //flip Picture when Button is pressed

        flipPictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipPictureButton.setImageResource(R.drawable.ic_swap_vertical_circle_black_24dp);
                if (direction == Direction.HORIZONTAL)
                {
                    artObjectPicture.setImageBitmap(flip(BitmapFactory.decodeResource(getResources(), R.drawable.mainpicturesmall), Direction.VERTICAL));
                    direction = Direction.VERTICAL;
                }
                else
                {
                    artObjectPicture.setImageBitmap(flip(BitmapFactory.decodeResource(getResources(), R.drawable.mainpicturesmall), Direction.HORIZONTAL));
                    direction = Direction.HORIZONTAL;
                }
            }

        });

        //shows dialog for visitor input
        showInputDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialogButton.setImageResource(R.drawable.ic_comment_black_24dp);
                final Dialog dialog = new Dialog(context);

                //sets the view for the custom dialog
                dialog.setContentView(R.layout.dialog_inputvisitor);

                //set custom dialog components
                ImageButton dismissDialogButton = (ImageButton) dialog.findViewById(R.id.dismissInputVisitorDialogButton);
                Button addVisitorInputToListButton = (Button) dialog.findViewById(R.id.addVisitorInputToListButton);
                final EditText visitorInput = (EditText) dialog.findViewById(R.id.inputVisitorText);
                final EditText visitorInputExhibition= (EditText) dialog.findViewById(R.id.inputVisitorExhibition);

                // if button is clicked, close the custom dialog
                dismissDialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                // if button is clicked, show list with visitor comments
                addVisitorInputToListButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, VisitorCommentsListActivity.class)
                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        //get visitor comment
                        String comment = visitorInput.getText().toString();
                        //puts visitor comments into extra
                        if (comment.length() != 0)
                        {
                            intent.putExtra("Comment", comment);
                            visitorInput.setText("");

                        } else
                            {
                               toastMessage("Please leave a comment here.");
                            }

                        String exhibition = visitorInputExhibition.getText().toString();
                        if (exhibition.length() != 0)
                        {
                            intent.putExtra("Exhibition", exhibition);
                            visitorInputExhibition.setText("");

                        } else
                        {
                            toastMessage("Please leave a comment about the exhibition here.");
                        }
                        startActivity(intent);
                    }
                });

                //opens dialog
                dialog.show();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //custom dialog
                final Dialog dialog = new Dialog(context);

                //sets the view for the custom dialog
                dialog.setContentView(R.layout.dialog_custom);
                dialog.setTitle("About this App");

                //set custom dialog components
                ImageButton dialogButton = (ImageButton) dialog.findViewById(R.id.dismissDialogButton);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                //opens dialog
                dialog.show();


            }
        });
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
                Intent intent = new Intent(MainActivity.this, ArtistListActivity.class);
                startActivity(intent);
                return true;

            //if icon artistInfo get clicked the artistInfo Activity starts
            case R.id.action_artistInfo:;
                Intent intent2 = new Intent(MainActivity.this, ArtistInfoActivity.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    public enum Direction { VERTICAL, HORIZONTAL };

    public void flip(){
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run(){
                //code to do the HTTP request
            }
        });
        thread.start();

    }



    public static Bitmap flip(Bitmap src, Direction type) {
        Matrix matrix = new Matrix();

        if(type == Direction.VERTICAL) {
            matrix.preScale(1.0f, -1.0f);
        }
        else if(type == Direction.HORIZONTAL) {
            matrix.preScale(-1.0f, 1.0f);
        } else {
            return src;
        }
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
