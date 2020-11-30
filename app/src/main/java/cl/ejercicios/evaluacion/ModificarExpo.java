package cl.ejercicios.evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import cl.ejercicios.evaluacion.Modelo.ExposSaveDatabaseHelper;
import cl.ejercicios.evaluacion.Modelo.Exposicion;

public class ModificarExpo extends AppCompatActivity {
    private Exposicion expo;
    private Intent intent;
    private ExposSaveDatabaseHelper helper=new ExposSaveDatabaseHelper(this);
    private String atent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_expo);

        intent=getIntent();
        String nombre2=(String) intent.getExtras().get(("nombreExpo"));
        atent=(String) intent.getExtras().get(("nombreExpo"));
        expo=helper.getExpo(nombre2);

        TextView nombre=(TextView) findViewById(R.id.nombreTxt);
        nombre.setText(nombre2);

        TextView descripcion=(TextView) findViewById(R.id.descripcionTxt);
        descripcion.setText(expo.getDescripcion());
    }

    public void modificarExpo(View view){
        expo=helper.getExpo(atent);
        String nombre=((TextView) findViewById(R.id.nombreTxt)).getText().toString();
        String descripcion=((TextView) findViewById(R.id.descripcionTxt)).getText().toString();
        try {
            if (!nombre.equals("") && !descripcion.equals("")) {
                Exposicion exposicion=new Exposicion(expo.id,nombre,descripcion,0);
                helper.updateExpo(exposicion);
                finish();
                Intent intent=new Intent(this,ExhibicionActivity.class);
                startActivity(intent);
            }else if (nombre.equals("")){
                Toast.makeText(this,"Ingrese un nombre",Toast.LENGTH_SHORT);
            }else {
                Toast.makeText(this,"Ingrese una Descripción",Toast.LENGTH_SHORT);
            }
        }catch (NumberFormatException ex){
            Toast.makeText(this,"Error: " + ex,Toast.LENGTH_SHORT);
        }
    }

    public void eliminarExpo(View view){
        expo=helper.getExpo(atent);
        try {
                helper.limpiarExpos(expo.id);
                finish();
        }catch (NumberFormatException ex){
            Toast.makeText(this,"Error: " + ex,Toast.LENGTH_SHORT);
        }
        Intent intent=new Intent(this,ExhibicionActivity.class);
        startActivity(intent);
    }
}