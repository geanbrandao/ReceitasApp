package com.geanbrandao.gean.reiceitasapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.geanbrandao.gean.reiceitasapp.R;
import com.geanbrandao.gean.reiceitasapp.json.Recipe;

import java.util.List;

public class ReceitasAdapater extends RecyclerView.Adapter<ReceitasAdapater.MyViewHolder> {

    private List<Recipe> recipes;
    private Context mContext;
    private ReceitaAdapaterListener listener;

    public ReceitasAdapater(List<Recipe> recipes, Context mContext, ReceitaAdapaterListener listener) {
        this.recipes = recipes;
        this.mContext = mContext;
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
    public void onBindViewHolder(@NonNull ReceitasAdapater.MyViewHolder myViewHolder, int i) {
        Recipe recipe = recipes.get(i);
        myViewHolder.mCategorias.setText(recipe.getSourceDisplayName());
        myViewHolder.mNomeReceitas.setText(recipe.getRecipeName());
        pegarImgReceita(myViewHolder, recipe);
        // aplicar os eventos de click
        aplicarEventoClick(myViewHolder, i);

    }


    private void pegarImgReceita(MyViewHolder myViewHolder, Recipe recipe) {
        if(!TextUtils.isEmpty(recipe.getSmallImageUrls().get(0))){
            Glide.with(mContext).load(recipe.getSmallImageUrls().get(0))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(myViewHolder.imageView);
            Log.i("ImagemReceita", "Conseguiu pegar a url da img da recipe.");
        } else {
            myViewHolder.imageView.setImageResource(R.drawable.img_padrao);
            Log.i("ImagemReceita", "Não conseguiu pegar a url");
        }
    }

    private void aplicarEventoClick(MyViewHolder myViewHolder, final int position) {
        myViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onImageClicked(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
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

    public interface ReceitaAdapaterListener {
        void onImageClicked(int position);
    }
}
