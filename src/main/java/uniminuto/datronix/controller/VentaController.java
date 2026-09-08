package uniminuto.datronix.controller;

import uniminuto.datronix.entity.Venta;
import uniminuto.datronix.service.VentaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = "*") // Permite peticiones desde cualquier origen (útil para desarrollo)
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    // ========== LISTAR TODAS LAS VENTAS ==========
    @GetMapping
    public List<Venta> listarVentas() {
        return ventaService.listarVentas();
    }

    // ========== BUSCAR VENTA POR ID ==========
    @GetMapping("/{id}")
    public Venta buscarVentaPorId(@PathVariable Long id) {
        return ventaService.buscarVentaPorId(id);
    }

    // ========== CREAR NUEVA VENTA ==========
    @PostMapping
    public Venta guardarVenta(@RequestBody Venta venta) {
        return ventaService.guardarVenta(venta);
    }

    // ========== ELIMINAR VENTA ==========
    @DeleteMapping("/{id}")
    public void eliminarVenta(@PathVariable Long id) {
        ventaService.eliminarVenta(id);
    }

    // ========== ACTUALIZAR VENTA ==========
    @PutMapping("/{id}")
    public Venta actualizarVenta(@PathVariable Long id, @RequestBody Venta venta) {
        // Si quieres implementar actualización, hazlo aquí
        // Por ahora devolvemos la misma venta (no implementado)
        return venta;
    }
}