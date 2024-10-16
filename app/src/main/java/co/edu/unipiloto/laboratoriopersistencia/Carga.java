package co.edu.unipiloto.laboratoriopersistencia;

public class Carga {
    private int id;
    private String origen;
    private String destino;
    private double peso;
    private String descripcion;
    
    public Carga(String origen, String destino, double peso, String descripcion) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
        this.descripcion = descripcion;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getOrigen() { return origen; }
    public void setOrigen(String origen) { this.origen = origen; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }

    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
