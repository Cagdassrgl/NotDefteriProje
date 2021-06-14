package com.sarigilcagdas.proje.SuggestPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sarigilcagdas.proje.Choose.ChooseActivity;
import com.sarigilcagdas.proje.Login.MainActivity;
import com.sarigilcagdas.proje.R;

public class SuggestActivity extends AppCompatActivity {
    private Button digit6, digit8, digit12;
    private ImageButton copy6, copy8, copy12;
    private TextView text6, text8, text12;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SuggestActivity.this, ChooseActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest);

        findById();
        digitButtons();
        copyButtons();

    }

    public void findById(){
        digit6 = findViewById(R.id.suggestButton6);
        text6 = findViewById(R.id.text6);

        digit8 = findViewById(R.id.suggestButton8);
        text8 = findViewById(R.id.text8);

        digit12 = findViewById(R.id.suggestButton12);
        text12 = findViewById(R.id.text12);

        copy6 = findViewById(R.id.copy6);
        copy8 = findViewById(R.id.copy8);
        copy12 = findViewById(R.id.copy12);
    }

    public void digitButtons(){
        digit6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PasswordClass passwordClass = new PasswordClass();
                text6.setText(passwordClass.suggestPassword6());
            }
        });

        digit8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PasswordClass passwordClass = new PasswordClass();
                text8.setText(passwordClass.suggestPassword8());
            }
        });

        digit12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PasswordClass passwordClass = new PasswordClass();
                text12.setText(passwordClass.suggestPassword12());
            }
        });
    }

    public void copyButtons(){
        copy6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("TextView",text6.getText().toString());
                clipboard.setPrimaryClip(clip);

                Toast.makeText(SuggestActivity.this, "Copied", Toast.LENGTH_SHORT).show();
            }
        });

        copy8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("TextView",text8.getText().toString());
                clipboard.setPrimaryClip(clip);

                Toast.makeText(SuggestActivity.this, "Copied", Toast.LENGTH_SHORT).show();
            }
        });
        copy12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("TextView",text12.getText().toString());
                clipboard.setPrimaryClip(clip);

                Toast.makeText(SuggestActivity.this, "Copied", Toast.LENGTH_SHORT).show();
            }
        });


    }
}