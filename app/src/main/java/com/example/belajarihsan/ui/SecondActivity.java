package com.example.belajarihsan.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.belajarihsan.R;

public class SecondActivity extends AppCompatActivity {
   ImageView ivBack;
   Button btnSelect;
   TextView selectUserName;

   TextView tvName;
   String name;


   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.second_screen);

      ivBack= findViewById(R.id.back);
      tvName = findViewById(R.id.tvName);
      selectUserName = findViewById(R.id.selectUserName);
      btnSelect = findViewById(R.id.btnSelect);

      name = getIntent().getStringExtra("name");
      tvName.setText(name);


      ivBack.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            onBackPressed();
         }
      });


      btnSelect.setOnClickListener(new View.OnClickListener(){
         @Override
         public void onClick(View view) {
            Intent ifnext = new Intent(SecondActivity.this,ThirdActivity.class);
            startActivityForResult(ifnext,111);
         };
      });

   }


   @Override
   protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
      super.onActivityResult(requestCode, resultCode, data);

      if(resultCode==RESULT_OK){
         if(data.hasExtra("name")){
            String nameSelected = data.getStringExtra("name");
            selectUserName.setText(nameSelected);
         }
      }
   }
}