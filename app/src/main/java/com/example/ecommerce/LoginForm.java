package com.example.ecommerce;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginForm extends AppCompatActivity {

    private TextView tela_cadastro;
    private EditText edit_email, edit_senha;
    private Button button_entrar;
    private ProgressBar progressbar;
    String[] messages = {"Preencher todos os campos", "Login conclúido com sucesso"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_sign);
        IniciarCadastro();
            tela_cadastro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LoginForm.this,CadastroForm.class);
                    startActivity(intent);
                }
            });

            button_entrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = edit_email.getText().toString();
                    String senha = edit_senha.getText().toString();

                    if (email.isEmpty() || senha.isEmpty()){
                        Snackbar snackbar = Snackbar.make(v,messages[0],Snackbar.LENGTH_SHORT );
                        snackbar.setBackgroundTint(Color.WHITE);
                        snackbar.setTextColor(Color.BLACK);
                        snackbar.show();
                    }else{
                        AuthUsers();
                    }
                }
            });

    }
    private void AuthUsers(){
        String email = edit_email.getText().toString();
        String senha = edit_senha.getText().toString();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                 progressbar.setVisibility(View.VISIBLE);
                /* new Handler().postDelayed(new Runnable() {
                     @Override
                     public void run() {

                     }
                 })*/ //Criar metódo com Intent para levar à tela principal (Após criação da mesma.);
                }
            }
        });
    }
    private void IniciarCadastro(){
        tela_cadastro = findViewById(R.id.textelacastrar);
        edit_email = findViewById(R.id.edit_email);
        edit_senha = findViewById(R.id.edit_senha);
        button_entrar = findViewById(R.id.button_entrar);
        progressbar = findViewById(R.id.progressbar);
    }

}