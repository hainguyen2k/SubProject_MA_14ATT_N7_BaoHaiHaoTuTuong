package com.example.subproject_ma_14att_n7_baohaihaotutuong;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class ListConservationAdapter extends RecyclerView.Adapter<ListConservationAdapter.RecycleViewHolder> {
    private LinkedList<Sender> list;
    private LayoutInflater inflater;
    private Context context;
    private Activity activity;

    public ListConservationAdapter(LinkedList<Sender> senders, Context context, Activity activity){
        inflater = LayoutInflater.from(context);
        this.list = senders;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_sender, parent, false);
        return new RecycleViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {
        Sender sender = list.get(position);
        holder.tvName.setText(sender.getName());
        holder.tvMessnow.setText(sender.getLbtxtview());
        holder.imgAvatar.setImageResource(sender.getImg());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ListConservationAdapter adapter;
        public TextView tvName;
        public TextView tvMessnow;
        public ImageView imgAvatar;

        public RecycleViewHolder(View view, ListConservationAdapter recycleViewAdapter_music) {
            super(view);
            tvName = view.findViewById(R.id.rv_Name);
            tvMessnow = view.findViewById(R.id.rv_Messnow);
            imgAvatar = view.findViewById(R.id.rv_imgAvatar);
            this.adapter = adapter;

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
////            int i = getLayoutPosition();
////            Music music = list.get(i);
//            Intent intent = new Intent(context, MusicMotion.class);
////            intent.putExtra("ten", music.getTen());
////            intent.putExtra("singer", music.getSinger());
////            intent.putExtra("hinh", music.getHinh());
//
//            ActivityOptionsCompat options =
//                    ActivityOptionsCompat.makeSceneTransitionAnimation(
//                            (Activity) context, imgMusic,
//                            ViewCompat.getTransitionName(imgMusic));
//            context.startActivity(intent, options.toBundle());
//            notifyDataSetChanged();
        }
    }
}
