/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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

//    private OnClickListener
    private CharSequence getPlace(CharSequence phone) {
        System.out.println(phone);
        CharSequence rep = "";
        try {
            URL url = new URL("http://wap.ip138.com/sim.asp?mobile=" + phone);
            HttpURLConnection hcon = (HttpURLConnection) url.openConnection();
//            hcon.getInputStream();
            // http正文内，因此需要设为true, 默认情况下是false;
            hcon.setDoOutput(true);

            // 设置是否从httpUrlConnection读入，默认情况下是true;
            hcon.setDoInput(true);
            hcon.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(hcon.getInputStream(), "utf-8"));
            String temp = "";
            while (reader.read() != -1) {
                temp += reader.readLine();

            }
            reader.close();
            int begin = temp.indexOf("title=");
            int end = temp.lastIndexOf("ip138.com");
            System.out.println(temp);
//            resulttext.setText(rep);
            temp = temp.substring(begin, end);
            String[] abc = temp.split("<|>|:");
//            for (int i = 0; i < abc.length; i++) {
//                System.out.println(i + ":" + abc[i]);
//            }
            if (abc.length <= 5) {
                rep = rep + abc[2];
            } else {
                rep = rep + abc[2] + "\n" + abc[4] + "\n" + abc[6];
            }
//            rep = rep + temp;
//            System.out.println(rep);

        } catch (Exception ex) {
            Logger.getLogger(PhoneLocation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rep;
    }
}
