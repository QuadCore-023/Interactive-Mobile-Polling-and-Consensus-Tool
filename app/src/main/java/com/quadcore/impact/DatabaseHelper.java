package com.quadcore.impact;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Name and Version
    private static final String DATABASE_NAME = "FormApp.db";
    private static final int DATABASE_VERSION = 1;

    // Table Names
    public static final String TABLE_FORMS = "Forms";
    public static final String TABLE_QUESTIONS = "Questions";
    public static final String TABLE_OPTIONS = "Options";

    // Forms Table Columns
    public static final String COLUMN_FORM_ID = "id";
    public static final String COLUMN_FORM_TITLE = "title";
    public static final String COLUMN_FORM_DESCRIPTION = "description";

    // Questions Table Columns
    public static final String COLUMN_QUESTION_ID = "id";
    public static final String COLUMN_QUESTION_TEXT = "question_text";
    public static final String COLUMN_QUESTION_FORM_ID = "form_id";

    // Options Table Columns
    public static final String COLUMN_OPTION_ID = "id";
    public static final String COLUMN_OPTION_TEXT = "option_text";
    public static final String COLUMN_OPTION_QUESTION_ID = "question_id";

    // SQL Statements
    private static final String CREATE_TABLE_FORMS = "CREATE TABLE " + TABLE_FORMS + " (" +
            COLUMN_FORM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_FORM_TITLE + " TEXT NOT NULL, " +
            COLUMN_FORM_DESCRIPTION + " TEXT NOT NULL" +
            ");";

    private static final String CREATE_TABLE_QUESTIONS = "CREATE TABLE " + TABLE_QUESTIONS + " (" +
            COLUMN_QUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_QUESTION_TEXT + " TEXT NOT NULL, " +
            COLUMN_QUESTION_FORM_ID + " INTEGER NOT NULL, " +
            "FOREIGN KEY(" + COLUMN_QUESTION_FORM_ID + ") REFERENCES " + TABLE_FORMS + "(" + COLUMN_FORM_ID + ")" +
            ");";

    private static final String CREATE_TABLE_OPTIONS = "CREATE TABLE " + TABLE_OPTIONS + " (" +
            COLUMN_OPTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_OPTION_TEXT + " TEXT NOT NULL, " +
            COLUMN_OPTION_QUESTION_ID + " INTEGER NOT NULL, " +
            "FOREIGN KEY(" + COLUMN_OPTION_QUESTION_ID + ") REFERENCES " + TABLE_QUESTIONS + "(" + COLUMN_QUESTION_ID + ")" +
            ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create tables
        db.execSQL(CREATE_TABLE_FORMS);
        db.execSQL(CREATE_TABLE_QUESTIONS);
        db.execSQL(CREATE_TABLE_OPTIONS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop existing tables if database version is updated
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OPTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FORMS);
        onCreate(db);
    }
}
