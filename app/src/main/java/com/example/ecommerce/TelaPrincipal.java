package com.example.ecommerce;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.Adapter.PlantAdapter;
import com.example.ecommerce.databinding.ActivityTelaPrincipalBinding;
import com.example.ecommerce.model.Produto;

import java.util.ArrayList;

public class TelaPrincipal extends AppCompatActivity {


    private ActivityTelaPrincipalBinding binding; //Passando variavel binding para puxar o Layout.
    private PlantAdapter plantAdapter;
    private ArrayList<Produto> plantList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTelaPrincipalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RecyclerView recyclerViewPlant = binding.recyclerviewPlant;
        recyclerViewPlant.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewPlant.setHasFixedSize(true);

        plantAdapter = new PlantAdapter(plantList,this);
        recyclerViewPlant.setAdapter(plantAdapter); //Exibir lista criada na RecyclerView

        }

    private void getPlant(){

        Produto planta1 = new Produto(
                R.drawable.suculenta,
                "Texto da descrição",
                "Suculenta",
                "R$ 30,00"
        );
        plantList.add(planta1);
        //adicionar as outras plantas
    }
}