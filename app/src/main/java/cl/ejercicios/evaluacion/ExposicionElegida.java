package cl.ejercicios.evaluacion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import cl.ejercicios.evaluacion.Modelo.ExposSaveDatabaseHelper;
import cl.ejercicios.evaluacion.Modelo.Exposicion;
import cl.ejercicios.evaluacion.Modelo.ListaExpos;

public class ExposicionElegida extends AppCompatActivity {
    private Exposicion expo;
    private Intent intent;
    private ExposSaveDatabaseHelper helper=new ExposSaveDatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exposicion_elegida);

        intent=getIntent();
        String nombre2=(String) intent.getExtras().get(("nombreExpo"));
        expo=helper.getExpo(nombre2);

        TextView nombre=(TextView) findViewById(R.id.nombre);
        nombre.setText(expo.getNombre());

        TextView descripcion=(TextView) findViewById(R.id.descripcion);
        descripcion.setText(expo.getDescripcion());
    }

    public void modificar(View view){
        ExposSaveDatabaseHelper helper=new ExposSaveDatabaseHelper(this);
        String nombre = ((TextView) findViewById(R.id.nombre)).getText().toString();
        Intent intent=new Intent(this, ModificarExpo.class);
        intent.putExtra("nombreExpo", nombre);
        startActivityForResult(intent,1);
    }
}