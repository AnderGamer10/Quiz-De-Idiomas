package com.example.quizdeidiomas;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private int botonesRespuestas[] = {
            R.id.opcion1,R.id.opcion2,R.id.opcion3,R.id.opcion4
    };
    private int red = Color.argb(100, 255,0,0);
    private int green = Color.argb(100, 0,255,0);
    private int pregunta = 0, respCorrectas = 0, cantPreguntas = 0;

    private String correcto, textoBoton;
    Button botonComprobar1,botonComprobar2,botonComprobar3,botonComprobar4, botonContinuar, botonCompletar, botonReiniciar;
    ImageButton goblin, lich, sylvanas, anderwow;
    TextView preguntaDosPuntos, preguntaTexto, resultadosTexto, cantPreguntasTexto, cantAcertadasTexto, barra;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preguntaDosPuntos = findViewById(R.id.preguntaDosPuntos);
        preguntaTexto = findViewById(R.id.preguntaId);

        botonContinuar = findViewById(R.id.botonContinuar);
        botonCompletar = findViewById(R.id.btnCompletar);
        botonReiniciar = findViewById(R.id.btnReiniciar);
        botonCompletar.setVisibility(View.INVISIBLE);
        botonContinuar.setVisibility(View.VISIBLE);

        botonComprobar1 = findViewById(R.id.opcion1);
        botonComprobar2 = findViewById(R.id.opcion2);
        botonComprobar3 = findViewById(R.id.opcion3);
        botonComprobar4 = findViewById(R.id.opcion4);

        goblin = findViewById(R.id.goblinImg);
        lich = findViewById(R.id.lichImg);
        sylvanas = findViewById(R.id.sylvanasImg);
        anderwow = findViewById(R.id.anderwowImg);

        goblin.setVisibility(View.INVISIBLE);
        lich.setVisibility(View.INVISIBLE);
        sylvanas.setVisibility(View.INVISIBLE);
        anderwow.setVisibility(View.INVISIBLE);

        resultadosTexto = findViewById(R.id.Resultados);
        resultadosTexto.setVisibility(View.INVISIBLE);

        cantPreguntasTexto = findViewById(R.id.cantidadPreguntas);
        cantAcertadasTexto = findViewById(R.id.cantidadAcertadas);
        barra = findViewById(R.id.barra);
        cantPreguntasTexto.setVisibility(View.INVISIBLE);
        cantAcertadasTexto.setVisibility(View.INVISIBLE);
        barra.setVisibility(View.INVISIBLE);

//        Esto llamara por primera vez al metodo en el cual apareceran las preguntas con botones
        correcto = mostrarPregunta(pregunta);

//        Text To Spech para decirnos la pregunta cuando cliquemos en ella
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

                if(i!=TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });

        TextView textPreguntas = (TextView) findViewById(R.id.preguntaId);

        textPreguntas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeech.speak(textPreguntas.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);
            }
        });

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

                    botonComprobar1.setClickable(true);
                    botonComprobar2.setClickable(true);
                    botonComprobar3.setClickable(true);
                    botonComprobar4.setClickable(true);
                }else if(pregunta != getResources().getStringArray(R.array.preguntas).length){
                    botonComprobar1.setVisibility(View.INVISIBLE);
                    botonComprobar2.setVisibility(View.INVISIBLE);
                    botonComprobar3.setVisibility(View.INVISIBLE);
                    botonComprobar4.setVisibility(View.INVISIBLE);

                    goblin.setVisibility(View.VISIBLE);
                    lich.setVisibility(View.VISIBLE);
                    sylvanas.setVisibility(View.VISIBLE);
                    anderwow.setVisibility(View.VISIBLE);

                    botonCompletar.setVisibility(View.VISIBLE);
                    botonContinuar.setVisibility(View.INVISIBLE);
                    String[] preguntas = getResources().getStringArray(R.array.preguntasImg);
                    TextView text_pregunta = (TextView) findViewById(R.id.preguntaId);
                    text_pregunta.setText(preguntas[0]);
                }
                cantPreguntas++;
            }
        });

        botonCompletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cantPreguntas++;
                goblin.setVisibility(View.INVISIBLE);
                lich.setVisibility(View.INVISIBLE);
                sylvanas.setVisibility(View.INVISIBLE);
                anderwow.setVisibility(View.INVISIBLE);

                resultadosTexto.setVisibility(View.VISIBLE);

                preguntaDosPuntos.setVisibility(View.INVISIBLE);
                preguntaTexto.setVisibility(View.INVISIBLE);
                botonCompletar.setVisibility(View.INVISIBLE);

                cantPreguntasTexto.setVisibility(View.VISIBLE);
                cantAcertadasTexto.setVisibility(View.VISIBLE);
                barra.setVisibility(View.VISIBLE);
                cantPreguntasTexto.setText(String.valueOf(cantPreguntas));
                cantAcertadasTexto.setText(String.valueOf(respCorrectas));

                Log.i("correctas", String.valueOf(respCorrectas));
            }
        });

        botonReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reiniciarApp();
            }
        });


        goblin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Incorrecto", Toast.LENGTH_SHORT).show();
                goblin.setColorFilter(red);
                goblin.setClickable(false);
                lich.setEnabled(false);
                sylvanas.setEnabled(false);
                anderwow.setEnabled(false);
            }
        });
        lich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Correcto", Toast.LENGTH_SHORT).show();
                lich.setColorFilter(green);
                respCorrectas++;
                goblin.setEnabled(false);
                lich.setClickable(false);
                sylvanas.setEnabled(false);
                anderwow.setEnabled(false);
            }
        });
        sylvanas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Incorrecto", Toast.LENGTH_SHORT).show();
                sylvanas.setColorFilter(red);
                goblin.setEnabled(false);
                lich.setEnabled(false);
                sylvanas.setClickable(false);
                anderwow.setEnabled(false);
            }
        });
        anderwow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Incorrecto", Toast.LENGTH_SHORT).show();
                anderwow.setColorFilter(red);
                goblin.setEnabled(false);
                lich.setEnabled(false);
                sylvanas.setEnabled(false);
                anderwow.setClickable(false);
            }
        });


        botonComprobar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoBoton = (String) botonComprobar1.getText();
                if (Objects.equals(correcto, textoBoton)){
                    Toast.makeText(MainActivity.this, "Correcto", Toast.LENGTH_SHORT).show();
                    botonComprobar1.setBackgroundColor(Color.parseColor("#31C000"));
                    respCorrectas++;
                }else {
                    Toast.makeText(MainActivity.this, "Incorrecto", Toast.LENGTH_SHORT).show();
                    botonComprobar1.setBackgroundColor(Color.parseColor("#CD0000"));
                }
                botonComprobar1.setClickable(false);
                botonComprobar2.setEnabled(false);
                botonComprobar3.setEnabled(false);
                botonComprobar4.setEnabled(false);
            }
        });
        botonComprobar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoBoton = (String) botonComprobar2.getText();
                if (Objects.equals(correcto, textoBoton)){
                    Toast.makeText(MainActivity.this, "Correcto", Toast.LENGTH_SHORT).show();
                    botonComprobar2.setBackgroundColor(Color.parseColor("#31C000"));
                    respCorrectas++;
                }else {
                    Toast.makeText(MainActivity.this, "Incorrecto", Toast.LENGTH_SHORT).show();
                    botonComprobar2.setBackgroundColor(Color.parseColor("#CD0000"));
                }
                botonComprobar2.setClickable(false);
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
                    Toast.makeText(MainActivity.this, "Correcto", Toast.LENGTH_SHORT).show();
                    botonComprobar3.setBackgroundColor(Color.parseColor("#31C000"));
                    respCorrectas++;
                }else {
                    Toast.makeText(MainActivity.this, "Incorrecto", Toast.LENGTH_SHORT).show();
                    botonComprobar3.setBackgroundColor(Color.parseColor("#CD0000"));
                }
                botonComprobar3.setClickable(false);
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
                    Toast.makeText(MainActivity.this, "Correcto", Toast.LENGTH_SHORT).show();
                    botonComprobar4.setBackgroundColor(Color.parseColor("#31C000"));
                    respCorrectas++;
                }else {
                    Toast.makeText(MainActivity.this, "Incorrecto", Toast.LENGTH_SHORT).show();
                    botonComprobar4.setBackgroundColor(Color.parseColor("#CD0000"));
                }
                botonComprobar4.setClickable(false);
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

    public void reiniciarApp() {
        Activity mActivity = MainActivity.this;
        mActivity.recreate();
    }
}