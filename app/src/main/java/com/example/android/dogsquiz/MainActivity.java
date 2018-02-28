package com.example.android.dogsquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_INDEX = "index";
    public static final String KEY_SCORE = "score";
    public static final String KEY_SIZE = "size";

    private ProgressBar progressBar;
    private TextView questionTextView;
    private ImageView questionImageView;
    private RadioGroup radioGroup;
    private RadioButton optionA;
    private RadioButton optionB;
    private RadioButton optionC;
    private RadioButton optionD;
    private Button bottomButton;

    private List<Question> questions;
    private int currentIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_INDEX);
            score = savedInstanceState.getInt(KEY_SCORE);
        }

        initializeData();

        progressBar = findViewById(R.id.progressBar);
        questionTextView = findViewById(R.id.question_textView);
        questionImageView = findViewById(R.id.question_imageView);
        radioGroup = findViewById(R.id.question_radioGroup);
        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);
        bottomButton = findViewById(R.id.question_button);

        progressBar.setMax(questions.size());

        bottomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
                currentIndex++;
                updateQuestion();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                bottomButton.setEnabled(true);
            }
        });
        updateQuestion();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX, currentIndex);
        outState.putInt(KEY_SCORE, score);
    }

    private void checkAnswer() {
        int index = radioGroup.indexOfChild(radioGroup.findViewById(radioGroup.getCheckedRadioButtonId()));

        boolean isCorrect = questions.get(currentIndex).getAnswers().get(index).isCorrect();
        if (isCorrect) {
            Toast.makeText(this, R.string.correct, Toast.LENGTH_SHORT).show();
            score++;
        }
        else Toast.makeText(this, R.string.incorrect, Toast.LENGTH_SHORT).show();
    }

    private void updateQuestion () {
        if (currentIndex < questions.size()) {
            questionImageView.setImageResource(questions.get(currentIndex).getImage());

            optionA.setText(questions.get(currentIndex).getAnswers().get(0).getTextResId());
            optionB.setText(questions.get(currentIndex).getAnswers().get(1).getTextResId());
            optionC.setText(questions.get(currentIndex).getAnswers().get(2).getTextResId());
            optionD.setText(questions.get(currentIndex).getAnswers().get(3).getTextResId());

            radioGroup.clearCheck();
            bottomButton.setEnabled(false);
            progressBar.setProgress(currentIndex);
        } else {
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra(KEY_SCORE, score);
            intent.putExtra(KEY_SIZE, questions.size());
            startActivity(intent);
            finish();
        }
    }

    private void initializeData () {
        questions = new ArrayList<>();

        List<Answer> question1 = new ArrayList<>();
        question1.add(new Answer(R.string.dog_bulldog, false));
        question1.add(new Answer(R.string.dog_beagle, true));
        question1.add(new Answer(R.string.dog_poodle, false));
        question1.add(new Answer(R.string.dog_siberian_husky, false));
        questions.add(new Question(R.drawable.dog_beagle, question1));

        List<Answer> question2 = new ArrayList<>();
        question2.add(new Answer(R.string.dog_american_foxhound, false));
        question2.add(new Answer(R.string.dog_chihuahua, false));
        question2.add(new Answer(R.string.dog_yorkshire_terrier, true));
        question2.add(new Answer(R.string.dog_japanese_chin, false));
        questions.add(new Question(R.drawable.dog_yorkshire_terrier, question2));

        List<Answer> question3 = new ArrayList<>();
        question3.add(new Answer(R.string.dog_siberian_husky, false));
        question3.add(new Answer(R.string.dog_labrador, false));
        question3.add(new Answer(R.string.dog_bulldog, false));
        question3.add(new Answer(R.string.dog_boxer, true));
        questions.add(new Question(R.drawable.dog_boxer, question3));

        List<Answer> question4 = new ArrayList<>();
        question4.add(new Answer(R.string.dog_siberian_husky, true));
        question4.add(new Answer(R.string.dog_collie, false));
        question4.add(new Answer(R.string.dog_maltese, false));
        question4.add(new Answer(R.string.dog_rottweiler, false));
        questions.add(new Question(R.drawable.dog_siberian_huskie, question4));

        List<Answer> question5 = new ArrayList<>();
        question5.add(new Answer(R.string.dog_german_shepherd, false));
        question5.add(new Answer(R.string.dog_dachshund, false));
        question5.add(new Answer(R.string.dog_bulldog, true));
        question5.add(new Answer(R.string.dog_greyhound, false));
        questions.add(new Question(R.drawable.dog_bulldog, question5));

        List<Answer> question6 = new ArrayList<>();
        question6.add(new Answer(R.string.dog_fox_terrier, false));
        question6.add(new Answer(R.string.dog_german_shepherd, false));
        question6.add(new Answer(R.string.dog_rottweiler, true));
        question6.add(new Answer(R.string.dog_greyhound, false));
        questions.add(new Question(R.drawable.dog_rottweiler, question6));

        List<Answer> question7 = new ArrayList<>();
        question7.add(new Answer(R.string.dog_french_bulldog, true));
        question7.add(new Answer(R.string.dog_poodle, false));
        question7.add(new Answer(R.string.dog_japanese_chin, false));
        question7.add(new Answer(R.string.dog_maltese, false));
        questions.add(new Question(R.drawable.dog_french_bulldog, question7));

        List<Answer> question8 = new ArrayList<>();
        question8.add(new Answer(R.string.dog_chow_chow, false));
        question8.add(new Answer(R.string.dog_chihuahua, false));
        question8.add(new Answer(R.string.dog_labrador, false));
        question8.add(new Answer(R.string.dog_poodle, true));
        questions.add(new Question(R.drawable.dog_poodle, question8));

        List<Answer> question9 = new ArrayList<>();
        question9.add(new Answer(R.string.dog_fox_terrier, false));
        question9.add(new Answer(R.string.dog_german_shepherd, true));
        question9.add(new Answer(R.string.dog_american_foxhound, false));
        question9.add(new Answer(R.string.dog_maltese, false));
        questions.add(new Question(R.drawable.dog_german_shepherd, question9));

        List<Answer> question10 = new ArrayList<>();
        question10.add(new Answer(R.string.dog_chow_chow, false));
        question10.add(new Answer(R.string.dog_dalmatian, false));
        question10.add(new Answer(R.string.dog_labrador, true));
        question10.add(new Answer(R.string.dog_greyhound, false));
        questions.add(new Question(R.drawable.dog_labrador, question10));

    }
}
