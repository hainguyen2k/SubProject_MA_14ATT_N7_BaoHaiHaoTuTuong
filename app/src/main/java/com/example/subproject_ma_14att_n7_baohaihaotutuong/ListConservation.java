package com.example.subproject_ma_14att_n7_baohaihaotutuong;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class ListConservation extends AppCompatActivity {
    RecyclerView rv_Sender;
    ListConservationAdapter adapter;
    LinkedList<Sender> senders = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_conservation);

        rv_Sender = findViewById(R.id.rv_Sender);

        senders.add(new Sender("Xuan Hai", "Hola !", R.drawable.avtsender));
        senders.add(new Sender("Xuan Hai", "Hola !", R.drawable.avtsender));
        senders.add(new Sender("Xuan Hai", "Hola !", R.drawable.avtsender));
        senders.add(new Sender("Xuan Hai", "Hola !", R.drawable.avtsender));
        senders.add(new Sender("Xuan Hai", "Hola !", R.drawable.avtsender));
        senders.add(new Sender("Xuan Hai", "Hola !", R.drawable.avtsender));
        senders.add(new Sender("Xuan Hai", "Hola !", R.drawable.avtsender));
        senders.add(new Sender("Xuan Hai", "Hola !", R.drawable.avtsender));
        senders.add(new Sender("Xuan Hai", "Hola !", R.drawable.avtsender));


        adapter = new ListConservationAdapter(senders, this,this);
        rv_Sender.setAdapter(adapter);
        rv_Sender.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
}