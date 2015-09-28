package com.example.kb50group6.userapplication;

import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class OfficeActivity extends AppCompatActivity {

    Uri contacts = ContactsContract.Contacts.CONTENT_URI;
    String URL = "content://com.example.user.contentprovider.AS3DB/offices";

    Uri offices = Uri.parse(URL);
    SimpleCursorAdapter adapter;
    Cursor c;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office);

        Bundle extras = getIntent().getExtras();
        String position="";

        if (extras != null) {
            position = extras.getString("position");
            // and get whatever type user account id is
        }

        TextView textViewaddress = (TextView)findViewById(R.id.textViewAddress);
        TextView textviewNumber = (TextView)findViewById(R.id.textViewNumber);



        CursorLoader cursorLoader = new CursorLoader(
                this,
                offices,
                null,
                null,
                null,
                null
        );
        c = cursorLoader.loadInBackground();
        /*int[] views = new int[]{R.id.contactName,R.id.contactID};
        String[] columns = new String[]{
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts._ID
        };


        adapter = new SimpleCursorAdapter(
                this,
                R.layout.activity_office,
                c,
                columns,
                views,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
                */
        //c.moveToPosition(Integer.parseInt(position));
        c.moveToPosition(Integer.parseInt(position));
        textViewaddress.setText(c.getString(c.getColumnIndex(offices+"/o_city")));
        textviewNumber.setText(c.getString(c.getColumnIndex(offices+"/o_telnr")));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_office, menu);
        return true;
    }

    @Override
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
    }

    public void onCallClick(View v)
    {
        Toast.makeText(this,"Making call...",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_CALL,
                Uri.parse("tel:"+c.getString(c.getColumnIndex(ContactsContract.Contacts._ID))));
        startActivity(intent);
    }
}
