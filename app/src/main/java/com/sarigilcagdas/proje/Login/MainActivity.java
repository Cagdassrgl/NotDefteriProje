package com.sarigilcagdas.proje.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.sarigilcagdas.proje.R;
import com.sarigilcagdas.proje.UserDatabase.User;
import com.sarigilcagdas.proje.UserDatabase.UserDao;
import com.sarigilcagdas.proje.Choose.ChooseActivity;

import java.sql.ResultSet;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity  {

    private EditText userNameText, passwordText;
    private Button loginBtn, registerBtn, forgotBtn;
    private CheckBox view, remember;

    final UserDao dao = new UserDao(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        dao.open();

        findById();
        buttons();
        checkboxes();

        loginButton();


    }

    public void loginButton() {
        SharedPreferences sharedPreferences = getSharedPreferences("Remember",MODE_PRIVATE);
        String _username = sharedPreferences.getString("username",null);
        String _password = sharedPreferences.getString("password",null);

        if (_username != null && _password != null){
            startActivity(new Intent(MainActivity.this, ChooseActivity.class));
            finish();
        }
        else {
            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String username = userNameText.getText().toString();
                    String password= passwordText.getText().toString();

                    ArrayList<User> checkUser = dao.getValues();

                    if (username.length() != 0 && password.length() != 0) {
                        boolean result = false;
                        for (User user : checkUser) {

                            if (user.getUsername().equals(username) &&
                                    user.getPassword().equals(password)){

                                if (remember.isChecked()){
                                    getSharedPreferences("Remember",MODE_PRIVATE)
                                            .edit()
                                            .putString("username",username)
                                            .putString("password",password)
                                            .commit();
                                }
                                Toast.makeText(getApplicationContext(),"Giriş başarılı",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, ChooseActivity.class));
                                finish();
                                result = true;
                            }

                        }

                        if (!result){
                            Toast.makeText(getApplicationContext(),"Kullanıcı adı veya şifre hatalı",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Lütfen bilgileri eksiksiz giriniz!",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void checkboxes(){
        view.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked){
                    passwordText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                else{
                    passwordText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

    }

    public void buttons(){

        forgotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MainActivity.this, ForgotActivity.class));
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });

    }

    public void findById(){

        userNameText = findViewById(R.id.usernameText);
        passwordText = findViewById(R.id.passwordText);

        loginBtn = findViewById(R.id.loginButton);
        registerBtn = findViewById(R.id.registerButton);
        forgotBtn = findViewById(R.id.forgotButton);

        view = findViewById(R.id.viewPassword);
        remember = findViewById(R.id.rememberCheck);
    }

}