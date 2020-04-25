/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listase.modelo;

import java.io.Serializable;

/**
 *
 * @author camil
 */
public class Piloto implements  Serializable {
    private String nombre; //null
    private short codigo;
    private byte añosExP; //0
    private byte edad; //0
    private String Nacionalidad;
    private String Moto;
    
    public Piloto() {
        this.edad=18;
       
    }    

    public Piloto(String nombre,short codigo, byte añosExP, byte edad, String Nacionalidad, String Moto ) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.añosExP = añosExP;
        this.edad = edad;
        this.Nacionalidad = Nacionalidad;
        this.codigo = codigo;
        this.Moto= Moto;
    }

    public String getMoto() {
        return Moto;
    }

    public void setMoto(String Moto) {
        this.Moto = Moto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public short getCodigo() {
        return codigo;
    }

    public void setCodigo(short codigo) {
        this.codigo = codigo;
    }

    public byte getAñosExP() {
        return añosExP;
    }

    public void setAñosExP(byte añosExP) {
        this.añosExP = añosExP;
    }

    public byte getEdad() {
        return edad;
    }

    public void setEdad(byte edad) {
        this.edad = edad;
    }

    public String getNacionalidad() {
        return Nacionalidad;
    }

    public void setNacionalidad(String Nacionalidad) {
        this.Nacionalidad = Nacionalidad;
    }
    
    @Override
    public String toString() {
       return this.nombre; 
    }
}
