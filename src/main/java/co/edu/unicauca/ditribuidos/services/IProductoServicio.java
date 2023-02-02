package co.edu.unicauca.ditribuidos.services;

import java.util.ArrayList;

import co.edu.unicauca.ditribuidos.models.Productos;
import co.edu.unicauca.ditribuidos.services.DTO.OfertaDTO;
import co.edu.unicauca.ditribuidos.services.DTO.ProductoDTO;

public interface IProductoServicio {
    public ProductoDTO register(ProductoDTO producto);
    public ArrayList<Productos> listarTodos();

    public Boolean activarSubasta(int id);//TODO: entradas
    public Boolean desactivarSubasta(int id);//TODO: entradas

    public ArrayList<Productos> listarActivos();//TODO: asegurarme de el tipo de return
    public ArrayList<Productos> listarInactivos();//TODO: asegurarme de el tipo de return

    public ProductoDTO encontrarPorId(int id);

    public ProductoDTO ofrecerOferta(int id, OfertaDTO valor);
    public Boolean esActivo(int id);

}
