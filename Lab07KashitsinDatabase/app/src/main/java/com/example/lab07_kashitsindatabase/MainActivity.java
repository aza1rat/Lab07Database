package com.example.lab07_kashitsindatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText keyText;
    EditText valueText;
    DB testdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        keyText = findViewById(R.id.input_key);
        valueText = findViewById(R.id.input_value);

        testdb = new DB(this, "testdb.db", null, 1);
    }

    public void onInsertClick(View v)//Кашицын,393
    {
        try {
            testdb.Insert(keyText.getText().toString(), valueText.getText().toString());
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "Такой ключ уже существует", Toast.LENGTH_LONG).show();
        }
    }

    public void onReplaceClick(View v)
    {
        testdb.Update(keyText.getText().toString(), valueText.getText().toString());
    }

    public void onSelectClick(View v)
    {
        String gettedValue = testdb.Select(keyText.getText().toString());
        valueText.setText(gettedValue);
    }

    public void onDeleteClick(View v)//Кашицын,393
    {
        if (testdb.Select(keyText.getText().toString()) != "(!) Не найдено")
            makeDialog("Вы действительно хотите удалить эту запись?");
        else
            Toast.makeText(getApplicationContext(), "В БД отсутствует такой ключ", Toast.LENGTH_LONG).show();
    }

    public void makeDialog(String str)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dlg = builder.create();
        dlg.setTitle(str);
        dlg.setView(dialogWithButtons(dlg));
        dlg.show();
    }

    LinearLayout dialogWithButtons(AlertDialog dlg)
    {
        LinearLayout linearLayout = new LinearLayout(getBaseContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        Button butYes = addButton("Да");
        butYes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                testdb.Delete(keyText.getText().toString());
                dlg.cancel();
            }
        });
        Button butNo = addButton("Нет");
        butNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlg.cancel();
            }
        });
        linearLayout.addView(butYes);
        linearLayout.addView(butNo);
        return linearLayout;
    }

    Button addButton (String str)//Кашицын,393
    {
        Button button = new Button(this);
        button.setText(str);
        return button;
    }
}