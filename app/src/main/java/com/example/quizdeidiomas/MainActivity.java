package com.example.quizdeidiomas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int botonesRespuestas[] = {
            R.id.opcion1,R.id.opcion2,R.id.opcion3,R.id.opcion4
    };
    Button botonComprobar1,botonComprobar2,botonComprobar3,botonComprobar4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getResources().getStringArray(R.array.preguntas);
        String correcto = mostrarPregunta(0);

        botonComprobar1 = findViewById(R.id.opcion1);
        botonComprobar2 = findViewById(R.id.opcion2);
        botonComprobar3 = findViewById(R.id.opcion3);
        botonComprobar4 = findViewById(R.id.opcion4);

        botonComprobar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textoBoton = (String) botonComprobar1.getText();
                if (correcto == textoBoton){
                    Toast.makeText(MainActivity.this, "Correcto", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(MainActivity.this, "Incorrecto", Toast.LENGTH_LONG).show();
                }
                Log.i("Correcto",correcto);
                Log.i("Texto",textoBoton);
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