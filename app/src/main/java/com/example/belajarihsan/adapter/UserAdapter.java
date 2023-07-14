package com.example.belajarihsan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.belajarihsan.R;
import com.example.belajarihsan.model.UserDataResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
   private Context context;
   private List<UserDataResponse.User> userList;
   private OnItemClickListener itemClickListener;

   public interface OnItemClickListener {
      void onItemClick(UserDataResponse.User user);
   }
   public void setOnItemClickListener(OnItemClickListener listener) {
      this.itemClickListener = listener;
   }


   public UserAdapter(Context context, List<UserDataResponse.User> userList) {
      this.context = context;
      this.userList = userList;
   }

   @NonNull
   @Override
   public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
      return new UserViewHolder(view);
   }

   @Override
   public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
      UserDataResponse.User user = userList.get(position);

      holder.txtName.setText(user.getFirstName() + " " + user.getLastName());
      holder.txtEmail.setText(user.getEmail());

      Picasso.get()
              .load(user.getAvatar())
              .into(holder.imgAvatar);

      holder.itemView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            if (itemClickListener != null) {
               itemClickListener.onItemClick(user);
            }
         }
      });
   }

   public void addData(List<UserDataResponse.User> newData) {
      int startPosition = userList.size();
      userList.addAll(newData);
      notifyItemRangeInserted(startPosition, newData.size());
   }


   @Override
   public int getItemCount() {
      return userList.size();
   }

   public class UserViewHolder extends RecyclerView.ViewHolder {
      private CircleImageView imgAvatar;
      private TextView txtName;
      private TextView txtEmail;

      public UserViewHolder(@NonNull View itemView) {
         super(itemView);
         imgAvatar = itemView.findViewById(R.id.ivAvatar);
         txtName = itemView.findViewById(R.id.tvName);
         txtEmail = itemView.findViewById(R.id.tvEmail);
      }
   }
}
