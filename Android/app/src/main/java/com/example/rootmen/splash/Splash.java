package com.example.rootmen.splash;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;

import com.deange.ropeprogressview.RopeProgressBar;
import com.example.rootmen.R;

public class Splash extends AppCompatActivity implements
        SeekBar.OnSeekBarChangeListener{
    private EditText mEditText;
    private RopeProgressBar mRopeProgressBar;
    private Handler handler;
    private int g = 9;
    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        mRopeProgressBar = findRopeProgressView(getWindow().getDecorView());
        new Thread(new Runnable() {
            public void run() {
                new MyAsyncTask().execute();
            }}).start();
    }

    protected RopeProgressBar getRopeProgressBar() {
        return mRopeProgressBar;
    }

    private RopeProgressBar findRopeProgressView(final View view) {
        if (view instanceof RopeProgressBar) {
            return (RopeProgressBar) view;

        } else if (view instanceof ViewGroup) {
            ViewGroup p = ((ViewGroup) view);
            View child;
            for (int i = 0; i < p.getChildCount(); i++) {
                if ((child = findRopeProgressView(p.getChildAt(i))) != null) {
                    return (RopeProgressBar) child;
                }
            }
        }

        return null;
    }

    @Override
    public void onProgressChanged(final SeekBar seekBar, final int progress, final boolean user) {
        mRopeProgressBar.setProgress(progress);
    }

    @Override
    public void onStartTrackingTouch(final SeekBar seekBar) {
        mRopeProgressBar.defer();
    }

    @Override
    public void onStopTrackingTouch(final SeekBar seekBar) {
        mRopeProgressBar.endDefer();
    }

    private class MyAsyncTask extends AsyncTask<String, Integer, Integer> {
        @Override
        protected void onProgressUpdate(Integer... progress) {
            g++;
            if(g==100) g=0;
            mRopeProgressBar.animateProgress(100);
        }


        @Override
        protected Integer doInBackground(String... parameter) {
           publishProgress();
           return 0;
        }
    }
}
