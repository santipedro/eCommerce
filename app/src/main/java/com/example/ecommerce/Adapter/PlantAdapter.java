package com.example.ecommerce.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.databinding.ProductItemBinding;
import com.example.ecommerce.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.PlantViewHolder> {

    private final ArrayList <Produto> plantlist; //Uso do FINAL por ser uma variável constante.
    private List<Produto> produtoList;
    private final Context context;

    public PlantAdapter(ArrayList<Produto> plantlist, Context context) {
        this.plantlist = plantlist;
        this.context = context;
    }


    @NonNull
    @Override
    public PlantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductItemBinding itemlist;
        itemlist = ProductItemBinding.inflate(LayoutInflater.from(context),parent, false);
        return new PlantViewHolder(itemlist);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantViewHolder holder, int position) {
        holder.binding.imgplant.setBackgroundResource(plantlist.get(position).getImgplant()); // O adapter resgata cada produto e "seta" nos seus respectivos nomes/preços etc.
        holder.binding.txtImg.setText(plantlist.get(position).getNomeplant());
        holder.binding.descImg.setText(plantlist.get(position).getDescplant());
        holder.binding.txtPreco.setText(plantlist.get(position).getPriceplant());
    }

    @Override
    public int getItemCount() {
        return plantlist.size();
    }

    public void setfilteredList(List<Produto> filteredList){
        produtoList = filteredList;
        notifyDataSetChanged();
    }

    public static class PlantViewHolder extends RecyclerView.ViewHolder{

        ProductItemBinding binding;

        public PlantViewHolder(ProductItemBinding binding){
            super(binding.getRoot()); //Pegar referencias do layout 'product_item'
            this.binding = binding;
        }
    }

}
