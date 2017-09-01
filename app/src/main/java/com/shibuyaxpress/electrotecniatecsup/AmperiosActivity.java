package com.shibuyaxpress.electrotecniatecsup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AmperiosActivity extends AppCompatActivity {
    EditText txtVolts,txtOhm,txtResultado;
    Button btnCalcular;
    double x,y,z;
    //se pasaran dos arraylist con los puntos de los ejes
    ArrayList<Double> puntosX= new ArrayList<>();
    ArrayList<Double> puntosY=new ArrayList<>();
    //el contador vera si hay la misma cantidad de puntos por eje para evitar problemas
    int contadorX=0,contadorY=0;
    int sample=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amperios);


        txtVolts=(EditText)findViewById(R.id.txtA);
        txtOhm=(EditText)findViewById(R.id.txtB);
        txtResultado=(EditText)findViewById(R.id.txtC);
        btnCalcular=(Button)findViewById(R.id.btnCalcular);




    }

    public void calcular(View view){
       try {
           x = Double.parseDouble(txtVolts.getText().toString());
           y = Double.parseDouble(txtOhm.getText().toString());
           z = x / y;
           txtResultado.setText(String.valueOf(z));
           txtResultado.setEnabled(false);
       }catch (Exception e1){

       }

    }

    public void AgregaX(View view){
        try {
            puntosX.add(Double.parseDouble(txtVolts.getText().toString()));
            contadorX++;
            Toast.makeText(getApplicationContext(), String.valueOf(puntosX.size()), Toast.LENGTH_SHORT).show();
        }catch (Exception e1){

        }

    }

    public void AgregaY(View view){
        try {
            puntosY.add(Double.parseDouble(txtOhm.getText().toString()));
            contadorY++;
            Toast.makeText(getApplicationContext(), String.valueOf(puntosY.size())+"° punto añadido", Toast.LENGTH_SHORT).show();

        }catch (Exception e1){

        }
    }

    public void Limpiar(View view){
        puntosY.clear();
        puntosX.clear();
        txtOhm.setText(" ");
        txtResultado.setText(" ");
        txtVolts.setText(" ");
        Toast.makeText(getApplicationContext(), "Se han eliminado los parámetros de la gráfica", Toast.LENGTH_SHORT).show();

    }

    public void pass(View view){
        if(puntosX.size()==puntosY.size()) {
            Intent act = new Intent();
            act.setClass(getApplicationContext(), GraficoActivity.class);
            act.putExtra("listaX",puntosX);
            act.putExtra("listaY",puntosY);
            act.putExtra("sample",sample);
            //Toast.makeText(getApplicationContext(),puntosX.size()+"   "+puntosY.size(),Toast.LENGTH_SHORT).show();
            startActivity(act);

        }
        else{
            Toast.makeText(getApplicationContext(),"no se puede iniciar por tener una cantidad de puntos desiguales", Toast.LENGTH_SHORT).show();

        }
    }
}
