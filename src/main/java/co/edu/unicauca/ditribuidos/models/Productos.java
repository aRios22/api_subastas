package co.edu.unicauca.ditribuidos.models;

public class Productos {
    //De los productos es necesario gestionar código, nombre y valor inicial de la oferta del producto.
    private Integer id;
    private String nombre;
    private Integer valorInicial;

    private Integer valorActual;
    private Boolean estaAbierta;


    public Productos() {
    }

    public Productos(Integer id, String nombre, Integer valorInicial) {
        this.id = id;
        this.nombre = nombre;
        this.valorInicial = valorInicial;

        this.valorActual=valorInicial;
        this.estaAbierta=false;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Integer getValorInicial() {
        return valorInicial;
    }
    public void setValorInicial(Integer valorInicial) {
        this.valorInicial = valorInicial;
    }
    public Integer getValorActual() {
        return valorActual;
    }
    public void setValorActual(Integer valorActual) {
        this.valorActual = valorActual;
    }
    public Boolean getEstaAbierta() {
        return estaAbierta;
    }
    public void setEstaAbierta(Boolean estaAbierta) {
        this.estaAbierta = estaAbierta;
    }

    
}
