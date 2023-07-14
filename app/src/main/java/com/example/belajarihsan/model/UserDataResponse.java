package com.example.belajarihsan.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserDataResponse {
   int page;
   @SerializedName("per_page")
   int perPage;
   int total;

   public int getPage() {
      return page;
   }

   public void setPage(int page) {
      this.page = page;
   }

   public int getPerPage() {
      return perPage;
   }

   public void setPerPage(int perPage) {
      this.perPage = perPage;
   }

   public int getTotal() {
      return total;
   }

   public void setTotal(int total) {
      this.total = total;
   }

   public int getTotalPages() {
      return totalPages;
   }

   public void setTotalPages(int totalPages) {
      this.totalPages = totalPages;
   }

   public List<User> getData() {
      return data;
   }

   public void setData(List<User> data) {
      this.data = data;
   }

   public Support getSupport() {
      return support;
   }

   public void setSupport(Support support) {
      this.support = support;
   }

   @SerializedName("total_pages")
   int totalPages;
   List<User> data;
   Support support;

   public class User {
      int id;
      String email;

      public int getId() {
         return id;
      }

      public void setId(int id) {
         this.id = id;
      }

      public String getEmail() {
         return email;
      }

      public void setEmail(String email) {
         this.email = email;
      }

      public String getFirstName() {
         return firstName;
      }

      public void setFirstName(String firstName) {
         this.firstName = firstName;
      }

      public String getLastName() {
         return lastName;
      }

      public void setLastName(String lastName) {
         this.lastName = lastName;
      }

      public String getAvatar() {
         return avatar;
      }

      public void setAvatar(String avatar) {
         this.avatar = avatar;
      }

      @SerializedName("first_name")
      String firstName;
      @SerializedName("last_name")
      String lastName;
      String avatar;
   }

   public class Support {
      String url;
      String text;

   }
}
