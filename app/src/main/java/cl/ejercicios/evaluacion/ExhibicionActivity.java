package cl.ejercicios.evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cl.ejercicios.evaluacion.Modelo.ExposSaveDatabaseHelper;
import cl.ejercicios.evaluacion.Modelo.Exposicion;
import cl.ejercicios.evaluacion.Modelo.ListaExpos;

public class ExhibicionActivity extends ListActivity {

    private ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cargarExpos();
    }

    public void cargarExpos(){
        ExposSaveDatabaseHelper helper=new ExposSaveDatabaseHelper(this);
        lista=getListView();
        List<Exposicion> expoList= helper.listaExpos();
        ArrayAdapter<Exposicion> listaAdapter=
                new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1,
                        expoList);
        lista.setAdapter(listaAdapter);
    }

    @Override
    protected void onListItemClick(ListView listView, View item, int posicion, long id) {
        ExposSaveDatabaseHelper helper=new ExposSaveDatabaseHelper(this);
        Object o=lista.getItemAtPosition(posicion);
        String linea=o.toString();

        String[] separar=linea.split(":");

        Intent intent=new Intent(ExhibicionActivity.this, ExposicionElegida.class);
        intent.putExtra("nombreExpo", separar[0]);
        startActivityForResult(intent,1);
    }
}