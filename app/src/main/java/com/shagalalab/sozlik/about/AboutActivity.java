package com.shagalalab.sozlik.about;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.shagalalab.sozlik.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView about = (TextView) findViewById(R.id.text_about);
        about.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
