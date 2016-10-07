package com.example.boss.lesson3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    ViewGroup scrollView;
    ViewGroup relativeLayout;
    TextView poetryTextView;
    TextView tapText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String poetry = readTextFile("poetry.txt");

        poetryTextView = (TextView) findViewById(R.id.poetryTextView);
        tapText = (TextView) findViewById(R.id.tapText);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        scrollView = (ScrollView) findViewById(R.id.scrollView);

        poetryTextView.setText(poetry);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scrollView.getVisibility() != View.VISIBLE) {
                    tapText.setText("");
                    relativeLayout.setClickable(false);
                    scrollView.setVisibility(View.VISIBLE);
                    poetryTextView.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.slide_in_left));
                }
            }
        });
    }

    public String readTextFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open(fileName)));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
