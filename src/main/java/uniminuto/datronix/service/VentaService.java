package uniminuto.datronix.service;

import uniminuto.datronix.entity.Venta;
import uniminuto.datronix.repository.VentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;

    
    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    // ========== LISTAR TODAS LAS VENTAS ==========
    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    // ========== BUSCAR POR ID ==========
    public Venta buscarVentaPorId(Long id) {
        return ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + id));
    }

    // ========== GUARDAR VENTA (con sus detalles) ==========
    public Venta guardarVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    // ========== ELIMINAR VENTA ==========
    public void eliminarVenta(Long id) {
        if (!ventaRepository.existsById(id)) {
            throw new RuntimeException("Venta no encontrada con ID: " + id);
        }
        ventaRepository.deleteById(id);
    }
}