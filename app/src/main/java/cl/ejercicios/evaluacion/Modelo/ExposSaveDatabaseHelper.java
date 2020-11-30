package cl.ejercicios.evaluacion.Modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ExposSaveDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="expos.db";
    private static final int DB_VERSION=1;

    public ExposSaveDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="CREATE TABLE EXPOS("
                +"_id INTEGER PRIMARY KEY, "
                +"NOMBRE TEXT, DESCRIPCION TEXT, "
                +"ESTADO INTEGER);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void agregar(Exposicion expo){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues valores=new ContentValues();
        valores.put("_id", expo.id);
        valores.put("NOMBRE",expo.nombre);
        valores.put("DESCRIPCION",expo.descripcion);
        valores.put("ESTADO", expo.estado);

        db.insert("EXPOS", null, valores);
    }
    public List<Exposicion> listaExpos(){
        SQLiteDatabase db=getReadableDatabase();
        List<Exposicion> expos=new ArrayList<>();
        Cursor cursor=db.query("EXPOS",
                new String[]{"_id","NOMBRE","DESCRIPCION","ESTADO"},
                null,null,null,null,null);
        cursor.moveToFirst();
        do {
            expos.add(new Exposicion(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3)));
        } while (cursor.moveToNext());
        cursor.close();
        db.close();
        return expos;
    }
    public Exposicion getExpo(String nombre){
        Exposicion e;
        SQLiteDatabase db=getReadableDatabase();
        String sqlTxt="SELECT _id, NOMBRE, DESCRIPCION, ESTADO "
                +"FROM EXPOS WHERE NOMBRE=? ";
        String[] argumentos=new String[]{nombre};
        try{
            Cursor cursor=db.rawQuery(sqlTxt,argumentos);
            cursor.moveToFirst();
            e=new Exposicion(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3));
        } catch (SQLException ex){
            e=null;
        }
        return e;
    }
    public String updateExpo(Exposicion expo){
        int id = expo.id;
        String nombre = expo.nombre;
        String descripcion = expo.descripcion;
        int estado = expo.estado;
        String sql="UPDATE EXPOS SET NOMBRE=?, DESCRIPCION=?, "
                +"ESTADO=? WHERE _id=?";
        Object[] argumentos=new Object[]{expo.nombre,expo.descripcion,expo.estado,expo.id};
        try {
            getWritableDatabase().execSQL(sql,argumentos);
            return "";
        }catch (SQLException ex){
            return "No se pudo actualizar";
        }
    }
    public String limpiarExpos(int id){
        String sql="DELETE FROM EXPOS WHERE _id=? ";
        Object[] argumentos=new Object[]{id};
        try{
            getWritableDatabase().execSQL(sql,argumentos);
            return "Exposiciones eliminadas";
        }catch (SQLException ex){
            return "No se pudo realizar la acción";
        }
    }
}
