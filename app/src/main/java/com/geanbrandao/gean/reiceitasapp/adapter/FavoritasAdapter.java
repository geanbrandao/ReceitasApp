package com.geanbrandao.gean.reiceitasapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geanbrandao.gean.reiceitasapp.R;
import com.geanbrandao.gean.reiceitasapp.activity.FavoritosActivity;
import com.geanbrandao.gean.reiceitasapp.helper.ReceitasFavoritas;
import com.geanbrandao.gean.reiceitasapp.json.Recipe;

import java.util.List;

public class FavoritasAdapter extends RecyclerView.Adapter<FavoritasAdapter.MyViewHolder>{

    private FavoritasAdapaterListener listener;
    private List<ReceitasFavoritas> favoritas;
    private Context context;
    public FavoritasAdapter(Context context, List<ReceitasFavoritas> favoritas, FavoritasAdapaterListener listener) {
        this.context = context;
        this.favoritas = favoritas;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_receitas, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        ReceitasFavoritas fav = favoritas.get(i);
        myViewHolder.mCategorias.setText(fav.getNomeFonte());
        myViewHolder.mNomeReceitas.setText(fav.getNome());
        pegarImgReceita(myViewHolder, fav);
        // aplicar os eventos de click
        aplicarEventoClick(myViewHolder, i);
    }

    private void pegarImgReceita(MyViewHolder myViewHolder, ReceitasFavoritas fav) {
        if(fav.getFoto()!=null){
            Bitmap raw  = BitmapFactory.decodeByteArray(fav.getFoto(),0,fav.getFoto().length);
            myViewHolder.imageView.setImageBitmap(raw);
        }
    }

    @Override
    public int getItemCount() {
        return favoritas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView mCategorias;
        public TextView mNomeReceitas;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_receita_adapter);
            mCategorias = itemView.findViewById(R.id.tv_categoria);
            mNomeReceitas = itemView.findViewById(R.id.tv_nome_receita);

        }
    }

    private void aplicarEventoClick(FavoritasAdapter.MyViewHolder myViewHolder, final int position) {
        myViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onImageClicked(position);
            }
        });
    }
    public interface FavoritasAdapaterListener {
        void onImageClicked(int position);
    }
}
