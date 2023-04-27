package com.example.viajecitosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Nivel1 extends AppCompatActivity {

    private TextView tv_nombre, tv_score, respuesta;
    private RadioButton rb_opcion1, rb_opcion2, rb_opcion3;
    private ImageView iv_banderas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel1);
        tv_nombre = (TextView) findViewById(R.id.tv_nombre);
        tv_score = (TextView) findViewById(R.id.tv_score);
        rb_opcion1 = (RadioButton) findViewById(R.id.rb_opcion1);
        rb_opcion2 = (RadioButton) findViewById(R.id.rb_opcion2);
        rb_opcion3 = (RadioButton) findViewById(R.id.rb_opcion3);
        iv_banderas = (ImageView) findViewById(R.id.iv_banderas);
        respuesta = (TextView) findViewById(R.id.tv_invisible);
// traer el nombre de la activity principal
        int puntosInt = getIntent().getIntExtra("puntos", 0);

        if (puntosInt == 0) {
            tv_score.setText("0");
        } else {
            String valor = String.valueOf(puntosInt);
            tv_score.setText(valor);
        }

        String viajero = getIntent().getStringExtra("nombre");
        tv_nombre.setText(viajero);


// hacer muestre imagen aleatoria de banderas

//meter en el mismo orden
        ArrayList<String> europa = new ArrayList<String>();
        int europa_bandera[] = {R.drawable.polonia,R.drawable.monaco,R.drawable.dinamarca,R.drawable.noruega,R.drawable.suiza};
        europa.add("Polonia");
        europa.add("Monaco");
        europa.add("Dinamarca");
        europa.add("Noruega");
        europa.add("Suiza");

        int n_bandera = numeroAleatorio("pais", europa.size());

        ArrayList<Integer> elegido = new ArrayList<Integer>();
        elegido.add(n_bandera);


        int i = 1;
        while (i < 5) {
            n_bandera = numeroAleatorio("pais", europa.size());
            if (!elegido.contains(n_bandera)) {
                elegido.add(n_bandera);
                i++;
            }
        }

        iv_banderas.setImageResource(europa_bandera[elegido.get(0)]);
        respuesta.setText(europa.get(0));

        int posicion_rb = numeroAleatorio("rb", 3);

        if (posicion_rb == 1) {
            rb_opcion1.setText(europa.get(elegido.get(0)));
            rb_opcion2.setText(europa.get(elegido.get(1)));
            rb_opcion3.setText(europa.get(elegido.get(2)));

        } else if (posicion_rb == 2) {
            rb_opcion1.setText(europa.get(elegido.get(2)));
            rb_opcion2.setText(europa.get(elegido.get(0)));
            rb_opcion3.setText(europa.get(elegido.get(1)));

        } else if (posicion_rb == 3) {
            rb_opcion1.setText(europa.get(elegido.get(1)));
            rb_opcion2.setText(europa.get(elegido.get(2)));
            rb_opcion3.setText(europa.get(elegido.get(0)));

        }
    }

    public static int numeroAleatorio(String tipo, int size) {
        int numero=0 ;

        if (tipo.equals("rb")) {
            numero = (int) (Math.random() * size + 1);
        } else {
            numero = (int) ((Math.random()) * size);
        }
        return numero;
    }


    public void comprobar(View view) {
        String correcta = respuesta.getText().toString();
        String scoreString = tv_score.getText().toString();
        int scoreInt=0;

        String seleccion = "";

        if (rb_opcion1.isChecked()) {
            seleccion = rb_opcion1.getText().toString();
        }
        if (rb_opcion2.isChecked()) {
            seleccion = rb_opcion2.getText().toString();
        }
        if (rb_opcion3.isChecked()) {
            seleccion = rb_opcion3.getText().toString();
        }


        if (correcta.equals(seleccion)) {
            Toast.makeText(this, "Respuesta correcta", Toast.LENGTH_SHORT).show();
            scoreInt = Integer.parseInt(scoreString);
            scoreInt+=2;

            Intent nivel1 = new Intent(this, Nivel1.class);
            nivel1.putExtra("puntos", scoreInt);
            startActivity(nivel1);

        } else {
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
        }

    }
}


