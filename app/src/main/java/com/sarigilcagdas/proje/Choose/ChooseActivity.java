package com.sarigilcagdas.proje.Choose;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.sarigilcagdas.proje.Login.MainActivity;
import com.sarigilcagdas.proje.R;
import com.sarigilcagdas.proje.SavedPassword.SavedActivity;
import com.sarigilcagdas.proje.SuggestPassword.SuggestActivity;

public class ChooseActivity extends AppCompatActivity {
    private Button save,suggest,logOut;
    private ImageView saved, choose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        findViewId();
        buttons();
    }

    private void findViewId() {
        save = findViewById(R.id.savedButton);
        suggest = findViewById(R.id.suggestButton);
        logOut = findViewById(R.id.logOutChose);
        saved = findViewById(R.id.savedImage);
        choose = findViewById(R.id.chooseImage);
    }

    public void buttons() {
        suggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent yeniIntent = new Intent(ChooseActivity.this, SuggestActivity.class);
                startActivity(yeniIntent);
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChooseActivity.this, SavedActivity.class));
                finish();
            }
        });
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSharedPreferences("Remember",MODE_PRIVATE).edit().clear().commit();
                startActivity(new Intent(ChooseActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}