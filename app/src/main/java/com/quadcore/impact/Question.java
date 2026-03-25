package com.quadcore.impact;

import android.widget.RadioGroup;

import java.util.List;

public class Question {
    private final String questionText;
    private final List<String> options;
    private RadioGroup radioGroup;

    public Question(String questionText, List<String> options) {
        this.questionText = questionText;
        this.options = options;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setRadioGroup(RadioGroup radioGroup) {
        this.radioGroup = radioGroup;
    }

    public RadioGroup getRadioGroup() {
        return radioGroup;
    }
}
