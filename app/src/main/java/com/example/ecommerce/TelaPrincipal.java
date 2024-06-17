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
    protected void onCreate(Bundle savedInstanceState) { // Método chamado na criação da atividade
        super.onCreate(savedInstanceState); // Chama o método onCreate da superclasse

        binding = ActivityTelaPrincipalBinding.inflate(getLayoutInflater()); // Infla o layout usando View Binding
        setContentView(binding.getRoot()); // Define o layout da atividade

        RecyclerView recyclerViewPlant = binding.recyclerviewPlant; // Obtém o RecyclerView do layout
        recyclerViewPlant.setLayoutManager(new LinearLayoutManager(this)); // Define o layout manager como LinearLayoutManager
        recyclerViewPlant.setHasFixedSize(true); // Define que o RecyclerView tem tamanho fixo

        plantAdapter = new PlantAdapter(plantList, this); // Inicializa o adapter com a lista de plantas
        recyclerViewPlant.setAdapter(plantAdapter); // Define o adapter para o RecyclerView

        SearchView searchView = (SearchView) findViewById(R.id.search); // Obtém o SearchView do layout

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // Define o listener para o SearchView
            @Override
            public boolean onQueryTextSubmit(String query) { // Método chamado quando a consulta é submetida
                return false; // Retorna false para indicar que a consulta não foi tratada
            }

            @Override
            public boolean onQueryTextChange(String newText) { // Método chamado quando o texto da consulta muda
                filterList(newText); // Chama o método para filtrar a lista

                return true; // Retorna true para indicar que a consulta foi tratada
            }
        });

        produtoList = new ArrayList<>(); // Inicializa a lista de produtos
        getPlant(); // Chama o método para obter os produtos
    }

    public void filterList(String newText) { // Método para filtrar a lista de produtos
        List<Produto> filteredList = new ArrayList<>(); // Cria uma lista para os produtos filtrados

        for (Produto produto : produtoList) { // Itera sobre a lista de produtos
            if (produto.getNomeplant().toLowerCase().contains(newText.toLowerCase())) { // Verifica se o nome da planta contém o texto da consulta
                filteredList.add(produto); // Adiciona o produto à lista filtrada
            }
        }

        if (filteredList.isEmpty()) { // Verifica se a lista filtrada está vazia
            Toast.makeText(this, "Sem dados encontrados.", Toast.LENGTH_SHORT).show(); // Mostra uma mensagem se a lista estiver vazia
        } else {
            plantAdapter.setfilteredList(filteredList); // Atualiza o adapter com a lista filtrada
        }
    }

    private void getPlant() { // Método para obter os produtos

        // Cria e adiciona os produtos à lista
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
