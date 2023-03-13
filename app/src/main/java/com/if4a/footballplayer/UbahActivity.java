package com.if4a.footballplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UbahActivity extends AppCompatActivity {

    private EditText etNama, etNomor, etKlub;
    private Button btnUbah;
    private String id, nama, nomor, klub;
    private MyDatabaseHelper MyDb = new MyDatabaseHelper(UbahActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        etNama = findViewById(R.id.et_namaPlayerUbah);
        etNomor = findViewById(R.id.et_noPunggungUbah);
        etKlub = findViewById(R.id.et_namaKlubUbah);
        btnUbah = findViewById(R.id.btn_ubah);

        Intent intent = new Intent();
        id = intent.getStringExtra("vId");
        nama = intent.getStringExtra("vNama");
        nomor = intent.getStringExtra("vNomor");
        klub = intent.getStringExtra("vKlub");

        etNama.setText(nama);
        etNomor.setText(nomor);
        etKlub.setText(klub);

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempNama = etNama.getText().toString();
                String tempNomor = etNomor.getText().toString();
                String tempKlub = etKlub.getText().toString();

                if(nama.trim().equals("") || nomor.trim().equals("") || klub.trim().equals("")){
                    if(nama.trim().equals("")){
                        etNama.setError("Nama Pemain Harus Diisi!");
                    }
                    if(nomor.trim().equals("")){
                        etNomor.setError("Nomor Punggung Harus Diisi!");
                    }
                    if(klub.trim().equals("")){
                        etKlub.setError("Nama Klub Harus Diisi!");
                    }
                }else{
                    long eksekusi = MyDb.ubahPlayer(id, tempNama, tempNomor, tempKlub);

                    if(eksekusi == -1){
                        Toast.makeText(UbahActivity.this, "Gagal Mengubah Data Player.", Toast.LENGTH_LONG).show();
                        etNama.requestFocus();
                    }else {
                        Toast.makeText(UbahActivity.this, "Sukses Mengubah Data Player", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }
}