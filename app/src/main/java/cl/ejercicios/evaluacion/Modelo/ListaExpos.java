package cl.ejercicios.evaluacion.Modelo;

import java.util.ArrayList;

public class ListaExpos {
    private static ListaExpos instancia=new ListaExpos();
    private ArrayList<Exposicion> listaExpos;

    private ListaExpos(){
        listaExpos=new ArrayList<>();
        agregarExpo(new Exposicion(0, "El Origen","Donde todo comenzó...",0));
        agregarExpo(new Exposicion(1,"Zona Desertica","Las zonas desérticas se caracterizan por la escasez de lluvias. Son secas y con amplia variación térmica.",0));
    }
    public static ListaExpos getInstancia(){
        return instancia;
    }
    public void agregarExpo(Exposicion exhibicion){
        listaExpos.add(exhibicion);
    }
    public Exposicion getExposicion(int id) {
        return listaExpos.get(id);
    }
    public ArrayList<Exposicion> getListaExpos(){
        return listaExpos;
    }

    public void eliminarExpos(int id){
        listaExpos.remove(id);
    }
}
