/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.listase.controlador;

import com.listase.excepciones.PilotoExcepcion1;
import com.listase.modelo.ListaPiloto;
import com.listase.modelo.NodoPiloto;
import com.listase.modelo.Piloto;
import com.listase.utilidades.JsfUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.StateMachineConnector;
import org.primefaces.model.diagram.endpoint.BlankEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;
import org.primefaces.model.diagram.overlay.ArrowOverlay;
import org.primefaces.model.diagram.overlay.LabelOverlay;

/**
 *
 * @author camil
 */
@Named(value = "sesionPiloto")
@SessionScoped
public class SesionPiloto implements Serializable {

    private ListaPiloto listaPiloto;
    private Piloto piloto;
    private String alInicio = "1";
    private boolean deshabilitarFormulario = true;
    private NodoPiloto ayudante;
    private String textoVista = "Gráfico";

    private List listadoPiloto;

    private DefaultDiagramModel model;

    private short codigoCaida;

    private ControladorPais controlPaises;

    private short pilotoSeleccionado;
    private Piloto pilotoDiagrama;

    /**
     * Creates a new instance of SesionInfante
     */
    public SesionPiloto() {
    }

    @PostConstruct
    private void inicializar() {
        //inicializando el combo en el primer depto
        listaPiloto = new ListaPiloto();
        //LLenado de la bds
        listaPiloto.adicionarNodo(new Piloto("Carlos", (short) 1, (byte) 4, (byte) 20, "Colombia", "Yamaha"));
        listaPiloto.adicionarNodo(new Piloto("Pedro", (short) 2, (byte) 2, (byte) 23, "España" , "Suzuki"));
        listaPiloto.adicionarNodo(new Piloto("Juan", (short) 3, (byte) 1, (byte) 24, "Francia" , "Ninja"));
        listaPiloto.adicionarNodo(new Piloto("Diego", (short) 4, (byte) 3, (byte) 27, "Mexico" , "Ducati"));
        ayudante = listaPiloto.getCabeza();
        piloto = ayudante.getDato();
        //Me llena el objeto List para la tabla
        listadoPiloto = listaPiloto.obtenerListaPiloto();
        pintarLista();
    }

    public short getPilotoSeleccionado() {
        return pilotoSeleccionado;
    }

    public void setPilotoSeleccionado(short pilotoSeleccionado) {
        this.pilotoSeleccionado = pilotoSeleccionado;
    }

    public Piloto getPilotoDiagrama() {
        return pilotoDiagrama;
    }

    public void setPilotoDiagrama(Piloto pilotoDiagrama) {
        this.pilotoDiagrama = pilotoDiagrama;
    }

    public DiagramModel getModel() {
        return model;
    }

    private Connection createConnection(EndPoint from, EndPoint to, String label) {
        Connection conn = new Connection(from, to);
        conn.getOverlays().add(new ArrowOverlay(20, 20, 1, 1));

        if (label != null) {
            conn.getOverlays().add(new LabelOverlay(label, "flow-label", 0.5));
        }

        return conn;
    }

    public short getCodigoCaida() {
        return codigoCaida;
    }

    public void setCodigoCaida(short codigoCaida) {
        this.codigoCaida = codigoCaida;
    }

    public String getTextoVista() {
        return textoVista;
    }

    public void setTextoVista(String textoVista) {
        this.textoVista = textoVista;
    }

    public List getListadoPiloto() {
        return listadoPiloto;
    }

    public void setListadoPiloto(List listadoPiloto) {
        this.listadoPiloto = listadoPiloto;
    }

    public boolean isDeshabilitarFormulario() {
        return deshabilitarFormulario;
    }

    public void setDeshabilitarFormulario(boolean deshabilitarFormulario) {
        this.deshabilitarFormulario = deshabilitarFormulario;
    }

    public String getAlInicio() {
        return alInicio;
    }

    public void setAlInicio(String alInicio) {
        this.alInicio = alInicio;
    }

    public ListaPiloto getListaPiloto() {
        return listaPiloto;
    }

    public void setListaPiloto(ListaPiloto listaPiloto) {
        this.listaPiloto = listaPiloto;
    }

    public Piloto getPiloto() {
        return piloto;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    public void guardarPiloto() {
        //obtiene el consecutivo
        piloto.setCodigo((short) (listaPiloto.contarNodos() + 1));
        if (alInicio.compareTo("1") == 0) {
            listaPiloto.adicionarNodoAlInicio(piloto);
        } else {
            listaPiloto.adicionarNodo(piloto);
        }
        //Vuelvo a llenar la lista para la tabla
        listadoPiloto = listaPiloto.obtenerListaPiloto();
        pintarLista();
        deshabilitarFormulario = true;
        JsfUtil.addSuccessMessage("El Piloto se ha guardado exitosamente");

    }

    public void habilitarFormulario() {
        deshabilitarFormulario = false;
        piloto = new Piloto();
    }

    public void irAnterior() {
        if (ayudante.getAnterior() != null) {
            ayudante = ayudante.getAnterior();
            piloto = ayudante.getDato();
        }
    }

    public void irSiguiente() {
        if (ayudante.getSiguiente() != null) {
            ayudante = ayudante.getSiguiente();
            piloto = ayudante.getDato();
        }
    }

    public void irPrimero() {
        if (listaPiloto.getCabeza() != null) {
            ayudante = listaPiloto.getCabeza();
            piloto = ayudante.getDato();

        } else {
            piloto = new Piloto();
        }
        listadoPiloto = listaPiloto.obtenerListaPiloto();
        pintarLista();

    }

    public void irUltimo() {
        if (listaPiloto.getCabeza() != null) {
            while (ayudante.getSiguiente() != null) {
                ayudante = ayudante.getSiguiente();
            }
            piloto = ayudante.getDato();
        }
    }

    public void cambiarVistaPiloto() {
        if (textoVista.compareTo("Tabla") == 0) {
            textoVista = "Gráfico";
        } else {
            textoVista = "Tabla";
        }
    }

    public void invertirLista() {
        //Invierte la lista
        listaPiloto.invertirLista();
        irPrimero();
    }

    public void pintarLista() {
        //Instancia el modelo
        model = new DefaultDiagramModel();
        //Se establece para que el diagrama pueda tener infinitas flechas
        model.setMaxConnections(-1);

        StateMachineConnector connector = new StateMachineConnector();
        connector.setOrientation(StateMachineConnector.Orientation.ANTICLOCKWISE);
        connector.setPaintStyle("{strokeStyle:'#7D7463',lineWidth:3}");
        model.setDefaultConnector(connector);

        ///Adicionar los elementos
        if (listaPiloto.getCabeza() != null) {
            //llamo a mi ayudante
            NodoPiloto temp = listaPiloto.getCabeza();
            int posX = 2;
            int posY = 2;
            //recorro la lista de principio a fin
            while (temp != null) {
                //Parado en un elemento
                //Crea el cuadrito y lo adiciona al modelo
                Element ele = new Element(temp.getDato().getCodigo() + " "
                        + temp.getDato().getNombre(),
                        posX + "em", posY + "em");
                ele.setId(String.valueOf(temp.getDato().getCodigo()));
                //adiciona un conector al cuadrito
                ele.addEndPoint(new BlankEndPoint(EndPointAnchor.TOP));
                ele.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM_RIGHT));

                ele.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM_LEFT));
                ele.addEndPoint(new BlankEndPoint(EndPointAnchor.BOTTOM));
                model.addElement(ele);
                temp = temp.getSiguiente();
                posX = posX + 5;
                posY = posY + 6;
            }

            //Pinta las flechas            
            for (int i = 0; i < model.getElements().size() - 1; i++) {
                model.connect(createConnection(model.getElements().get(i).getEndPoints().get(1),
                        model.getElements().get(i + 1).getEndPoints().get(0), "Sig"));

                model.connect(createConnection(model.getElements().get(i + 1).getEndPoints().get(2),
                        model.getElements().get(i).getEndPoints().get(3), "Ant"));
            }

        }
    }

    public void onClickRight() {
        String id = FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("elementId");

        pilotoSeleccionado = Short.valueOf(id.replaceAll("frmPiloto:diagrama-", ""));

    }

    public void caidaPiloto() {
        if (codigoCaida > 0) {
            //llamo el eliminar de la lista
            try {
                listaPiloto.caidaPiloto(codigoCaida);
                irPrimero();
                JsfUtil.addSuccessMessage("Piloto " + codigoCaida + " se ha caido.");
            } catch (PilotoExcepcion1 e) {
                JsfUtil.addErrorMessage(e.getMessage());
            }
        } else {
            JsfUtil.addErrorMessage("El código a eliminar " + codigoCaida + " no es válido");
        }
    }

    public void obtenerPilotoDiagrama() {
        try {
            pilotoDiagrama = listaPiloto.obtenerPiloto(pilotoSeleccionado);
        } catch (PilotoExcepcion1 ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }

    public void enviarAlFinal() {
        try {
            Piloto pilTemporal = listaPiloto.obtenerPiloto(pilotoSeleccionado);
            listaPiloto.caidaPiloto(pilotoSeleccionado);
            listaPiloto.adicionarNodo(pilTemporal);
            pintarLista();
        } catch (PilotoExcepcion1 ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }

    public void enviarAlInicio() {
        try {
            Piloto pilTemporal = listaPiloto.obtenerPiloto(pilotoSeleccionado);
            listaPiloto.caidaPiloto(pilotoSeleccionado);
            listaPiloto.adicionarNodoAlInicio(pilTemporal);
            pintarLista();
        } catch (PilotoExcepcion1 ex) {
            JsfUtil.addErrorMessage(ex.getMessage());

        }
    }
     public void PerderPosicion() {
        try {
            Piloto pilTemporal = listaPiloto.obtenerPiloto(pilotoSeleccionado);
            listaPiloto.PerderPosicionPiloto(pilTemporal);
            pintarLista();
        } catch (PilotoExcepcion1 ex) {
            JsfUtil.addErrorMessage(ex.getMessage());

        }
        
    }
    
    public void AdelantarPosicion() {
        try {
            Piloto pilTemporal = listaPiloto.obtenerPiloto(pilotoSeleccionado);
            listaPiloto.adelantarPiloto(pilTemporal);
            pintarLista();
        } catch (PilotoExcepcion1 ex) {
            JsfUtil.addErrorMessage(ex.getMessage());

        }
        
    }

    //Eliminar infante en el grafico flata completar
    public void caidaPilotoGraficoSesion() {
        try {
            Piloto pilTemporal = listaPiloto.obtenerPiloto(pilotoSeleccionado);
            listaPiloto.caidaPiloto(pilotoSeleccionado);
            pintarLista();
        } catch (PilotoExcepcion1 ex) {
            JsfUtil.addErrorMessage(ex.getMessage());

        }
    }

}
