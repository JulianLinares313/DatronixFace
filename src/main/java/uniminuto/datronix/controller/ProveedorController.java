package uniminuto.datronix.controller;

import uniminuto.datronix.entity.Proveedor;
import uniminuto.datronix.service.ProveedorService;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
@CrossOrigin(origins = "*")
public class ProveedorController {

    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @GetMapping
    public List<Proveedor> listarProveedores() {
        return proveedorService.listarProveedores();
    }

    @GetMapping("/{id}")
    public Proveedor buscarProveedorPorId(@PathVariable Long id) {
        return proveedorService.buscarProveedorPorId(id);
    }

    @PostMapping
    public Proveedor guardarProveedor(@RequestBody Proveedor proveedor) {
        return proveedorService.guardarProveedor(proveedor);
    }

    @PutMapping("/{id}")
    public Proveedor actualizarProveedor(@PathVariable Long id, @RequestBody Proveedor proveedor) {
        return proveedorService.actualizarProveedor(id, proveedor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProveedor(@PathVariable Long id) {

        try {
            proveedorService.eliminarProveedor(id);
            return ResponseEntity.ok().build(); // esto da una respuesta http 200 el cual nos confirma que no hubo
                                                // problemas al eliminar este Proveedor
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("No se puede eliminar el proveedor porque tiene compras asociadas. Elimina primero las compras o desactiva el proveedor.");

        }

    }
}