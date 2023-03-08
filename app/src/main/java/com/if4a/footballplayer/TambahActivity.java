package com.if4a.footballplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TambahActivity extends AppCompatActivity {

    private EditText etNama, etNomor, etKlub;
    private Button btnTambah;
    private MyDatabaseHelper myDb = new MyDatabaseHelper(TambahActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etNama = findViewById(R.id.et_namaPlayer);
        etNomor = findViewById(R.id.et_noPunggung);
        etKlub = findViewById(R.id.et_namaKlub);

        btnTambah = findViewById(R.id.btn_tambah);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama, nomor, klub;

                nama = etNama.getText().toString();
                nomor = etNomor.getText().toString();
                klub = etKlub.getText().toString();

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
                    long eksekusi = myDb.tambahPlayer(nama, Integer.parseInt(nomor), klub);

                    if(eksekusi == -1){
                        Toast.makeText(TambahActivity.this, "Gagal Menambahkan Data Pemain", Toast.LENGTH_LONG).show();
                        etNama.requestFocus();
                    }else {
                        Toast.makeText(TambahActivity.this, "Sukses Menambahkan Data Pemain", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }
}