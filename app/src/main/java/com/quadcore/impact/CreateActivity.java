package com.quadcore.impact;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateActivity extends AppCompatActivity {

    private EditText titleField, descriptionField;
    private LinearLayout questionsLayout;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        // Initialize SQLite Database
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        titleField = findViewById(R.id.editTextTitle);
        descriptionField = findViewById(R.id.editTextDescription);
        questionsLayout = findViewById(R.id.questionsLayout);

        Button addQuestionButton = findViewById(R.id.buttonAddQuestion);
        Button publishButton = findViewById(R.id.buttonPublish);

        addQuestionButton.setOnClickListener(v -> openAddQuestionDialog());

        publishButton.setOnClickListener(v -> saveFormToDatabase());
    }

    @SuppressLint("SetTextI18n")
    private void openAddQuestionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Question");

        LinearLayout dialogLayout = new LinearLayout(this);
        dialogLayout.setOrientation(LinearLayout.VERTICAL);

        final EditText questionField = new EditText(this);
        questionField.setHint("Enter Question");
        dialogLayout.addView(questionField);

        final LinearLayout optionsLayout = new LinearLayout(this);
        optionsLayout.setOrientation(LinearLayout.VERTICAL);

        Button addOptionButton = new Button(this);
        addOptionButton.setText("Add Option");
        addOptionButton.setOnClickListener(v -> {
            EditText optionField = new EditText(CreateActivity.this);
            optionField.setHint("Enter Option");
            optionsLayout.addView(optionField);
        });

        dialogLayout.addView(optionsLayout);
        dialogLayout.addView(addOptionButton);

        builder.setView(dialogLayout);

        builder.setPositiveButton("Save Question", (dialog, which) -> {
            String questionText = questionField.getText().toString().trim();
            if (!TextUtils.isEmpty(questionText)) {
                TextView questionView = new TextView(CreateActivity.this);
                questionView.setText(questionText);
                questionsLayout.addView(questionView);

                for (int i = 0; i < optionsLayout.getChildCount(); i++) {
                    EditText optionField = (EditText) optionsLayout.getChildAt(i);
                    String optionText = optionField.getText().toString().trim();
                    if (!TextUtils.isEmpty(optionText)) {
                        TextView optionView = new TextView(CreateActivity.this);
                        optionView.setText(" - " + optionText);
                        questionsLayout.addView(optionView);
                    }
                }
            } else {
                Toast.makeText(CreateActivity.this, "Question cannot be empty!", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", null);
        builder.create().show();
    }

    private void saveFormToDatabase() {
        String title = titleField.getText().toString().trim();
        String description = descriptionField.getText().toString().trim();

        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description)) {
            Toast.makeText(this, "Title and Description are required!", Toast.LENGTH_SHORT).show();
            return;
        }

        ContentValues formValues = new ContentValues();
        formValues.put("title", title);
        formValues.put("description", description);

        long formId = database.insert("Forms", null, formValues);
        if (formId != -1) {
            Toast.makeText(this, "Form published successfully!", Toast.LENGTH_SHORT).show();

            // Navigate to FormListActivity after publishing the form
            Intent intent = new Intent(CreateActivity.this, HomeActivity.class);
            startActivity(intent);

            finish(); // Close CreateActivity after successful publish
        } else {
            Toast.makeText(this, "Error saving form. Try again!", Toast.LENGTH_SHORT).show();
        }
    }
}
