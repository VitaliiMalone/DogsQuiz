package com.example.android.dogsquiz;

import java.util.List;

public class Question {
    private int image;
    private List<Answer> answers;

    public Question(int image, List<Answer> answers) {
        this.image = image;
        this.answers = answers;
    }

    public int getImage() {
        return image;
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
