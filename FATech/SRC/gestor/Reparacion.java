package gestor;

import java.util.Date;

public class Reparacion {

    private String cliente;
    private String equipo;
    private String falla;
    private String estado;
    private Date fecha;

    public Reparacion(String cliente, String equipo, String falla, String estado, Date fecha) {
        this.cliente = cliente;
        this.equipo = equipo;
        this.falla = falla;
        this.estado = estado;
        this.fecha = fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getFalla() {
        return falla;
    }

    public void setFalla(String falla) {
        this.falla = falla;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
