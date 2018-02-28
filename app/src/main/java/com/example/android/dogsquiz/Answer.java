package com.example.android.dogsquiz;

public class Answer {
    private int textResId;
    private boolean correct;

    public Answer(int textResId, boolean correct) {
        this.textResId = textResId;
        this.correct = correct;
    }

    public int getTextResId() {
        return textResId;
    }

    public boolean isCorrect() {
        return correct;
    }
}
