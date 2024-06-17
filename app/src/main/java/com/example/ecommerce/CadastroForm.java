package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CadastroForm extends AppCompatActivity {

    private EditText edit_nome,email_cadastro,senha_cadastro;
    private Button button_cadastro;
    private String userID;
    String[] messages = {"Preencher todos os campos", "Cadastro Conclúido com Sucesso"};

    @Override
   protected void onCreate(Bundle savedInstanceState) { // Método chamado na criação da atividade
        super.onCreate(savedInstanceState); // Chama o método onCreate da superclasse
        setContentView(R.layout.activity_cadastro_form); // Define o layout da atividade

        IniciarComponents(); // Inicializa os componentes da interface

        button_cadastro.setOnClickListener(new View.OnClickListener() { // Define ação para o botão de cadastro
            @Override
            public void onClick(View v) { // Método chamado quando o botão é clicado
                String nome = edit_nome.getText().toString(); // Obtém o texto do campo nome
                String email = email_cadastro.getText().toString(); // Obtém o texto do campo email
                String senha = senha_cadastro.getText().toString(); // Obtém o texto do campo senha

                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) { // Verifica se algum campo está vazio
                    Snackbar snackbar = Snackbar.make(v, messages[0], Snackbar.LENGTH_SHORT); // Cria uma Snackbar para notificar o usuário
                    snackbar.setBackgroundTint(Color.WHITE); // Define a cor de fundo da Snackbar
                    snackbar.setTextColor(Color.BLACK); // Define a cor do texto da Snackbar
                    snackbar.show(); // Mostra a Snackbar
                } else {
                    CadastrarUser(v); // Chama o método para cadastrar o usuário
                }
            }
        });
    }

    private void CadastrarUser(View v) { // Método para cadastrar o usuário

        String email = email_cadastro.getText().toString(); // Obtém o texto do campo email
        String senha = senha_cadastro.getText().toString(); // Obtém o texto do campo senha

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() { // Tenta criar o usuário com email e senha
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) { // Método chamado quando a tarefa é concluída
                if (task.isSuccessful()) { // Verifica se a criação do usuário foi bem-sucedida
                    SaveUserData(); // Salva os dados do usuário
                    Snackbar snackbar = Snackbar.make(v, messages[1], Snackbar.LENGTH_SHORT); // Cria uma Snackbar para notificar sucesso
                    snackbar.setBackgroundTint(Color.WHITE); // Define a cor de fundo da Snackbar
                    snackbar.setTextColor(Color.BLACK); // Define a cor do texto da Snackbar
                    snackbar.show(); // Mostra a Snackbar
                } else {
                    String error;

                    try {
                        throw task.getException(); // Lança a exceção capturada

                    } catch (FirebaseAuthWeakPasswordException e) {
                        error = "Insira no mínimo 6 caracteres!"; // Define a mensagem de erro para senha fraca

                    } catch (FirebaseAuthUserCollisionException e) {
                        error = "Este e-mail já foi cadastrado!"; // Define a mensagem de erro para e-mail já cadastrado

                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        error = "E-mail inserido de forma incorreta!"; // Define a mensagem de erro para e-mail inválido

                    } catch (Exception e) {
                        error = "Erro no cadastro de novo usuário, digite novamente!"; // Define a mensagem de erro genérica

                    }
                    Snackbar snackbar = Snackbar.make(v,error,Snackbar.LENGTH_SHORT );
                                        Snackbar snackbar = Snackbar.make(v, error, Snackbar.LENGTH_SHORT); // Cria uma Snackbar para notificar o erro
                    snackbar.setBackgroundTint(Color.WHITE); // Define a cor de fundo da Snackbar
                    snackbar.setTextColor(Color.BLACK); // Define a cor do texto da Snackbar
                    snackbar.show(); // Mostra a Snackbar
                }
            }
        });
    }

    private void SaveUserData() { // Método para salvar os dados do usuário
        String nome = edit_nome.getText().toString(); // Obtém o texto do campo nome

        FirebaseFirestore database = FirebaseFirestore.getInstance(); // Obtém uma instância do FirebaseFirestore
        Map<String, Object> users = new HashMap<>(); // Cria um mapa para armazenar os dados do usuário

        userID = FirebaseAuth.getInstance().getCurrentUser().getUid(); // Obtém o ID do usuário atual

        // Criação de um Log para registrar e verificar se o usuário foi salvo no banco de dados.
        DocumentReference docReference = database.collection("Users").document(userID); // Obtém uma referência para o documento do usuário
        docReference.set(users).addOnSuccessListener(new OnSuccessListener<Void>() { // Tenta salvar os dados do usuário no Firestore
            @Override
            public void onSuccess(Void unused) { // Método chamado quando os dados são salvos com sucesso
                Log.d("database", "Dados salvos com sucesso!"); // Log de sucesso
            }
        })
        .addOnFailureListener(new OnFailureListener() { // Método chamado em caso de falha ao salvar os dados
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("database_error", "Erro ao salvar os dados!"); // Log de erro
            }
        });
    }

    private void IniciarComponents() { // Método para inicializar os componentes da interface
        edit_nome = findViewById(R.id.edit_nome); // Inicializa o campo nome
        email_cadastro = findViewById(R.id.email_cadastro); // Inicializa o campo email
        senha_cadastro = findViewById(R.id.senha_cadastro); // Inicializa o campo senha
        button_cadastro = findViewById(R.id.button_cadastro); // Inicializa o botão de cadastro
    }
}
