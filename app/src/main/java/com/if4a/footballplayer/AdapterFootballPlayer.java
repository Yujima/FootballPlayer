package com.if4a.footballplayer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterFootballPlayer extends RecyclerView.Adapter<AdapterFootballPlayer.ViewHolderFootballPlayer> {

    private Context ctx;
    private ArrayList arrId, arrNama, arrNomor, arrKlub;

    public AdapterFootballPlayer(Context ctx, ArrayList arrId, ArrayList arrNama, ArrayList arrNomor, ArrayList arrKlub){
        this.ctx = ctx;
        this.arrId = arrId;
        this.arrNama = arrNama;
        this.arrNomor = arrNomor;
        this.arrKlub = arrKlub;
    }

    @NonNull
    @Override
    public ViewHolderFootballPlayer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.list_item_player, parent, false);

        return new ViewHolderFootballPlayer(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFootballPlayer holder, int position) {
        holder.tvId.setText(arrId.get(position).toString());
        holder.tvNama.setText(arrNama.get(position).toString());
        holder.tvNomor.setText(arrNomor.get(position).toString());
        holder.tvKlub.setText(arrKlub.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return arrNama.size();
    }

    public class ViewHolderFootballPlayer extends RecyclerView.ViewHolder {
        private TextView tvId, tvNama, tvNomor, tvKlub;
        public ViewHolderFootballPlayer(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_idPlayer);
            tvNama = itemView.findViewById(R.id.tv_namaPlayer);
            tvNomor = itemView.findViewById(R.id.tv_noPunggung);
            tvKlub = itemView.findViewById(R.id.tv_klub);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder pesan = new AlertDialog.Builder(ctx);
                    pesan.setTitle("Perhatian");
                    pesan.setMessage(tvNama.getText().toString() + "\nPilih perintah yang diinginkan");
                    pesan.setCancelable(true);
                    pesan.setNegativeButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MyDatabaseHelper myDb =new MyDatabaseHelper(ctx);
                            long eksekusi = myDb.hapusPlayer(tvId.getText().toString());
                            if(eksekusi == -1){
                                Toast.makeText(ctx, "Gagal Menghapus Data!", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(ctx, "Sukses Menghapus Data!", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        }
                    });

                    pesan.setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(ctx, UbahActivity.class);
                            intent.putExtra("vId", tvId.getText().toString());
                            intent.putExtra("vNama", tvNama.getText().toString());
                            intent.putExtra("vNomor", tvNomor.getText().toString());
                            intent.putExtra("vKlub", tvKlub.getText().toString());
                            ctx.startActivity(intent);
                        }
                    });
                    pesan.show();
                    return false;
                }
            });
        }
    }
}
