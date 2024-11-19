package com.example.lab3zolotarev;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewDataActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        dbHelper = new DatabaseHelper(this);
        TextView textViewData = findViewById(R.id.textViewData);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, null, null, null, null, null, null);

        StringBuilder data = new StringBuilder();
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID));
                String lastName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_LAST_NAME));
                String firstName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_FIRST_NAME));
                String middleName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_MIDDLE_NAME));
                String timestamp = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TIMESTAMP));

                data.append("ID: ").append(id).append("\n");
                data.append("Фамилия: ").append(lastName).append("\n");
                data.append("Имя: ").append(firstName).append("\n");
                data.append("Отчество: ").append(middleName).append("\n");
                data.append("Время: ").append(timestamp).append("\n\n");
            } while (cursor.moveToNext());
        } else {
            data.append("Нет данных для отображения.");
        }
        cursor.close();

        textViewData.setText(data.toString());
    }
}
