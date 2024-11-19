package com.example.lab3zolotarev;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);

        // Кнопка для открытия нового активити и отображения данных
        findViewById(R.id.button_view_data).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ViewDataActivity.class);
            startActivity(intent);
        });

        // Кнопка для добавления новой записи в таблицу
        findViewById(R.id.button_add_data).setOnClickListener(view -> addData());

        // Кнопка для обновления последней записи
        findViewById(R.id.button_update_last).setOnClickListener(view -> updateLastRecord());

        // Удаляем все записи из таблицы и добавляем 5 новых при запуске приложения
        resetDatabase();
    }

    // Метод для удаления всех записей и добавления начальных данных
    private void resetDatabase() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_NAME, null, null); // Удаление всех записей

        // Добавление 5 начальных записей
        for (int i = 1; i <= 5; i++) {
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.COLUMN_LAST_NAME, "Фамилия " + i);
            values.put(DatabaseHelper.COLUMN_FIRST_NAME, "Имя " + i);
            values.put(DatabaseHelper.COLUMN_MIDDLE_NAME, "Отчество " + i);
            values.put(DatabaseHelper.COLUMN_TIMESTAMP, getCurrentTimestamp());
            db.insert(DatabaseHelper.TABLE_NAME, null, values);
        }
        Toast.makeText(this, "База данных сброшена. Добавлено 5 записей.", Toast.LENGTH_SHORT).show();
    }

    // Метод для получения текущей даты и времени
    private String getCurrentTimestamp() {
        return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault()).format(new java.util.Date());
    }



    // Метод для добавления новой записи
    private void addData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_LAST_NAME, "Новая фамилия");
        values.put(DatabaseHelper.COLUMN_FIRST_NAME, "Новое имя");
        values.put(DatabaseHelper.COLUMN_MIDDLE_NAME, "Новое отчество");
        values.put(DatabaseHelper.COLUMN_TIMESTAMP, getCurrentTimestamp());
        db.insert(DatabaseHelper.TABLE_NAME, null, values);
        Toast.makeText(this, "Запись добавлена.", Toast.LENGTH_SHORT).show();
    }



    // Метод для обновления последней записи
    private void updateLastRecord() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToLast()) {
            String lastId = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID));
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.COLUMN_LAST_NAME, "Иванов");
            values.put(DatabaseHelper.COLUMN_FIRST_NAME, "Иван");
            values.put(DatabaseHelper.COLUMN_MIDDLE_NAME, "Иванович");
            db.update(DatabaseHelper.TABLE_NAME, values, DatabaseHelper.COLUMN_ID + "=?", new String[]{lastId});
            Toast.makeText(this, "Последняя запись обновлена.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Нет записей для обновления.", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }

}
