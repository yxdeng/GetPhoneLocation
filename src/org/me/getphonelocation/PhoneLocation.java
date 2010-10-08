/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.getphonelocation;

import android.app.Activity;
import android.os.Bundle;
//import android.sax.Element;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
//import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.SAXParser;
//import javax.xml.parsers.SAXParserFactory;
//import org.xml.sax.InputSource;
//import org.xml.sax.XMLReader;
//import org.w3c.dom.Document;

/**
 *
 * @author yxdeng
 */
public class PhoneLocation extends Activity {

    private TextView resulttext;
    private TextView phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.getphone);

        resulttext = (TextView) this.findViewById(R.id.result);
        phone = (TextView) this.findViewById(R.id.yourphone);
        Button submit = (Button) this.findViewById(R.id.widget34);

        submit.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {
//                HttpURLConnection hcon = new HttpURLConnection("");
//                throw new UnsupportedOperationException("Not supported yet.");
//                getPlace();
                resulttext.setText(getPlace(phone.getText()));
            }
        });
//        DatePicker dp = (DatePicker) this.findViewById(R.id.mygalendar);
//        DateSorter ds = new DateSorter();
//        dp.setClipChildren(true);
//        dp.


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
//            int responseCode = hcon.getResponseCode();
//
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//
//                InputStream in = hcon.getInputStream();
//
////                DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
////
////                DocumentBuilder db = dbfactory.newDocumentBuilder();
////                Document dom=db.parse(in);
////                XMLContentHandler handler = new XMLContentHandler();
//                XMLReader reader = null;
//                InputSource is = null;
//                SAXParserFactory parserFactory = SAXParserFactory.newInstance();
//                SAXParser saxParser = parserFactory.newSAXParser();
//                reader = saxParser.getXMLReader();
////                Element docEle=dom.getDocumentElement();
//                is = new InputSource((new InputStreamReader(hcon.getInputStream(), "utf-8")));
//                reader.parse(is);
////                reader.getEntityResolver()
//
//            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(hcon.getInputStream(), "utf-8"));
            String temp = "";
            while (reader.read() != -1) {
//                System.out.println(reader.readLine());
                temp += reader.readLine();

            }
            reader.close();
            int begin =temp.indexOf("title=");
            int end = temp.lastIndexOf("ip138.com");
            System.out.println(temp);
//            resulttext.setText(rep);
            temp = temp.substring(begin, end);
            String [] abc =temp.split("<|>|:");
            for(int i = 0 ;i<abc.length;i++){
                System.out.println(i+":"+abc[i]);
            }
            if(abc.length<=5){
                rep = rep + abc[2];
            }else{
                rep = rep +abc[2]+"\n"+abc[4]+"\n"+abc[6];
            }
//            rep = rep + temp;
            System.out.println(rep);

        } catch (IOException ex) {
            System.out.println("faile1");
            Logger.getLogger(PhoneLocation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            System.out.println("faile2");
            Logger.getLogger(PhoneLocation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rep;
    }
}
