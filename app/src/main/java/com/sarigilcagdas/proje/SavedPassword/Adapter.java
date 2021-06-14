package com.sarigilcagdas.proje.SavedPassword;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sarigilcagdas.proje.PasswordDatabase.Password;
import com.sarigilcagdas.proje.R;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    ArrayList<Password> passwordArrayList;
    LayoutInflater inflater;
    Context context;

    public Adapter(ArrayList<Password> passwordArrayList, Context context) {
        this.passwordArrayList = passwordArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.row_list,parent,false);
        ViewHolder vh = new ViewHolder(row);
        return vh;
    }

    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder, int position) {
        holder.type.setText(passwordArrayList.get(position).getType());
        holder.password.setText(passwordArrayList.get(position).getPassword());
        holder.linearLayout.setTag(holder);
    }

    @Override
    public int getItemCount() {
        return passwordArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView type,password;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.textType);
            password = itemView.findViewById(R.id.textPassword);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }

}
