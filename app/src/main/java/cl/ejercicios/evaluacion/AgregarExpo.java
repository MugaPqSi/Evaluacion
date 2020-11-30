package cl.ejercicios.evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import cl.ejercicios.evaluacion.Modelo.ExposSaveDatabaseHelper;
import cl.ejercicios.evaluacion.Modelo.Exposicion;

public class AgregarExpo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_expo);
    }

    public void agregarExpo(View view){
        ExposSaveDatabaseHelper helper=new ExposSaveDatabaseHelper(this);
        int id= Integer.parseInt(((TextView) findViewById(R.id.idTxT)).getText().toString());
        String nombre=((TextView) findViewById(R.id.nombreTxT)).getText().toString();
        String descripcion=((TextView) findViewById(R.id.descripcionTxt)).getText().toString();
        int estado= Integer.parseInt(((TextView) findViewById(R.id.estadoTxt)).getText().toString());
        try {
            if (!nombre.equals("") && !descripcion.equals("")) {
                Exposicion exposicion=new Exposicion(id,nombre,descripcion,estado);
                helper.agregar(exposicion);
                finish();
            }else if (nombre.equals("")){
                Toast.makeText(this,"Ingrese un nombre",Toast.LENGTH_SHORT);
            }else {
                Toast.makeText(this,"Ingrese una Descripción",Toast.LENGTH_SHORT);
            }
        }catch (NumberFormatException ex){
            Toast.makeText(this,"Error: " + ex,Toast.LENGTH_SHORT);
        }
    }
}