package com.example.quizdeidiomas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    Button btnCambiarAQuizPrincipal, botonReiniciar;
    ImageButton btnAnderwow, btnGoblin, btnLichking, btnSylvanas;
    int numPregunta = 0;
    String correcta = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        btnCambiarAQuizPrincipal = findViewById(R.id.btnCambiarAQuizPrincipal);
        botonReiniciar = findViewById(R.id.btnReiniciar);

        btnAnderwow = findViewById(R.id.btnAnderwow);
        btnGoblin = findViewById(R.id.btnGoblin);
        btnLichking = findViewById(R.id.btnLichking);
        btnSylvanas = findViewById(R.id.btnSylvanas);

        correcta = mostrarPregunta(numPregunta);

        btnAnderwow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (correcta.equals("btnAnderwow")){
                    btnAnderwow.setVisibility(View.INVISIBLE);
                    correcta = mostrarPregunta(2);
                }

            }
        });

        btnGoblin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (correcta.equals("btnGoblin")){
                    btnGoblin.setVisibility(View.INVISIBLE);
                }

            }
        });

        btnLichking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (correcta.equals("btnLichking")){
                    btnLichking.setVisibility(View.INVISIBLE);
                    correcta = mostrarPregunta(1);
                }
            }
        });

        btnSylvanas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (correcta.equals("btnSylvanas")){
                    btnSylvanas.setVisibility(View.INVISIBLE);
                    correcta = mostrarPregunta(3);
                }
            }
        });

        botonReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reiniciarApp();
            }
        });

        btnCambiarAQuizPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private String mostrarPregunta(int num){
        String[] preguntas = getResources().getStringArray(R.array.preguntasActivity2);
        TextView text_pregunta = (TextView) findViewById(R.id.preguntaId);
        text_pregunta.setText(preguntas[num]);
        switch (num){
            case 0:
                return "btnLichking";
            case 1:
                return "btnSylvanas";
            case 2:
                return "btnGoblin";
            case 3:
                return "btnAnderwow";
        }
        return null;
    }

    public void reiniciarApp() {
        Activity mActivity = MainActivity2.this;
        mActivity.recreate();
    }

}