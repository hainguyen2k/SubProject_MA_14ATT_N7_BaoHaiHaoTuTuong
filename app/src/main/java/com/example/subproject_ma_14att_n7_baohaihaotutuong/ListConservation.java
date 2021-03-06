package com.example.subproject_ma_14att_n7_baohaihaotutuong;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.LinkedList;

public class ListConservation extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_email;
    GoogleSignInClient mGoogleSignInClient;
    RecyclerView rv_Sender;
    ListConservationAdapter adapter;
    LinkedList<Sender> senders = new LinkedList<>();
    public static final String KEY_EMAIL="email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_conservation);

        ImageView btnMaps = (ImageView) findViewById(R.id.btnMaps);
        btnMaps.setClickable(true);
        btnMaps.setOnClickListener(this);

        ImageView btnLogout = (ImageView) findViewById(R.id.btnLogout);
        btnLogout.setClickable(true);
        btnLogout.setOnClickListener(this);

        ImageView btnProfile = (ImageView) findViewById(R.id.btnProfile);
        btnProfile.setClickable(true);
        btnProfile.setOnClickListener(this);

        tv_email = (TextView) findViewById(R.id.tv_email);
        rv_Sender = findViewById(R.id.rv_Sender);

        Intent intent = getIntent();
        tv_email.setText(intent.getStringExtra(MainActivity.KEY_EMAIL));

        senders.add(new Sender("Xuan Hai", "Hola !", R.drawable.avtsender));
        senders.add(new Sender("Thanh Tu", "Hola !", R.drawable.avtsender));
        senders.add(new Sender("The Bao", "Hola !", R.drawable.avtsender));
        senders.add(new Sender("Nhat Hao", "Hola !", R.drawable.avtsender));
        senders.add(new Sender("Son Tung MTP", "Hola !", R.drawable.avtsender));
        senders.add(new Sender("Jack", "Hola !", R.drawable.avtsender));
        senders.add(new Sender("Wowy", "Hola !", R.drawable.avtsender));
        senders.add(new Sender("Binz", "Hola !", R.drawable.avtsender));
        senders.add(new Sender("Justatee", "Hola !", R.drawable.avtsender));


        adapter = new ListConservationAdapter(senders, this,this);
        rv_Sender.setAdapter(adapter);
        rv_Sender.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
    }

    private void openProfile() {
        Intent intent = new Intent(ListConservation.this, ProfileActivity.class);
        intent.putExtra(KEY_EMAIL, tv_email.getText().toString());
        startActivity(intent);
    }

    private void openMaps() {
        Intent intent = new Intent(ListConservation.this, MapsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnMaps:
                openMaps();
                break;
            case R.id.btnLogout:
                signOut();
                break;
            case R.id.btnProfile:
                openProfile();
                break;
        }
    }
}