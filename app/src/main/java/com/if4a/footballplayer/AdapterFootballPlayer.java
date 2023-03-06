package com.if4a.footballplayer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterFootballPlayer extends RecyclerView.Adapter<AdapterFootballPlayer.ViewHolderFootballPlayer> {

    private Context ctx;
    private ArrayList arrId, arrNama, arrNomor, arrKlub;

    public AdapterFootballPlayer(Context cxt, ArrayList arrId, ArrayList arrNama, ArrayList arrNomor, ArrayList arrKlub){
        
    }

    @NonNull
    @Override
    public ViewHolderFootballPlayer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFootballPlayer holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolderFootballPlayer extends RecyclerView.ViewHolder {
        public ViewHolderFootballPlayer(@NonNull View itemView) {
            super(itemView);
        }
    }
}
