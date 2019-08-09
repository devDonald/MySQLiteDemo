package com.example.mysqlitedemo.helper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysqlitedemo.R;

import java.util.List;

public class UsersRecyclerAdapter extends RecyclerView.Adapter <UsersRecyclerAdapter.UserViewHolder>{

    private List<User> listUsers;

    public UsersRecyclerAdapter(List<User> listUsers) {
        this.listUsers = listUsers;
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_rows, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        holder.textViewName.setText(listUsers.get(position).getName());
        holder.textViewEmail.setText(listUsers.get(position).getEmail());
        holder.textViewHobby.setText(listUsers.get(position).getPassword());
    }

    @Override
    public int getItemCount() {
        return listUsers.size();
    }


    public class UserViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;
        public TextView textViewEmail;
        public TextView textViewHobby;

        public UserViewHolder(View view) {
            super(view);
            textViewName = (TextView) view.findViewById(R.id.display_user_name);
            textViewEmail = (TextView) view.findViewById(R.id.display_user_email);
            textViewHobby = (TextView) view.findViewById(R.id.display_user_hobby);
        }
    }

}
