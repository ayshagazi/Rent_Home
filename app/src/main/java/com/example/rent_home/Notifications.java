package com.example.rent_home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Model.Renters;

public class Notifications extends AppCompatActivity {

    private RecyclerView rentList;
    private DatabaseReference rentRef;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        rentList= findViewById(R.id.rent_recyler);
       // rentList.setLayoutManager(new LinearLayoutManager(this));
        rentList.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager( this);
        rentList.setLayoutManager(layoutManager);
        rentRef= FirebaseDatabase.getInstance().getReference().child("ConfirmRent");

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Renters> options=
                new FirebaseRecyclerOptions.Builder<Renters>().setQuery(rentRef,Renters.class).build();
        FirebaseRecyclerAdapter<Renters,RentersViewHolder> adapter= new FirebaseRecyclerAdapter<Renters, RentersViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull RentersViewHolder holder, int position, @NonNull Renters model)
            {
                holder.nam.setText(model.getName());
                holder.contact.setText(model.getContactNo());
                holder.adres.setText(model.getAddress());

            }

            @NonNull
            @Override
            public RentersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rent_list_layout, parent, false);
                return new RentersViewHolder(view);            }
        };

        rentList.setAdapter(adapter);
        adapter.startListening();

    }



    public static class RentersViewHolder extends RecyclerView.ViewHolder{
        public TextView nam,contact,adres;
        public Button showButton;

        public RentersViewHolder(View itemView){
            super(itemView);
            nam= itemView.findViewById(R.id.nam);
            contact=itemView.findViewById(R.id.cnctNo);
            adres=itemView.findViewById(R.id.adres);
            showButton=itemView.findViewById(R.id.showBtn);
        }
    }
}