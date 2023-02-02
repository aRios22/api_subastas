package co.edu.unicauca.ditribuidos.services;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicauca.ditribuidos.models.Productos;
import co.edu.unicauca.ditribuidos.repositories.ProductRepository;
import co.edu.unicauca.ditribuidos.services.DTO.OfertaDTO;
import co.edu.unicauca.ditribuidos.services.DTO.ProductoDTO;

@Service
public class ProductoServicioImpl implements IProductoServicio{

    @Autowired
    private ProductRepository servicioAccesoBaseDatos;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductoDTO register(ProductoDTO producto) {
        Productos productEntity=this.modelMapper.map(producto, Productos.class);
        productEntity.setValorActual(productEntity.getValorInicial());
		Productos objProductEntity= this.servicioAccesoBaseDatos.save(productEntity);
        if (objProductEntity == null) {
            return null;
        }
		ProductoDTO productDTO=this.modelMapper.map(objProductEntity, ProductoDTO.class);
        
		return productDTO;	
    }

    @Override
    public ArrayList<Productos> listarTodos() {
        return this.servicioAccesoBaseDatos.findAll();
    }

    @Override
    public Boolean activarSubasta(int id) {
        Boolean objProductoEntity= this.servicioAccesoBaseDatos.activate(id);
        if (objProductoEntity == false) {
            return false;
        }
		return true;
    }

    @Override
    public Boolean desactivarSubasta(int id) {
        Boolean objProductoEntity= this.servicioAccesoBaseDatos.deactivate(id);
        if (objProductoEntity == false) {
            return false;
        }
		return true;
    }

    @Override
    public ArrayList<Productos> listarActivos() {
        return this.servicioAccesoBaseDatos.listarActivos();
    }

    @Override
    public ArrayList<Productos> listarInactivos() {
        return this.servicioAccesoBaseDatos.listarInactivos();
    }

    @Override
    public ProductoDTO encontrarPorId(int id) {
        Productos objProductoEntity= this.servicioAccesoBaseDatos.findById(id);
        if (objProductoEntity == null) {
            return null;
        }
		ProductoDTO productoDTO=this.modelMapper.map(objProductoEntity, ProductoDTO.class);
		return productoDTO;
    }

    @Override
    public ProductoDTO ofrecerOferta(int id, OfertaDTO valor) {
        Productos result = this.servicioAccesoBaseDatos.ofertar(id, valor.getValor());
        if (result==null) {
            return null;
        }
        ProductoDTO productDTO=this.modelMapper.map(result, ProductoDTO.class);
        return productDTO;
    }

    @Override
    public Boolean esActivo(int id) {
        Productos objProductoEntity= this.servicioAccesoBaseDatos.findById(id);
        if (objProductoEntity==null) {
            return false;
        }
        return objProductoEntity.getEstaAbierta();
    }
    
}
