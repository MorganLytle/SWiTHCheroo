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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class SWiTHCheroo extends Activity  {

    private TextView mTextMessage;

    Channel_obj ch [] = new Channel_obj[8];

    String ch_name;
    int ch_num;
    TextView textView;

    EditText editText;
    // Capture our button from layout

    // Register the onClick listener with the implementation above

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    if (ch[ch_num - 1].state == -1) {
                        ch[ch_num - 1].state = 0;
                        setName();
                        System.out.println("name: " + ch_name);
                        ch[ch_num - 1].setName(ch_name);

                        changeColor(ch_num - 1);
                        System.out.println("ch num: " + (ch_num) + "ch name: " + ch[ch_num - 1].name);

                        switch (ch_num) {

                            case 1:
                                textView = (TextView) findViewById(R.id.channel_1_text);
                                textView.setText(ch[ch_num - 1].name);
                                break;
                            case 2:
                                textView = (TextView) findViewById(R.id.channel_2_text);
                                textView.setText(ch[ch_num - 1].name);
                                System.out.println("channel name" + ch[ch_num - 1].name);
                                break;
                            case 3:
                                textView = (TextView) findViewById(R.id.channel_3_text);
                                textView.setText(ch[ch_num - 1].name);
                                break;
                            case 4:
                                textView = (TextView) findViewById(R.id.channel_4_text);
                                textView.setText(ch[ch_num - 1].name);
                                break;
                            case 5:
                                textView = (TextView) findViewById(R.id.channel_5_text);
                                textView.setText(ch[ch_num - 1].name);
                                break;
                            case 6:
                                textView = (TextView) findViewById(R.id.channel_6_text);
                                textView.setText(ch[ch_num - 1].name);
                                break;
                            case 7:
                                textView = (TextView) findViewById(R.id.channel_7_text);
                                textView.setText(ch[ch_num - 1].name);
                                break;
                            default:
                                textView = (TextView) findViewById(R.id.channel_8_text);
                                textView.setText(ch[ch_num - 1].name);
                        }
                    }
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    for (int i = 0; i < 8; i++) {
                        ch[i].clearState();
                        changeColor(i);
                        ch[i].setName(null);
                    }
                    textView = (TextView) findViewById(R.id.channel_1_text);
                    textView.setText("Channel 1: ");
                    textView = (TextView) findViewById(R.id.channel_2_text);
                    textView.setText("Channel 2: ");
                    textView = (TextView) findViewById(R.id.channel_3_text);
                    textView.setText("Channel 3: ");
                    textView = (TextView) findViewById(R.id.channel_4_text);
                    textView.setText("Channel 4: ");
                    textView = (TextView) findViewById(R.id.channel_5_text);
                    textView.setText("Channel 5: ");
                    textView = (TextView) findViewById(R.id.channel_6_text);
                    textView.setText("Channel 6: ");
                    textView = (TextView) findViewById(R.id.channel_7_text);
                    textView.setText("Channel 7: ");
                    textView = (TextView) findViewById(R.id.channel_8_text);
                    textView.setText("Channel 8: ");
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
        makeChannels();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//        GameView gameView = new GameView(this);
        button.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                System.out.print("\nchannel 1\n");
                changeColor(0);
                next_page(v);
            }
        });
        button1.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                System.out.print("\nchannel 2\n");
                if(ch[1].state != -1){
                    if(ch[1].state == 0){
                        ch[1].state = 1;
                    }
                    else{
                        ch[1].state = 0;
                    }
                }
                changeColor(1);
                //next_page(v);
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
                String temp = item.toString();
                ch_num = Integer.parseInt(temp);
                System.out.println("ch num: "+ch_num);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

         editText = (EditText) findViewById(R.id.editText2);

    }

    public void setName(){
        ch_name = editText.getText().toString();
    }

    public void next_page(View v) {
        Intent intent = new Intent(this, SWiTHCheroo.class);
        startActivity(intent);
    }
    public void makeChannels(){
        for(int i = 0 ; i < 8;i++){
            ch[i] = new Channel_obj(i+1);
        }
    }
    public void changeColor(int num){
        ImageButton button1;
        switch(num) {
            case 0:
                button1 = (ImageButton) findViewById(R.id.channel_1);
                break;
            case 1:
                button1 = (ImageButton) findViewById(R.id.channel_2);
                break;
            case 2:
                button1 = (ImageButton) findViewById(R.id.channel_3);
                break;
            case 3:
                button1 = (ImageButton) findViewById(R.id.channel_4);
                break;
            case 4:
                button1 = (ImageButton) findViewById(R.id.channel_5);
                break;
            case 5:
                button1 = (ImageButton) findViewById(R.id.channel_6);
                break;
            case 6:
                button1 = (ImageButton) findViewById(R.id.channel_7);
                break;
            case 7:
                button1 = (ImageButton) findViewById(R.id.channel_8);
                break;
            default:
                button1 = (ImageButton) findViewById(R.id.channel_8);
        }
        if(ch[num].state == -1) { //grey
            button1.setImageResource(R.drawable.power_symbol_grey);
        }
        else if(ch[num].state == 0) {//red
            button1.setImageResource(R.drawable.power_symbol_red);
        }
        else { //green
            button1.setImageResource(R.drawable.power_symbol_green);
        }


        }


}
