/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listase.modelo;

/**
 *
 * @author carloaiza
 */
public class NodoPiloto {
    private Piloto dato;
    private NodoPiloto siguiente;
    private NodoPiloto anterior;

    public NodoPiloto(Piloto dato) {
        this.dato = dato;
    }

    public Piloto getDato() {
        return dato;
    }

    public void setDato(Piloto dato) {
        this.dato = dato;
    }

    public NodoPiloto getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoPiloto siguiente) {
        this.siguiente = siguiente;
    }

    public NodoPiloto getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoPiloto anterior) {
        this.anterior = anterior;
    }
    
    
    
}
