/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listase.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author camilo
 */
public class ListaSE implements Serializable{
    private Nodo cabeza;

    public ListaSE() {
    }

    public Nodo getCabeza() {
        return cabeza;
    }

    public void setCabeza(Nodo cabeza) {
        this.cabeza = cabeza;
    }
    
    public void adicionarNodo(Infante infante)
    {
        if(cabeza ==null)
        {
            cabeza = new Nodo(infante);
        }
        else
        {
            //Lamo a mi ayudante
            Nodo temp= cabeza;
            while(temp.getSiguiente()!=null) //Mientras que en siguiente exista algo
            {
                temp= temp.getSiguiente();
            }
            //temp va estar ubicado en el ultimo nodo
            temp.setSiguiente(new Nodo(infante));
        }
        
    }
    
    public void adicionarNodoAlInicio(Infante infante)
    {
        if(cabeza ==null)
        {
            cabeza = new Nodo(infante);
        }
        else
        {
            Nodo temp= new Nodo(infante);
            temp.setSiguiente(cabeza);
            cabeza= temp;
        }
    }
    
    public short contarNodos()
    {
        if(cabeza ==null)
        {
            return 0;
        }
        else
        {
            //llamar a mi ayudante
            Nodo temp= cabeza;
            short cont=1;
            while(temp.getSiguiente()!=null)
            {
                temp=temp.getSiguiente();
                cont++;
            }
            return cont;
        }
    }
    
    public String obtenerListadoInfantes()
    {
        
        //Un método recursivo que recoora mis infantes y que sacando la
        // info la adicione een el string
        
        return listarInfantes("");
    }
    
    public String listarInfantes(String listado)
    {
        if(cabeza !=null)
        {
            Nodo temp= cabeza;            
            while(temp!=null)
            {
                listado += temp.getDato()+"\n";
                temp=temp.getSiguiente();
                
            }
            return listado;
        }
        return "No hay infantes";
    }
    
    
     public List obtenerListaInfantes()
    {
        List<Infante> listado = new ArrayList<>();
        //Un método recursivo que recoora mis infantes y que sacando la
        // info la adicione een el string
        listarInfantes(listado);
        return listado;
    }
    
    public void listarInfantes(List listado)
    {
        if(cabeza !=null)
        {
            Nodo temp= cabeza;            
            while(temp!=null)
            {
                //listado += temp.getDato()+"\n";
                listado.add(temp.getDato());
                temp=temp.getSiguiente();
                
            }            
        }
        
    }
    public ListaSE obtenerListaInvertida(){
        ListaSE contra = new ListaSE();
        Nodo temp = cabeza;

        while (temp.getSiguiente() != null){
            contra.adicionarNodoAlInicio(temp.getDato());
            temp = temp.getSiguiente();
            
        }
        contra.adicionarNodoAlInicio(temp.getDato());
          
        return contra;
    }
    
////    public double promedioInfantes() {
////        float suma = 0;
////        float conta=0;
////        Nodo temp = cabeza;
////        while (temp.getSiguiente() != null) {
////            conta++;
////            temp = temp.getSiguiente();
////            suma=suma+temp.getDato().getEdad();
////        }
////        suma=suma+temp.getDato().getEdad();
////        temp = temp.getSiguiente();
////        return (float) (suma/contarNodos());
//
//    }

    
}
