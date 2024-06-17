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
import com.google.firebase.auth.FirebaseUser;

public class LoginForm extends AppCompatActivity {

    private TextView tela_cadastro;
    private EditText edit_email, edit_senha;
    private Button button_entrar;
    private ProgressBar progressbar;
    String[] messages = {"Preencher todos os campos", "Login conclúido com sucesso"};
    @Override
    protected void onCreate(Bundle savedInstanceState) { // Método chamado na criação da atividade
        super.onCreate(savedInstanceState); // Chama o método onCreate da superclasse
        EdgeToEdge.enable(this); // Habilita o modo EdgeToEdge na atividade
        setContentView(R.layout.activity_login_sign); // Define o layout da atividade
        IniciarCadastro(); // Inicializa os componentes da interface

        tela_cadastro.setOnClickListener(new View.OnClickListener() { // Define ação para o TextView de cadastro
            @Override
            public void onClick(View v) { // Método chamado quando o TextView é clicado
                Intent intent = new Intent(LoginForm.this, CadastroForm.class); // Cria uma Intent para abrir a atividade de cadastro
                startActivity(intent); // Inicia a atividade de cadastro
            }
        });

        button_entrar.setOnClickListener(new View.OnClickListener() { // Define ação para o botão de login
            @Override
            public void onClick(View v) { // Método chamado quando o botão é clicado
                String email = edit_email.getText().toString(); // Obtém o texto do campo email
                String senha = edit_senha.getText().toString(); // Obtém o texto do campo senha

                if (email.isEmpty() || senha.isEmpty()) { // Verifica se algum campo está vazio
                    Snackbar snackbar = Snackbar.make(v, messages[0], Snackbar.LENGTH_SHORT); // Cria uma Snackbar para notificar o usuário
                    snackbar.setBackgroundTint(Color.WHITE); // Define a cor de fundo da Snackbar
                    snackbar.setTextColor(Color.BLACK); // Define a cor do texto da Snackbar
                    snackbar.show(); // Mostra a Snackbar
                } else {
                    AuthUsers(v); // Chama o método para autenticar o usuário
                }
            }
        });
    }

    private void AuthUsers(View view) { // Método para autenticar o usuário
        String email = edit_email.getText().toString(); // Obtém o texto do campo email
        String senha = edit_senha.getText().toString(); // Obtém o texto do campo senha

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() { // Tenta autenticar o usuário com email e senha
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) { // Método chamado quando a tarefa é concluída
                if (task.isSuccessful()) { // Verifica se a autenticação foi bem-sucedida
                    progressbar.setVisibility(View.VISIBLE); // Torna a ProgressBar visível
                    new Handler().postDelayed(new Runnable() { // Cria um Handler para executar um Runnable após um atraso
                        @Override
                        public void run() {
                            TelaPrincipal(); // Chama o método para abrir a tela principal
                        }
                    }, 3000); // Define um atraso de 3000 milissegundos (3 segundos)
                } else { // Em caso de falha na autenticação
                    String error; // Declara variável para armazenar a mensagem de erro
                    try {
                        throw task.getException(); // Lança a exceção capturada
                    } catch (Exception e) {
                        error = "Usuário ou Senha Incorretos!"; // Define a mensagem de erro genérica
                    }
                    Snackbar snackbar = Snackbar.make(view, error, Snackbar.LENGTH_SHORT); // Cria uma Snackbar para notificar o erro
                    snackbar.setBackgroundTint(Color.WHITE); // Define a cor de fundo da Snackbar
                    snackbar.setTextColor(Color.BLACK); // Define a cor do texto da Snackbar
                    snackbar.show(); // Mostra a Snackbar
                }
            }
        });
    }

    @Override
    protected void onStart() { // Método chamado quando a atividade é iniciada
        super.onStart(); // Chama o método onStart da superclasse

        FirebaseUser userAtual = FirebaseAuth.getInstance().getCurrentUser(); // Obtém o usuário atual autenticado

        if (userAtual != null) { // Verifica se já existe um usuário logado
            TelaPrincipal(); // Chama o método para abrir a tela principal
        }
    }

    private void TelaPrincipal() { // Método para abrir a tela principal
        Intent intent = new Intent(LoginForm.this, TelaPrincipal.class); // Cria uma Intent para abrir a tela principal
        startActivity(intent); // Inicia a atividade da tela principal
        finish(); // Encerra a atividade atual
    }

    private void IniciarCadastro() { // Método para inicializar os componentes da interface
        tela_cadastro = findViewById(R.id.textelacastrar); // Inicializa o TextView de cadastro
        edit_email = findViewById(R.id.edit_email); // Inicializa o campo email
        edit_senha = findViewById(R.id.edit_senha); // Inicializa o campo senha
        button_entrar = findViewById(R.id.button_entrar); // Inicializa o botão de login
        progressbar = findViewById(R.id.progressbar); // Inicializa a ProgressBar
    }
}
