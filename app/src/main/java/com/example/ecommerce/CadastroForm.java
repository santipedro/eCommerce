package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        IniciarComponents();

        button_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = edit_nome.getText().toString();
                String email = email_cadastro.getText().toString();
                String senha = senha_cadastro.getText().toString();

                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(v,messages[0],Snackbar.LENGTH_SHORT );
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }else{
                    CadastrarUser(v);
                }
            }
        });

    }

    private void CadastrarUser(View v){

        String email = email_cadastro.getText().toString();
        String senha = senha_cadastro.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    SaveUserData();
                    Snackbar snackbar = Snackbar.make(v,messages[1],Snackbar.LENGTH_SHORT );
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }else {
                    String error;

                    try {
                        throw task.getException();

                    }catch (FirebaseAuthWeakPasswordException e) {
                        error = "Insira no mínimo 6 caracteres!";

                    }catch(FirebaseAuthUserCollisionException e) {
                        error = "Este e-mail já foi cadastrado!";

                    }catch (FirebaseAuthInvalidCredentialsException e) {
                        error = "E-mail inserido de forma incorreta!";

                    }catch (Exception e){
                        error = "Erro no cadastro de novo usuário, digite novamente!";

                    }
                    Snackbar snackbar = Snackbar.make(v,error,Snackbar.LENGTH_SHORT );
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
            }
        });
    }

    private void SaveUserData(){
        String nome = edit_nome.getText().toString();

        FirebaseFirestore database = FirebaseFirestore.getInstance();
        Map<String,Object> users = new HashMap<>();

        userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //Criação de um Log para resgistrar e pesquisar se o User foi salvo no banco de dados.

        DocumentReference docReference = database.collection("Users").document(userID);
        docReference.set(users).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("database", "Dados salvos com sucesso!");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                            Log.d("database_error","Erro ao salvar os dados!");

                    }
                });
    }
    private void IniciarComponents(){

        edit_nome = findViewById(R.id.edit_nome);
        email_cadastro = findViewById(R.id.email_cadastro);
        senha_cadastro = findViewById(R.id.senha_cadastro);
        button_cadastro = findViewById(R.id.button_cadastro);

    }
}