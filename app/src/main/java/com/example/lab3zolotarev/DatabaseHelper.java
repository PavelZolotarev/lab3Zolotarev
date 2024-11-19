package com.example.lab3zolotarev;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "students.db";
    private static final int DATABASE_VERSION = 2; // Увеличена версия БД

    public static final String TABLE_NAME = "Одногруппники";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_LAST_NAME = "Фамилия";
    public static final String COLUMN_FIRST_NAME = "Имя";
    public static final String COLUMN_MIDDLE_NAME = "Отчество";
    public static final String COLUMN_TIMESTAMP = "ВремяДобавления";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Создание таблицы с новой структурой
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_LAST_NAME + " TEXT, " +
                COLUMN_FIRST_NAME + " TEXT, " +
                COLUMN_MIDDLE_NAME + " TEXT, " +
                COLUMN_TIMESTAMP + " TEXT);";
        db.execSQL(createTable);

        // Добавление начальных данных
        insertInitialData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Удаление старой таблицы
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Создание новой таблицы
        onCreate(db);
    }

    // Метод для добавления начальных данных
    private void insertInitialData(SQLiteDatabase db) {
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_LAST_NAME + ", " +
                COLUMN_FIRST_NAME + ", " +
                COLUMN_MIDDLE_NAME + ", " +
                COLUMN_TIMESTAMP + ") VALUES ('Иванов', 'Иван', 'Иванович', datetime('now'));");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_LAST_NAME + ", " +
                COLUMN_FIRST_NAME + ", " +
                COLUMN_MIDDLE_NAME + ", " +
                COLUMN_TIMESTAMP + ") VALUES ('Петров', 'Петр', 'Петрович', datetime('now'));");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_LAST_NAME + ", " +
                COLUMN_FIRST_NAME + ", " +
                COLUMN_MIDDLE_NAME + ", " +
                COLUMN_TIMESTAMP + ") VALUES ('Сидоров', 'Сидор', 'Сидорович', datetime('now'));");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_LAST_NAME + ", " +
                COLUMN_FIRST_NAME + ", " +
                COLUMN_MIDDLE_NAME + ", " +
                COLUMN_TIMESTAMP + ") VALUES ('Алексеев', 'Алексей', 'Алексеевич', datetime('now'));");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_LAST_NAME + ", " +
                COLUMN_FIRST_NAME + ", " +
                COLUMN_MIDDLE_NAME + ", " +
                COLUMN_TIMESTAMP + ") VALUES ('Федоров', 'Федор', 'Федорович', datetime('now'));");
    }
}
