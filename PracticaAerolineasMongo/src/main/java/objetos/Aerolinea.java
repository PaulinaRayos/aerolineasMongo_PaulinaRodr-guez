/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetos;

import org.bson.types.ObjectId;

/**
 *
 * @author pauli
 */
public class Aerolinea {

    private ObjectId id;
    private String name;
    private String country;
    private String currency;
    private boolean lowcost;

    public Aerolinea() {
    }

    public Aerolinea(ObjectId id, String country, String currency, String name, boolean lowcost) {
        this.id = id;
        this.country = country;
        this.currency = currency;
        this.name = name;
        this.lowcost = lowcost;
    }

    public Aerolinea(String country, String currency, String name, boolean lowcost) {
        this.country = country;
        this.currency = currency;
        this.name = name;
        this.lowcost = lowcost;
    }

    public Aerolinea(ObjectId id) {
        this.id = id;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLowcost() {
        return lowcost;
    }

    public void setLowcost(boolean lowcost) {
        this.lowcost = lowcost;
    }

    @Override
    public String toString() {
        return "Aerolinea{" + "id=" + id + ", country=" + country + ", currency=" + currency + ", name=" + name + ", lowcost=" + lowcost + '}';
    }

}
