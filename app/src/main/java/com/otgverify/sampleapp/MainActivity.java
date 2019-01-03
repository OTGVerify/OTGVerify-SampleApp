package com.otgverify.sampleapp;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.otgverify.otg_verify.View.OTGMobVerifyEditText;
import com.otgverify.otg_verify.View.OnOtgResponseReceiveListener;

public class MainActivity extends AppCompatActivity {

    private OTGMobVerifyEditText otgMobVerifyEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        otgMobVerifyEditText = (OTGMobVerifyEditText) findViewById(R.id.editText);
        otgMobVerifyEditText.setClientId("YOUR_CLIENT_ID");
        otgMobVerifyEditText.setClientSecret("YOUR_CLIENT_SECRET");

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            otgMobVerifyEditText.setOnOtgResponseReceiveListener(new OnOtgResponseReceiveListener() {
                @Override
                public void onOtgResponseReceive(boolean isVerify) {
                    if (isVerify) {
                        //Todo: Number is verified by OTGVerify, Do your stuff here
                        Toast.makeText(MainActivity.this,"Verified By OTG",Toast.LENGTH_SHORT).show();
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //Todo: Number is not verified by OTGVerify, You can go with old OTP process.
                                Toast.makeText(MainActivity.this,"Not Verified",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });
        }



    }
}
