package com.rick.swithcheroo;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
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
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.OutputStream;
import java.util.UUID;

public class SWiTHCheroo extends Activity  {

//    private TextView mTextMessage;

    Channel_obj ch [] = new Channel_obj[8];

    String ch_name;
    int ch_num;
    TextView textView;
    BluetoothDevice myBluetooth = null;
    BluetoothAdapter Badapter = null;
    final String address = "20:16:08:10:47:51";
    final String btname = "HC-06";
    BluetoothSocket btSocket = null;
    EditText editText;
    OutputStream mOut;
    TextView connect;
    UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");    // Capture our button from layout

    // Register the onClick listener with the implementation above

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
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
                case R.id.navigation_dashboard :
                    if(ch[ch_num-1].state != -1){
                        ch[ch_num-1].state = -1;
                        changeColor(ch_num-1);
                    }
                    switch (ch_num) {
                        case 1:
                            textView = (TextView) findViewById(R.id.channel_1_text);
                            textView.setText("Channel 1: ");
                            break;
                        case 2:
                            textView = (TextView) findViewById(R.id.channel_2_text);
                            textView.setText("Channel 2: ");
                            break;
                        case 3:
                            textView = (TextView) findViewById(R.id.channel_3_text);
                            textView.setText("Channel 3: ");
                            break;
                        case 4:
                            textView = (TextView) findViewById(R.id.channel_4_text);
                            textView.setText("Channel 4: ");
                            break;
                        case 5:
                            textView = (TextView) findViewById(R.id.channel_5_text);
                            textView.setText("Channel 5: ");
                            break;
                        case 6:
                            textView = (TextView) findViewById(R.id.channel_6_text);
                            textView.setText("Channel 6: ");
                            break;
                        case 7:
                            textView = (TextView) findViewById(R.id.channel_7_text);
                            textView.setText("Channel 7: ");
                            break;
                        default:
                            textView = (TextView) findViewById(R.id.channel_8_text);
                            textView.setText("Channel 8: ");
                    }
//                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
//                    mTextMessage.setText(R.string.title_notifications);
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
        ImageButton button2 = (ImageButton)findViewById(R.id.channel_3);
        ImageButton button3 = (ImageButton)findViewById(R.id.channel_4);
        ImageButton button4 = (ImageButton)findViewById(R.id.channel_5);
        ImageButton button5 = (ImageButton)findViewById(R.id.channel_6);
        ImageButton button6 = (ImageButton)findViewById(R.id.channel_7);
        ImageButton button7 = (ImageButton)findViewById(R.id.channel_8);
        makeChannels();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//        GameView gameView = new GameView(this);
        button.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                System.out.print("\nchannel 1\n");
                flipState(0);
                changeColor(0);
                if(ch[ch_num-1].state != -1) {
                    write("1");
                }
                //next_page(v);
            }
        });
        button1.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                System.out.print("\nchannel 2\n");
                flipState(1);
                changeColor(1);
                if(ch[ch_num-1].state != -1) {
                    write("2");
                }
                //next_page(v);
            }
        });
        button2.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                System.out.print("\nchannel 3\n");
                flipState(2);
                changeColor(2);
                if(ch[ch_num-1].state != -1) {
                    write("3");
                }
                //next_page(v);
            }
        });
        button3.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                System.out.print("\nchannel 4\n");
                flipState(3);
                changeColor(3);
                if(ch[ch_num-1].state != -1) {
                    write("4");
                }
                //next_page(v);
            }
        });
        button4.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                System.out.print("\nchannel 5\n");
                flipState(4);
                changeColor(4);
                if(ch[ch_num-1].state != -1) {
                    write("5");
                }
                //next_page(v);
            }
        });
        button5.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                System.out.print("\nchannel 6\n");
                flipState(5);
                changeColor(5);
                if(ch[ch_num-1].state != -1) {
                    write("6");
                }
                //next_page(v);
            }
        });
        button6.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                System.out.print("\nchannel 7\n");
                flipState(6);
                changeColor(6);
                if(ch[ch_num-1].state != -1) {
                    write("7");
                }
                //next_page(v);
            }
        });
        button7.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                System.out.print("\nchannel 8\n");
                flipState(7);
                changeColor(7);
                if(ch[ch_num-1].state != -1) {
                    write("8");
                }
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

         Switch bluetooth = (Switch) findViewById(R.id.bluetooth);
         connect = (TextView)findViewById(R.id.connected);
         Boolean switchState = bluetooth.isChecked();
         if(!switchState){
             try {

                 Badapter = BluetoothAdapter.getDefaultAdapter();
                 myBluetooth = Badapter.getRemoteDevice(address);
                 btSocket = myBluetooth.createRfcommSocketToServiceRecord(uuid);
                 BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                 btSocket.connect();
                 mOut = btSocket.getOutputStream();
                 connect.setText("Trying to connect bt");
             }catch (Exception e){
                System.out.println("Connected not successful.");
                connect.setText("Not connected");
             }
         }

    }

    public void write(String s){
        try {
            mOut.write(s.getBytes());
            System.out.println("Sent: "+s.getBytes());
            connect.setText("Writing: "+s.getBytes());
        }catch(Exception e){
            connect.setText("Cannot write");
        }
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
     public void flipState(int num){
         if(ch[num].state != -1){
             if(ch[num].state == 0){
                 ch[num].state = 1;
             }
             else{
                 ch[num].state = 0;
             }
         }
     }
}
