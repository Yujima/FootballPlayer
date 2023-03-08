package com.if4a.footballplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MyDatabaseHelper myDb = new MyDatabaseHelper(MainActivity.this);
    private FloatingActionButton fabTambah;
    private RecyclerView rvPlayer;
    private AdapterFootballPlayer adapterFootballPlayer;
    private ArrayList<String> arrId, arrNama, arrNomor, arrKlub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabTambah = findViewById(R.id.fab_tambah);

        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TambahActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        tampilPlayer();
    }

    private void SQLiteToArrayList(){
        Cursor cursor = myDb.bacaDataPlayer();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "Data Kosong", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                arrId.add(cursor.getString(0));
                arrNama.add(cursor.getString(1));
                arrNomor.add(cursor.getString(2));
                arrKlub.add(cursor.getString(3));
            }
        }
    }

    private void tampilPlayer() {
        arrId = new ArrayList<>();
        arrNama = new ArrayList<>();
        arrNomor = new ArrayList<>();
        arrKlub = new ArrayList<>();

        SQLiteToArrayList();

        rvPlayer = findViewById(R.id.rv_player);
        adapterFootballPlayer = new AdapterFootballPlayer(MainActivity.this, arrId, arrNama, arrNomor, arrKlub);
        rvPlayer.setAdapter(adapterFootballPlayer);
        rvPlayer.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }
}