package com.rick.swithcheroo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class SWiTHCheroo extends Activity  {

    private TextView mTextMessage;
    // Capture our button from layout

    // Register the onClick listener with the implementation above

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);

                    return true;
                case R.id.navigation_dashboard :
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }




    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swi_thcheroo);
        ImageButton button = (ImageButton)findViewById(R.id.channel_1);
        ImageButton button1 = (ImageButton)findViewById(R.id.channel_2);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//        GameView gameView = new GameView(this);
        button.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                System.out.print("\nchannel 1\n");
                next_page(v);
            }
        });
        button1.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                System.out.print("\nchannel 2\n");
                next_page(v);
            }
        });



        Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Channel_Number, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                System.out.println(item.toString());
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
    public void next_page(View v) {
        Intent intent = new Intent(this, SWiTHCheroo.class);
        startActivity(intent);
    }


}
