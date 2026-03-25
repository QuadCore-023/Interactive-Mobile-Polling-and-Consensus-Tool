package com.quadcore.impact;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AnswerFormActivity extends AppCompatActivity {
    private LinearLayout questionsLayout;
    private Button submitButton;
    private List<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_form);

        questionsLayout = findViewById(R.id.questions_layout);
        submitButton = findViewById(R.id.submit_button);

        // Get form details from intent
        String formTitle = getIntent().getStringExtra("formTitle");
        String formCreator = getIntent().getStringExtra("formCreator");

        // Simulate fetching questions for the selected form
        questions = fetchQuestionsForForm(formTitle);

        // Dynamically add questions to the layout
        for (Question question : questions) {
            addQuestionToLayout(question);
        }

        submitButton.setOnClickListener(v -> {
            if (validateAnswers()) {
                Toast.makeText(this, "Form submitted successfully!", Toast.LENGTH_SHORT).show();
                finish(); // Close the activity
            }
        });
    }

    private List<Question> fetchQuestionsForForm(String formTitle) {
        // Replace this with actual database or API call
        List<Question> sampleQuestions = new ArrayList<>();
        sampleQuestions.add(new Question("What is your favorite color?", List.of("Red", "Blue", "Green", "Yellow")));
        sampleQuestions.add(new Question("What is your age group?", List.of("Under 18", "18-25", "26-40", "40+")));
        return sampleQuestions;
    }

    private void addQuestionToLayout(Question question) {
        // Add question text
        TextView questionText = new TextView(this);
        questionText.setText(question.getQuestionText());
        questionText.setTextSize(18);
        questionsLayout.addView(questionText);

        // Add RadioGroup for options
        RadioGroup radioGroup = new RadioGroup(this);
        for (String option : question.getOptions()) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(option);
            radioGroup.addView(radioButton);
        }
        questionsLayout.addView(radioGroup);
        question.setRadioGroup(radioGroup); // Associate RadioGroup with the question
    }

    private boolean validateAnswers() {
        for (Question question : questions) {
            RadioGroup radioGroup = question.getRadioGroup();
            if (radioGroup.getCheckedRadioButtonId() == -1) { // No option selected
                Toast.makeText(this, "Please answer all questions!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }
}
