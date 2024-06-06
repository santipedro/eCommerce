package com.example.ecommerce;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.Adapter.PlantAdapter;
import com.example.ecommerce.databinding.ActivityTelaPrincipalBinding;
import com.example.ecommerce.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class TelaPrincipal extends AppCompatActivity {


    private ActivityTelaPrincipalBinding binding; //Passando variavel binding para puxar o Layout.
    private PlantAdapter plantAdapter;
    private TelaPrincipal telaPrincipal;
    private ArrayList<Produto> plantList = new ArrayList();
    private List<Produto> produtoList;
    private SearchView searchView;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTelaPrincipalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RecyclerView recyclerViewPlant = binding.recyclerviewPlant;
        recyclerViewPlant.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewPlant.setHasFixedSize(true);

        plantAdapter = new PlantAdapter(plantList, this);
        recyclerViewPlant.setAdapter(plantAdapter); //Exibir lista criada na RecyclerView
        SearchView searchView = (SearchView) findViewById(R.id.search);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                filterList(newText);

                return true;
            }
        });
        produtoList = new ArrayList<>();
        getPlant();

    }

    public void filterList(String newText) {
        List<Produto> filteredList = new ArrayList<>();

        for (Produto produto : produtoList){
            if (produto.getNomeplant().toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(produto);
            }
       }
        if (filteredList.isEmpty()){
           Toast.makeText(this,"Sem dados encontrados.", Toast.LENGTH_SHORT).show();
        }else{
            plantAdapter.setfilteredList(filteredList);
        }
    }


        private void getPlant(){

            Produto planta1 = new Produto(
                    R.drawable.suculenta,
                    "Texto da descrição",
                    "Suculenta",
                    "R$ 30,00"
            );
            plantList.add(planta1);

            Produto planta2 = new Produto(
                    R.drawable.tulipa,
                    "Texto da descrição",
                    "Tulipa",
                    "R$ 50,00"
            );
            plantList.add(planta2);

            Produto planta3 = new Produto(
                    R.drawable.lirio,
                    "Texto da descrição",
                    "Lírio",
                    "R$ 40,00"
            );
            plantList.add(planta3);

            Produto planta4 = new Produto(
                    R.drawable.samambaia,
                    "Texto da descrição",
                    "Samambaia",
                    "R$ 100,00"
            );
            plantList.add(planta4);

            Produto planta5 = new Produto(
                    R.drawable.espadastjorge,
                    "Texto da descrição",
                    "Espada-de-São-Jorge",
                    "R$ 100,00"
            );
            plantList.add(planta5);

            Produto planta6 = new Produto(
                    R.drawable.orquidea,
                    "Texto da descrição",
                    "Orquídea",
                    "R$ 35,00"
            );
            plantList.add(planta6);
        }
}