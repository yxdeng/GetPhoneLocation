package org.me.getphonelocation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This android is my first android program.
 * It can help you to know about the phone where it come from.
 *
 * @author yxdeng
 */
public class PhoneLocation extends Activity {

    private TextView resulttext;
    private TextView phone;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.getphone);

        //init the ui webkit
        findView();

        // deal with the listener
        dealWithListener();
    }

    /**
     * Use this method to init the members.
     */
    private void findView() {
        resulttext = (TextView) this.findViewById(R.id.result);
        phone = (TextView) this.findViewById(R.id.yourphone);
        submit = (Button) this.findViewById(R.id.widget34);
    }

    /**
     * Use this method to deal with all the listener
     */
    private void dealWithListener() {
        //deal with the click action.
        submit.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {
                resulttext.setText(getPlace(phone.getText()));
            }
        });
    }

    /**
     * Use the method to comunicate with the server to get info
     * @param phone the phone number
     * @return CharSequence the place info
     */
    private CharSequence getPlace(CharSequence phone) {
        System.out.println(phone);
        CharSequence rep = "";
        try {
            //communicate with the server
            URL url = new URL(this.getString(R.string.url) + phone);
            HttpURLConnection hcon = (HttpURLConnection) url.openConnection();
            hcon.setDoOutput(true);
            hcon.setDoInput(true);
            hcon.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(hcon.getInputStream(), this.getString(R.string.charset)));
            String temp = "";
            while (reader.read() != -1) {
                temp += reader.readLine();

            }
            reader.close();

            //anlisize the stream
            int begin = temp.indexOf(this.getString(R.string.beginindex));
            int end = temp.lastIndexOf(this.getString(R.string.endindex));
            temp = temp.substring(begin, end);
            String[] abc = temp.split("<|>|:");
            if (abc.length <= 5) {
                rep = rep + abc[2];
            } else {
                rep = rep + abc[2] + "\n" + abc[4] + "\n" + abc[6];
            }
        } catch (Exception ex) {
            Logger.getLogger(PhoneLocation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rep;
    }
}
