/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.me.getphonelocation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 *
 * @author yxdeng
 */
public class MainActivity extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        // ToDo add your GUI initialization code here
        Intent it = new Intent(this,PhoneLocation.class);
        Bundle bundle = new Bundle();
        it.putExtras(bundle);
        this.startActivity(it);
    }

}
