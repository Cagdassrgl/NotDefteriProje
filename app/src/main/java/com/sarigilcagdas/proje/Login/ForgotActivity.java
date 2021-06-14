package com.sarigilcagdas.proje.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.sarigilcagdas.proje.R;
import com.sarigilcagdas.proje.UserDatabase.User;
import com.sarigilcagdas.proje.UserDatabase.UserDao;

import java.util.ArrayList;

public class ForgotActivity extends AppCompatActivity {

    private EditText keyWord, changePass;
    private Button changeBtn;
    private ImageView key;

    public static String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        final UserDao dao = new UserDao(this);
        dao.open();

        findViewId();
        button(dao);
    }

    private void findViewId() {
        keyWord = findViewById(R.id.editKeyWord);
        changePass = findViewById(R.id.editNewPassword);
        changeBtn = findViewById(R.id.changeButton);
        key = findViewById(R.id.keyImage);
    }

    public void button(UserDao dao) {
        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<User> checkUser = dao.getValues();
                if (keyWord.getText().toString().length() != 0 && changePass.getText().toString().length() != 0) {
                    boolean result = false;
                    for (User user: checkUser) {
                        if (user.getKeyword().equals(keyWord.getText().toString())) {
                            if (changePass.length()>=8){
                                user.setPassword(changePass.getText().toString());
                                dao.update(user,changePass.getText().toString());
                                Toast.makeText(getApplicationContext(),"Şifre değiştirildi",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ForgotActivity.this, MainActivity.class));
                                finish();
                                result = true;
                            }
                            else {
                                Toast.makeText(ForgotActivity.this, "Lütfen şifrenizi 8 haneden az girmeyiniz.", Toast.LENGTH_SHORT).show();
                                result = true;
                            }
                        }
                    }
                    if (!result){
                        Toast.makeText(getApplicationContext(),"Anahtar kelimenizi yanlış girdiniz",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"Lütfen bilgileri eksiksiz giriniz!",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ForgotActivity.this,MainActivity.class));
    }

}