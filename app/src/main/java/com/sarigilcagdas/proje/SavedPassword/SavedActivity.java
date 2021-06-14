package com.sarigilcagdas.proje.SavedPassword;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.sarigilcagdas.proje.Choose.ChooseActivity;
import com.sarigilcagdas.proje.Login.MainActivity;
import com.sarigilcagdas.proje.PasswordDatabase.Password;
import com.sarigilcagdas.proje.R;
import com.sarigilcagdas.proje.PasswordDatabase.PasswordDao;
import com.sarigilcagdas.proje.SuggestPassword.SuggestActivity;

import java.util.ArrayList;

public class SavedActivity extends AppCompatActivity {
    private Button add, show, delete, update;
    private EditText type, password;
    private RecyclerView recyclerView;

    PasswordDao dao = new PasswordDao(this);
    Context context = this;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SavedActivity.this, ChooseActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_passwords);

        dao.open();
        findById();
        buttons();

    }

    public void buttons(){

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.getText().toString().length() != 0 && password.getText().toString().length() != 0){
                    dao.addToDB(type.getText().toString(),password.getText().toString());
                    Toast.makeText(getApplicationContext(),"Veritabanına kaydedildi",Toast.LENGTH_SHORT).show();
                    type.setText("");
                    password.setText("");
                }
                else{
                    Toast.makeText(getApplicationContext(),"Lütfen boş alan bırakmayınız",Toast.LENGTH_SHORT).show();
                }

            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                layoutManager.scrollToPosition(0);
                recyclerView.setLayoutManager(layoutManager);

                ArrayList<Password> passwordArrayList = dao.getValues();
                Adapter adapter = new Adapter(passwordArrayList,context);
                recyclerView.setAdapter(adapter);
            }

        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.getText().toString().length() != 0 && password.getText().toString().length() != 0){
                    dao.deleteToDB(type.getText().toString(), password.getText().toString());
                    Toast.makeText(getApplicationContext(),"Veri silindi",Toast.LENGTH_SHORT).show();
                    type.setText("");
                    password.setText("");
                }
                else {
                    Toast.makeText(getApplicationContext(),"Lütfen verilerinizi doğru giriniz",Toast.LENGTH_SHORT).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.getText().toString().length() != 0 && password.getText().toString().length() != 0){
                    dao.updateToDB(type.getText().toString(), password.getText().toString());
                    Toast.makeText(getApplicationContext(),"Veri güncellendi!",Toast.LENGTH_SHORT).show();
                    type.setText("");
                    password.setText("");
                }
                else {
                    Toast.makeText(getApplicationContext(),"Lütfen boş bırakmayınız!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public void findById(){

        recyclerView = findViewById(R.id.recycler);
        add = findViewById(R.id.buttonAdd);
        show = findViewById(R.id.buttonShow);
        delete = findViewById(R.id.buttonDelete);
        update = findViewById(R.id.buttonUpdate);

        type = findViewById(R.id.textType);
        password = findViewById(R.id.textPassword);
    }
}