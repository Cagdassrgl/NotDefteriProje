package com.sarigilcagdas.proje.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sarigilcagdas.proje.R;
import com.sarigilcagdas.proje.UserDatabase.User;
import com.sarigilcagdas.proje.UserDatabase.UserDao;

import java.util.ArrayList;


public class SignUpActivity extends AppCompatActivity {

    private EditText signUsername, signPassword, signKeyword;
    private Button signButton;



    public String username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final UserDao dao = new UserDao(this);
        dao.open();

        findViewId();
        Button(dao);
    }

    private void findViewId() {
        signUsername = findViewById(R.id.signUsername);
        signPassword = findViewById(R.id.signPassword);
        signKeyword = findViewById(R.id.signKeyword);
        signButton = findViewById(R.id.signButton);
    }

    public void Button(UserDao dao) {
        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (signUsername.getText().toString().length() >= 8 && signPassword.getText().toString().length() >= 8 &&
                        signKeyword.getText().toString().length() >= 4) {

                    long checkUnique = dao.addToDb(new User(signUsername.getText().toString(), signPassword.getText().toString(), signKeyword.getText().toString()));

                    if ( checkUnique != -1){

                        Log.e("mesaj",String.valueOf(checkUnique));

                        Toast.makeText(getApplicationContext(),"Kullanıcı veritabanına kaydedildi!",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),
                                "Daha önce alınmış bir kullanıcı adı veya anahtar kelime girdiniz!",
                                Toast.LENGTH_SHORT).show();
                    }

                    }
                else{
                    Toast.makeText(getApplicationContext(),
                            "Kullanıcı adı ve şifre en az 8 hane olmalıdır!\nAnahtar kelime en az 4 hane olmalıdır!",
                            Toast.LENGTH_SHORT).show();
                    }

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SignUpActivity.this,MainActivity.class));
    }
}