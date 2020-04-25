/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listase.modelo;

import com.listase.excepciones.InfanteExcepcion;
import com.listase.excepciones.PilotoExcepcion1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author camil
 */
public class ListaPiloto implements Serializable {

    private NodoPiloto cabeza;

    public ListaPiloto() {
    }

    public NodoPiloto getCabeza() {
        return cabeza;
    }

    public void setCabeza(NodoPiloto cabeza) {
        this.cabeza = cabeza;
    }

    public void adicionarNodo(Piloto piloto) {
        if (cabeza == null) {
            cabeza = new NodoPiloto(piloto);
        } else {
            //Lamo a mi ayudante
            NodoPiloto temp = cabeza;
            while (temp.getSiguiente() != null) //Mientras que en siguiente exista algo
            {
                temp = temp.getSiguiente();
            }
            //temp va estar ubicado en el ultimo nodo
            temp.setSiguiente(new NodoPiloto(piloto));
            temp.getSiguiente().setAnterior(temp);

        }
    }

    public void adicionarNodoAlInicio(Piloto piloto) {
        if (cabeza == null) {
            cabeza = new NodoPiloto(piloto);
        } else {
            NodoPiloto temp = new NodoPiloto(piloto);
            temp.setSiguiente(cabeza);
            cabeza.setAnterior(temp);
            cabeza = temp;
        }
    }
    public void adelantarPiloto(Piloto piloto) {
        if (cabeza.getSiguiente() != null) {
            cabeza = cabeza.getSiguiente();
            piloto = cabeza.getDato();
        }
        
    }
    
    public void PerderPosicionPiloto(Piloto piloto) {
        if (cabeza.getAnterior() != null) {
            cabeza = cabeza.getAnterior();
            piloto = cabeza.getDato();
        }
    }  
    
    

    public short contarNodos() {
        if (cabeza == null) {
            return 0;
        } else {
            //llamar a mi ayudante
            NodoPiloto temp = cabeza;
            short cont = 1;
            while (temp.getSiguiente() != null) {
                temp = temp.getSiguiente();
                cont++;
            }
            return cont;
        }
    }

    public String obtenerListadoPiloto() {

        //Un método recursivo que recoora mis infantes y que sacando la
        // info la adicione een el string
        return listarPiloto("");
    }

    public String listarPiloto(String listado) {
        if (cabeza != null) {
            NodoPiloto temp = cabeza;
            while (temp != null) {
                listado += temp.getDato() + "\n";
                temp = temp.getSiguiente();

            }
            return listado;
        }
        return "No hay Piloto";
    }

    public List obtenerListaPiloto() {
        List<Piloto> listado = new ArrayList<>();
        //Un método recursivo que recoora mis infantes y que sacando la
        // info la adicione een el string
        listarPiloto(listado);
        return listado;
    }

    public void listarPiloto(List listado) {
        if (cabeza != null) {
            NodoPiloto temp = cabeza;
            while (temp != null) {
                //listado += temp.getDato()+"\n";
                listado.add(temp.getDato());
                temp = temp.getSiguiente();
            }
        }

    }

    public void invertirLista() {
        if (cabeza != null) {
            //Crear una lista temporal la cabeza de la lista temporal está vacía
            ListaPiloto listaTemporal = new ListaPiloto();
            // Llamo un ayudante
            NodoPiloto temp = cabeza;
            //Recorro la lista de principio a fin
            while (temp != null) {
                //Parado en cada nodo , se extrae la información y se
                // envía a la otra lista al inicio
                listaTemporal.adicionarNodoAlInicio(temp.getDato());
                temp = temp.getSiguiente();
            }
            //Igualo la cabeza de mi lista principal a la cabeza de la lista temporal
            cabeza = listaTemporal.getCabeza();
        }
    }

    public void caidaPiloto(short codigo) throws PilotoExcepcion1 {
        if (cabeza != null) {
            if (cabeza.getDato().getCodigo() == codigo) {
                cabeza = cabeza.getSiguiente();
                cabeza.setAnterior(null);
                return;
            } else {
                NodoPiloto temp = cabeza;
                while (temp.getSiguiente() != null) {
                    if (temp.getSiguiente().getDato().getCodigo() == codigo) {
                        //el que sigue es el que hay que eliminar
                        temp.setSiguiente(temp.getSiguiente().getSiguiente());
                        if (temp.getSiguiente() != null) {
                            temp.getSiguiente().setAnterior(temp);
                        }
                        return;
                    }
                    temp = temp.getSiguiente();
                }

                throw new PilotoExcepcion1("El código " + codigo + " no existe en la lista");
            }
        }
        throw new PilotoExcepcion1("La lista de Piloto está vacía");
    }

    public Piloto obtenerPiloto(short codigo) throws PilotoExcepcion1 {
        if (cabeza != null) {
            if (cabeza.getDato().getCodigo() == codigo) {
                return cabeza.getDato();
            } else {
                NodoPiloto temp = cabeza;
                while (temp != null) {
                    if (temp.getDato().getCodigo() == codigo) {
                        return temp.getDato();
                    }
                    temp = temp.getSiguiente();
                }

                throw new PilotoExcepcion1("El código " + codigo + " no existe en la lista");
            }
        }
        throw new PilotoExcepcion1("La lista de piloto está vacía");
    }

//    public void caidaPilotoGrafico(short codigo) throws PilotoExcepcion1 {
//        if (cabeza != null) {
//            if (cabeza.getDato().getCodigo() == codigo) {
//                cabeza = cabeza.getSiguiente();
//
//                return;
//            } else {
//                NodoPiloto temp = cabeza;
//                while (temp.getSiguiente() != null) {
//                    if (temp.getDato().getCodigo()==codigo) {
//                        //el que sigue es el que hay que eliminar
//                        temp.setSiguiente(temp.getSiguiente().getSiguiente());
//                        if (temp.getSiguiente() != null) {
//                            temp.getSiguiente();
//                        }
//                        return;
//                    }
//                    temp = temp.getSiguiente();
//                }
//
//            }
//        }
//        throw new PilotoExcepcion1("La lista de piloto está vacía");
//    }
    


//    public void eliminarPiloto(short codigo) {
//        if (cabeza != null) {
//            NodoPiloto aux = cabeza;
//            NodoPiloto ant = null;
//            while (aux != null) {
//                if (aux.getDato().getCodigo() == codigo) {
//                    if (ant == null) {
//                        cabeza = cabeza.getSiguiente();
//                        aux.setSiguiente(null);
//                        aux = cabeza;
//
//                    } else {
//                        ant.setSiguiente(aux.getSiguiente());
//                        aux.setSiguiente(null);
//                        aux = ant.getSiguiente();
//                    }
//                } else {
//                    ant = aux;
//                    aux = aux.getSiguiente();
//                }
//            }
//        }
//
//    }

}
