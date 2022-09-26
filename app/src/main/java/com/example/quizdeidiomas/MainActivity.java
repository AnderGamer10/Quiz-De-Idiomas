package com.example.quizdeidiomas;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private int botonesRespuestas[] = {
            R.id.opcion1,R.id.opcion2,R.id.opcion3,R.id.opcion4
    };
    private int pregunta = 0;
    private String correcto, textoBoton;
    Button botonComprobar1,botonComprobar2,botonComprobar3,botonComprobar4, botonContinuar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getResources().getStringArray(R.array.preguntas);

        correcto = mostrarPregunta(pregunta);

        botonContinuar = findViewById(R.id.botonContinuar);

        botonComprobar1 = findViewById(R.id.opcion1);
        botonComprobar2 = findViewById(R.id.opcion2);
        botonComprobar3 = findViewById(R.id.opcion3);
        botonComprobar4 = findViewById(R.id.opcion4);


        botonContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pregunta != getResources().getStringArray(R.array.preguntas).length-1){
                    pregunta++;
                    correcto = mostrarPregunta(pregunta);
                    botonComprobar1.setBackgroundColor(Color.parseColor("#FF6200EE"));
                    botonComprobar2.setBackgroundColor(Color.parseColor("#FF6200EE"));
                    botonComprobar3.setBackgroundColor(Color.parseColor("#FF6200EE"));
                    botonComprobar4.setBackgroundColor(Color.parseColor("#FF6200EE"));
                    botonComprobar1.setEnabled(true);
                    botonComprobar2.setEnabled(true);
                    botonComprobar3.setEnabled(true);
                    botonComprobar4.setEnabled(true);
                }
            }
        });

        botonComprobar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoBoton = (String) botonComprobar1.getText();
                if (Objects.equals(correcto, textoBoton)){
                    Toast.makeText(MainActivity.this, "Correcto", Toast.LENGTH_LONG).show();
                    botonComprobar1.setBackgroundColor(Color.parseColor("#31C000"));
                }else {
                    Toast.makeText(MainActivity.this, "Incorrecto", Toast.LENGTH_LONG).show();
                    botonComprobar1.setBackgroundColor(Color.parseColor("#CD0000"));
                }
                botonComprobar2.setEnabled(false);
                botonComprobar3.setEnabled(false);
                botonComprobar4.setEnabled(false);
                Log.i("Correcto",correcto);
                Log.i("Texto",textoBoton);
            }
        });
        botonComprobar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoBoton = (String) botonComprobar2.getText();
                if (Objects.equals(correcto, textoBoton)){
                    Toast.makeText(MainActivity.this, "Correcto", Toast.LENGTH_LONG).show();
                    botonComprobar2.setBackgroundColor(Color.parseColor("#31C000"));
                }else {
                    Toast.makeText(MainActivity.this, "Incorrecto", Toast.LENGTH_LONG).show();
                    botonComprobar2.setBackgroundColor(Color.parseColor("#CD0000"));
                }
                botonComprobar1.setEnabled(false);
                botonComprobar3.setEnabled(false);
                botonComprobar4.setEnabled(false);
            }
        });
        botonComprobar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoBoton = (String) botonComprobar3.getText();
                if (Objects.equals(correcto, textoBoton)){
                    Toast.makeText(MainActivity.this, "Correcto", Toast.LENGTH_LONG).show();
                    botonComprobar3.setBackgroundColor(Color.parseColor("#31C000"));
                }else {
                    Toast.makeText(MainActivity.this, "Incorrecto", Toast.LENGTH_LONG).show();
                    botonComprobar3.setBackgroundColor(Color.parseColor("#CD0000"));
                }
                botonComprobar1.setEnabled(false);
                botonComprobar2.setEnabled(false);
                botonComprobar4.setEnabled(false);
            }
        });
        botonComprobar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoBoton = (String) botonComprobar4.getText();
                if (Objects.equals(correcto, textoBoton)){
                    Toast.makeText(MainActivity.this, "Correcto", Toast.LENGTH_LONG).show();
                    botonComprobar4.setBackgroundColor(Color.parseColor("#31C000"));
                }else {
                    Toast.makeText(MainActivity.this, "Incorrecto", Toast.LENGTH_LONG).show();
                    botonComprobar4.setBackgroundColor(Color.parseColor("#CD0000"));
                }
                botonComprobar1.setEnabled(false);
                botonComprobar2.setEnabled(false);
                botonComprobar3.setEnabled(false);
            }
        });
    }
    private String mostrarPregunta(int num){
        String[] preguntas = getResources().getStringArray(R.array.preguntas);
        String[] opcion = preguntas[num].split(";");
        TextView text_pregunta = (TextView) findViewById(R.id.preguntaId);
        text_pregunta.setText(opcion[0]);
        String correcto = "";
        for (int x = 0; x < opcion.length-1;x++){
            Log.i("s",opcion[x]);
            TextView text_resp = (TextView) findViewById(botonesRespuestas[x]);
            if (x == 0){
                if (opcion[x+1].charAt(0) == '*'){
                    text_resp.setText(opcion[x+1].substring(1));
                    correcto = opcion[x+1].substring(1);
                }else {
                    text_resp.setText(opcion[x+1]);
                }
            }else{
                if (opcion[x+1].charAt(0) == '*'){
                    text_resp.setText(opcion[x+1].substring(1));
                    correcto = opcion[x+1].substring(1);
                }else {
                    text_resp.setText(opcion[x+1]);
                }
            }
        }
        return correcto;
    }
}