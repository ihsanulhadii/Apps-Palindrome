package com.example.belajarihsan.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.belajarihsan.R;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    Button btnCheck;
    EditText etPalindrome;
    EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etPalindrome = findViewById(R.id.etPalindrome);
        etName = findViewById(R.id.etName);

        btnCheck = findViewById(R.id.btnCheck);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dataPalindrome = etPalindrome.getText().toString();
                if(!dataPalindrome.isEmpty()){
                    if(isPalindrome(dataPalindrome)){
                        Toast.makeText(LoginActivity.this,"isPalindrome True",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(LoginActivity.this,"isPalindrome False",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(LoginActivity.this,"please insert data palindrome",Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnLogin = findViewById(R.id.btnNext);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                if(name.isEmpty()){
                    Toast.makeText(LoginActivity.this,"please insert name",Toast.LENGTH_SHORT).show();
                }else {
                    Intent second = new Intent(LoginActivity.this,SecondActivity.class);
                    second.putExtra("name",name);
                    startActivity(second);
                }

            }
        });

    }


    public static boolean isPalindrome(String str) {
        // Remove whitespace and convert to lowercase
        str = str.replaceAll("\\s+", "").toLowerCase();

        // Compare characters from start and end
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}