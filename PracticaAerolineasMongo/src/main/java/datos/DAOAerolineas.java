/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import interfaces.IAerolineasDAO;
import java.util.List;
import objetos.Aerolinea;

/**
 *
 * @author pauli
 */
public class DAOAerolineas implements IAerolineasDAO {

    private MongoCollection getCollection() {
        ConexionBD conexion = new ConexionBD();
        MongoDatabase database = conexion.crearConexion();
        MongoCollection collection = database.getCollection("airlines", Aerolinea.class);
        return collection;
    }

    public boolean agregar(Aerolinea a) {
        try {
            this.getCollection().insertOne(a);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizar(Aerolinea a) {
        try {
            UpdateResult result = this.getCollection().updateOne(eq("_id", a.getId())
            ,combine(
                    set("name", a.getName()),
                     set("country", a.getCountry()),
                     set("currency", a.getCurrency()),
                     set("lowcost", a.isLowcost())));
            return result.getModifiedCount()==1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }

    @Override
    public boolean eliminar(Aerolinea a) {
        try{
            DeleteResult result = this.getCollection().deleteOne(eq("_id", a.getId()));
            return result.getDeletedCount() == 1;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Aerolinea consultar(Aerolinea a) {
        try{
            Aerolinea result = (Aerolinea) this.getCollection().find(eq("_id", a.getId())).first();
            return result;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Aerolinea> consultarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
