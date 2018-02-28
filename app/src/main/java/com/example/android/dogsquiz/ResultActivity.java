package com.example.android.dogsquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView scoreTextView;
    private ImageButton restartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        scoreTextView = findViewById(R.id.score_textView);
        restartButton = findViewById(R.id.restartButton);

        int score = getIntent().getIntExtra(MainActivity.KEY_SCORE, 0);
        int size = getIntent().getIntExtra(MainActivity.KEY_SIZE, 10);
        scoreTextView.setText(score + "/" + size);

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
