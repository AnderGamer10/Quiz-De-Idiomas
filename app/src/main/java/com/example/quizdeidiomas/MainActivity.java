package com.example.quizdeidiomas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int botonesRespuestas[] = {
            R.id.BotonRespuesta1,R.id.BotonRespuesta2,R.id.BotonRespuesta3,R.id.BotonRespuesta4
    };
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getResources().getStringArray(R.array.preguntas);
        mostrarPregunta();
    }
    private void mostrarPregunta(){
        String[] preguntas = getResources().getStringArray(R.array.preguntas);
        String[] opcion = preguntas[0].split(";");
        TextView text_pregunta = (TextView) findViewById(R.id.preguntaId);
        text_pregunta.setText(opcion[0]);
        for (int x = 0; x < opcion.length;x++){
            Log.i("s",opcion[x]);
//            TextView text_resp = (TextView) findViewById(botonesRespuestas[x]);
//            if (opcion[x].charAt(0) == '*'){
//                text_resp.setText(opcion[x].substring(0));
//            }else {
//                text_resp.setText(opcion[x]);
//            }

        }
    }
}