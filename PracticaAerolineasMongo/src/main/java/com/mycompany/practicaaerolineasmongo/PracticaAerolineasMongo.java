/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.practicaaerolineasmongo;

import datos.Aerolineas;
import datos.DAOAerolineas;
import java.util.ArrayList;
import objetos.Aerolinea;
import org.bson.types.ObjectId;

/**
 *
 * @author pauli
 */
public class PracticaAerolineasMongo {

    public static void main(String[] args) {
        Aerolineas aerolineas = new Aerolineas();
        ArrayList<Aerolinea> lista = aerolineas.getAllAirlines();
        Aerolinea insertar = new Aerolinea(new ObjectId(),"MXN","MXXN","Volaris",true);
        
        Aerolinea actualizar = new Aerolinea(new ObjectId("67311e7aba521d2f1561284b"),"USA","USD","American Airlines", false);
        
        Aerolinea eliminar = new Aerolinea(new ObjectId("67311e007ac23173b88f0b1a"));

        
        DAOAerolineas dao = new DAOAerolineas();
        
        Aerolinea busqueda = dao.consultar(actualizar);
        
        //dao.eliminar(eliminar);

        //dao.agregar(insertar);
        //dao.actualizar(actualizar);
        
        
        
        

        
        
        
        
        /*Aerolineas aerolineasDAO = new Aerolineas();
        
        // Inserta una aerolínea
        Aerolinea a = new Aerolinea(new ObjectId(), "CAN", "CAD", "Air Canada", false);
        aerolineasDAO.insertAirline(a);
        
        // Verifica las colecciones en la base de datos
        aerolineasDAO.checkDatabase();*/
    
    }
}
