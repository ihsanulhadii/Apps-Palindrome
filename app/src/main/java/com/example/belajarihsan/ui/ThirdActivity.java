package com.example.belajarihsan.ui;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.belajarihsan.R;
import com.example.belajarihsan.adapter.UserAdapter;
import com.example.belajarihsan.model.UserDataResponse;
import com.example.belajarihsan.services.MyApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ThirdActivity extends AppCompatActivity implements UserAdapter.OnItemClickListener  {
   ImageView ivBack2;
   private RecyclerView recyclerView;
   private UserAdapter userAdapter;

   private SwipeRefreshLayout swipeRefreshLayout;
   private int currentPage = 1;
   private boolean isLoading = false;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.third_screen);

      ivBack2 = findViewById(R.id.back2);
      ivBack2.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            onBackPressed();
         }
      });

      swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

      recyclerView = findViewById(R.id.recyclerView);
      recyclerView.setLayoutManager(new LinearLayoutManager(this));
      recyclerView.setHasFixedSize(true);


      parsingData();

      swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
         @Override
         public void onRefresh() {
            currentPage = 1;
            parsingData();
         }
      });


      recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
         @Override
         public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

            if (!isLoading && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0 && totalItemCount >= 10) {
               currentPage++;
               parsingData();
            }
         }
      });

   }


   public void parsingData(){
      Retrofit retrofit = new Retrofit.Builder()
              .baseUrl("https://reqres.in/") // Replace with your API base URL
              .addConverterFactory(GsonConverterFactory.create())
              .build();

      // Create API service
      MyApiService apiService = retrofit.create(MyApiService.class);

      // Make API call
      isLoading = true;
      Call<UserDataResponse> call = apiService.getUserData(currentPage, 10);
      call.enqueue(new Callback<UserDataResponse>() {
         @Override
         public void onResponse(Call<UserDataResponse> call, Response<UserDataResponse> response) {
            if (response.isSuccessful()) {
               UserDataResponse userDataResponse = response.body();
               if (userDataResponse != null) {
                  if (userAdapter == null) {
                     userAdapter = new UserAdapter(ThirdActivity.this, userDataResponse.getData());
                     userAdapter.setOnItemClickListener(ThirdActivity.this);
                     recyclerView.setAdapter(userAdapter);
                  } else {
                     userAdapter.addData(userDataResponse.getData());
                  }
                  isLoading = false;
                  swipeRefreshLayout.setRefreshing(false);
               }
            } else {
               isLoading = false;
               swipeRefreshLayout.setRefreshing(false);
               Toast.makeText(ThirdActivity.this, "Failed to fetch data.", Toast.LENGTH_SHORT).show();
            }
         }

         @Override
         public void onFailure(Call<UserDataResponse> call, Throwable t) {
            isLoading = false;
            Toast.makeText(ThirdActivity.this, "Failed to fetch data.", Toast.LENGTH_SHORT).show();
         }
      });
   }

   @Override
   public void onItemClick(UserDataResponse.User user) {
      Toast.makeText(this,"Select "+user.getFirstName() + " "+ user.getLastName(),Toast.LENGTH_SHORT).show();
      Intent intent = new Intent();
      intent.putExtra("name",user.getFirstName()+" "+user.getLastName());
      setResult(RESULT_OK,intent);
      finish();
   }
}