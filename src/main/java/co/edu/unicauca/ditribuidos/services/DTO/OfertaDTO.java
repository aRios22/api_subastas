package co.edu.unicauca.ditribuidos.services.DTO;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class OfertaDTO {
    
    @NotNull
    private Integer valor;

    public OfertaDTO() {
    }

    
}
