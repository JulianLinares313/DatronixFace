package uniminuto.datronix.controller;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uniminuto.datronix.entity.Producto;
import uniminuto.datronix.service.ProductoService;

@RestController //le indica  sprint boot que esta clase es la comtroladora de producto
@RequestMapping("/api/productos") 
@CrossOrigin(origins = "*") //crea la comunicion entre nuetro bakend y frontend recibiendo peticiones http y metodos con el objetivo de cacturar esa informacion y procesar los datos
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {

        this.productoService = productoService;

    }

    @GetMapping
    public List<Producto> listarProducto() {

        return productoService.listarProductos();

    }

    @GetMapping("/{id}")
    public Producto BuscarProductoPorId(@PathVariable Long id) {

        return productoService.buscarProductoPorId(id);

    }

    @PostMapping
    public Producto guardarProducto(@RequestBody Producto producto) {

        return productoService.guardarProducto(producto);

    }


    // el ResponseEntity nos permite devolver una peticion http personalizada
    @DeleteMapping("/{id}") //cactura la peticion enviada por el js, debe ciumplir la peticion http y el metodo para acturarlo en este caso el metodo delete
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
        try { 
            productoService.eliminarProducto(id);
            return ResponseEntity.ok().build(); //retorna un mensaje http 200 si todo sale bien

        } catch (DataIntegrityViolationException e) {  // el DataIntegrityViolationException cactura el error lanzado por el Jpa
            return ResponseEntity.status(HttpStatus.CONFLICT) //restorna como respuesta un 409 y muestra un mensaje de advertencia
                    .body("No se puede eliminar el proveedor porque tiene compras asociadas. Elimina primero las compras o desactiva el proveedor.");//retornamos el mensaje 
        }

    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        return productoService.actualizarProducto(id, producto);

    }

}
