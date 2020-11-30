package cl.ejercicios.evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import cl.ejercicios.evaluacion.Modelo.ExposSaveDatabaseHelper;
import cl.ejercicios.evaluacion.Modelo.Exposicion;

public class MainActivity extends AppCompatActivity {
    private ExposSaveDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void irExhibiciones(View view){
        Intent intent=new Intent(this,ExhibicionActivity.class);
        startActivity(intent);
    }

    public void irAgregar(View view){
        Intent intent=new Intent(this,AgregarExpo.class);
        startActivity(intent);
    }
}