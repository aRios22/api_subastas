package co.edu.unicauca.ditribuidos.services.DTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class ProductoDTO {
    
    @NotNull
    private Integer id;
    @NotNull
    @Size(min = 5, max = 50)
    private String nombre;

    @NotNull
    private Integer valorInicial;

    private Integer valorActual;
    private Boolean estaAbierta;
    

    public ProductoDTO() {
        this.estaAbierta=false;
    }

    
}
