package co.edu.unicauca.ditribuidos.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.ditribuidos.services.IProductoServicio;
import co.edu.unicauca.ditribuidos.services.DTO.OfertaDTO;
import co.edu.unicauca.ditribuidos.services.DTO.ProductoDTO;
import co.edu.unicauca.ditribuidos.models.Productos;;

@RestController
@RequestMapping("/api/product")
public class ProductoRESTControler {
    
    @Autowired
    private IProductoServicio ProductoService;

    @GetMapping("/products")
    public ResponseEntity<List<Productos>> index() {
        return new ResponseEntity<List<Productos>>(ProductoService.listarTodos(), HttpStatus.OK);
    }

    @GetMapping("/products/active")
    public ResponseEntity activeProducts(){
        List<Productos> result = ProductoService.listarActivos();
        if(result.size()==0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay productos activos");
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);    
    }

    @GetMapping("/products/inactive")
    public ResponseEntity inactiveProducts(){
        List<Productos> result = ProductoService.listarInactivos();
        if(result.size()==0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay productos desactivados");
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);    
    }


    @GetMapping("/products/{id}")
    public ResponseEntity getById(@PathVariable int id) {
        ProductoDTO objProducto = null;		
		objProducto = ProductoService.encontrarPorId(id);	
        if (objProducto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el producto.");
        }	
		return ResponseEntity.status(HttpStatus.OK).body(objProducto);
    }

    @PostMapping("/register")
	public ResponseEntity create(@Valid @RequestBody  ProductoDTO producto, BindingResult result) {	
		ProductoDTO objProducto = null;
        if(result.hasErrors()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result.getFieldErrors()); 
        }
        objProducto =  ProductoService.register(producto);

		if (objProducto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(objProducto);
        } else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Ya existe un producto con este id.");
        }
        
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<String> deactivate(@PathVariable int id){
        Boolean objProducto = null;		
		objProducto = ProductoService.desactivarSubasta(id);	
        if (objProducto == false) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el producto.");
        }	
		return ResponseEntity.status(HttpStatus.OK).body("Se ha desactivado la Subasta.");
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<String> activate(@PathVariable int id){
        Boolean objProducto = null;		
		objProducto = ProductoService.activarSubasta(id);	
        if (objProducto == false) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el producto.");
        }	
		return ResponseEntity.status(HttpStatus.OK).body("Se ha activado la Subasta.");
    }

    @PutMapping("/offer/{id}")
    public ResponseEntity ponerOferta(@PathVariable int id, @Valid @RequestBody OfertaDTO oferta, BindingResult result){
        ProductoDTO objProducto = null;
        if(result.hasErrors()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result.getFieldErrors()); 
        }
        Boolean activo = ProductoService.esActivo(id);
        if(activo==false){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("El producto no esta activo como subasta o no existe.");
        }

        objProducto =  ProductoService.ofrecerOferta(id, oferta);
        if (objProducto == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("La oferta debe ser mayor al precio actual.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(objProducto);
    }
}
