package com.example.lhi06.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    boolean count = true;

    @BindView(R.id.button) Button button;

    @BindView(R.id.button2) Button button2;

    @BindView(R.id.text) TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        // button.setOnClickListener(this);

        RxView.clicks(button).observeOn(AndroidSchedulers.mainThread()).subscribe(aVoid -> {

            Observable
                    .timer(1, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(ignored -> System.currentTimeMillis())
                    .map(timeInMilliseconds -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date(timeInMilliseconds)))
                    .subscribe(aLong -> {

                text.setText(aLong);

                count = !count;
            });

        });

        button2.setOnClickListener(this);



    }



    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button2:

                Intent main2 = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(main2);
                break;
        }

    }
}
