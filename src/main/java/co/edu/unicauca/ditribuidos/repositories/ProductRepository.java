package co.edu.unicauca.ditribuidos.repositories;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import co.edu.unicauca.ditribuidos.models.Productos;

@Repository
public class ProductRepository {
    
    ArrayList<Productos> listaProductos;

    public ProductRepository() {
        
        this.listaProductos=new ArrayList<Productos>();
        cargarProductos();

    }

    public ArrayList<Productos> findAll() {
        return this.listaProductos;
    }

    public ArrayList<Productos> listarActivos(){
        ArrayList<Productos> result=new ArrayList<Productos>();
        for (Productos savedProducto : this.listaProductos) {
            if (savedProducto.getEstaAbierta()==true) {
                result.add(savedProducto);
            }
        }
        return result;
    }

    public ArrayList<Productos> listarInactivos(){
        ArrayList<Productos> result=new ArrayList<Productos>();
        for (Productos savedProducto : this.listaProductos) {
            if (savedProducto.getEstaAbierta()==false) {
                result.add(savedProducto);
            }
        }
        return result;
    }

    public Productos save(Productos Producto){
        Productos objProducto=null;

        for (Productos producto : this.listaProductos) {
            if(producto.getId()==Producto.getId()){
                return objProducto;
            }
        }
        
        if(this.listaProductos.add(Producto)){
            objProducto=Producto;
        }
        return objProducto;
    }

    public Productos findById(Integer id)
   {
        Productos objCliente=null;
		
		for (Productos producto : this.listaProductos) {
			if(producto.getId()==id)
			{
				objCliente=producto;
				break;
			}
		}
		
		return objCliente;
	}

    public Boolean deactivate(int id){
        Boolean result=false;
        for (Productos productoSaved : this.listaProductos) {
            if (id==productoSaved.getId()) {
                productoSaved.setEstaAbierta(false);
                result=true;
                return result;
            }
        }
        return result;
    }

    public Boolean activate(int id){
        Boolean result=false;
        for (Productos productoSaved : this.listaProductos) {
            if (id==productoSaved.getId()) {
                productoSaved.setEstaAbierta(true);
                result=true;
                return result;
            }
        }
        return result;
    }

    public Productos ofertar(int id, int valor){
        Productos result=null;
        for (Productos savedProductos : this.listaProductos) {
            if (id==savedProductos.getId() && savedProductos.getEstaAbierta()==true) {
                if(valor>savedProductos.getValorActual()){
                    savedProductos.setValorActual(valor);
                    return savedProductos;
                }
            }
        }
        return result;
    }

    private void cargarProductos(){

        Productos objProducto1=new Productos(1, "Camara analoga", 200);
        this.listaProductos.add(objProducto1);

        Productos objProducto2=new Productos(2, "Portatil lenovo", 500);
        this.listaProductos.add(objProducto2);

        Productos objProducto3=new Productos(3, "Mouse toshiba", 200);
        this.listaProductos.add(objProducto3);
    }

}
