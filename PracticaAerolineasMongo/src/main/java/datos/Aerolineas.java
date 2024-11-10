/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;
import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.*;
import java.util.ArrayList;
import objetos.Aerolinea;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author pauli
 */
public class Aerolineas {

    MongoClient mongoClient;
    MongoDatabase database;
    MongoCollection<Document> collection;

    public Aerolineas() {
        mongoClient = MongoClients.create();
        database = mongoClient.getDatabase("airport");
        collection = database.getCollection("airlines");
    }

    private Document objectToDocument(Aerolinea a) {
        Document aerolinea = new Document()
                .append("name", a.getName())
                .append("country", a.getCountry())
                .append("currency", a.getCurrency())
                .append("lowcost", a.isLowcost());
        return aerolinea;
    }

    public ObjectId insertAirline(Aerolinea a) {
        try {
        ObjectId id = new ObjectId();  // Genera un nuevo ObjectId
        Document aerolinea = objectToDocument(a).append("_id", id); // Crea el documento
        collection.insertOne(aerolinea);  // Inserta el documento
        System.out.println("Inserted document with id: " + id);  // Confirmación
        return id;
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
    }

    public boolean updateAirline(Aerolinea a) {
        try {
            Bson idQuery = Filters.eq("_id", a.getId());
            Bson updates = Updates.combine(Updates.set("name", a.getName())
                    , Updates.set("country", a.getCountry())
                    , Updates.set("currency", a.getCurrency())
                    , Updates.set("lowcost", a.isLowcost()));//
            UpdateResult result = collection.updateMany(idQuery, updates);
            System.out.println(result.getModifiedCount());
            return result.getModifiedCount() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAirlineById(ObjectId id) {
        try {
            Bson idQuery = Filters.eq("_id", id);
            DeleteResult result = collection.deleteOne(idQuery);
            System.out.println(result.getDeletedCount());
            return result.getDeletedCount() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Aerolinea> getAllAirlines(){
        ArrayList<Aerolinea> aerolineas = new ArrayList();
        
        MongoCursor<Document> cursor = collection.find().iterator();
        try{
            while (cursor.hasNext()){
                Document d = cursor.next();

                Aerolinea a = new Aerolinea(
                d.getObjectId("_id"),
                d.getString("country"),
                d.getString("currency"),
                d.getString("name"),  
                d.getBoolean("lowcost")
            );
                System.out.println(a.toString());
                aerolineas.add(a);
            }
            return aerolineas;
        }finally{
            cursor.close();
        }
    }
    
        // Método para verificar las colecciones de la base de datos
    public void checkDatabase() {
        MongoIterable<String> collections = database.listCollectionNames();
        System.out.println("Colecciones en la base de datos 'airport':");
        for (String collectionName : collections) {
            System.out.println(" - " + collectionName);
        }
    }
}
