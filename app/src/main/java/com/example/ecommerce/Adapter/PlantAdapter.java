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

    public PlantAdapter(ArrayList<Produto> plantlist, Context context) { // Construtor da classe
        this.plantlist = plantlist; // Inicializa a lista de produtos
        this.context = context; // Inicializa o contexto
    }

    @NonNull
    @Override
    public PlantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // Método para criar um novo ViewHolder
        ProductItemBinding itemlist; // Declara uma variável para o binding do item de produto
        itemlist = ProductItemBinding.inflate(LayoutInflater.from(context), parent, false); // Infla o layout do item de produto
        return new PlantViewHolder(itemlist); // Retorna um novo ViewHolder
    }

    @Override
    public void onBindViewHolder(@NonNull PlantViewHolder holder, int position) { // Método para vincular dados ao ViewHolder
        holder.binding.imgplant.setBackgroundResource(plantlist.get(position).getImgplant()); // Define a imagem do produto
        holder.binding.txtImg.setText(plantlist.get(position).getNomeplant()); // Define o nome do produto
        holder.binding.descImg.setText(plantlist.get(position).getDescplant()); // Define a descrição do produto
        holder.binding.txtPreco.setText(plantlist.get(position).getPriceplant()); // Define o preço do produto
    }

    @Override
    public int getItemCount() { // Método para obter o número de itens na lista
        return plantlist.size(); // Retorna o tamanho da lista de produtos
    }

    public void setfilteredList(List<Produto> filteredList) { // Método para definir uma lista filtrada de produtos
        produtoList = filteredList; // Define a lista de produtos filtrados
        notifyDataSetChanged(); // Notifica que os dados foram alterados
    }

    public static class PlantViewHolder extends RecyclerView.ViewHolder { // Classe ViewHolder estática

        ProductItemBinding binding; // Declara uma variável para o binding do item de produto

        public PlantViewHolder(ProductItemBinding binding) { // Construtor da classe ViewHolder
            super(binding.getRoot()); // Chama o construtor da superclasse com a raiz do layout
            this.binding = binding; // Inicializa o binding
        }
    }

}
