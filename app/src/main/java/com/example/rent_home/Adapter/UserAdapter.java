package com.example.rent_home.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rent_home.R;
import com.example.rent_home.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {


    private Context mContext;
    private List<Users> mUsers;
    private FirebaseUser firebaseUser;

    public UserAdapter(Context mContext, List<Users> mUsers) {
        this.mContext = mContext;
        this.mUsers = mUsers;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View view= LayoutInflater.from(mContext).inflate(R.layout.user_item)
        //need for search
    return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        Users user= mUsers.get(position);
        holder.username.setText(user.getUsername());
        holder.name.setText(user.getName());
        Picasso.get().load(user.getImage()).placeholder(R.mipmap.ic_launcher).into(holder.pro_pic);

    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public CircleImageView pro_pic;
        public TextView address, username, name;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            pro_pic= itemView.findViewById(R.id.profile_imgP);
            address= itemView.findViewById(R.id.addressP);

            name= itemView.findViewById(R.id.nameP);

        }
    }
    
}
