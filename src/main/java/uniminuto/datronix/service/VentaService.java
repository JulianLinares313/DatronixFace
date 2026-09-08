package uniminuto.datronix.service;

import uniminuto.datronix.entity.Venta;
import uniminuto.datronix.repository.VentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// Gestiona las operaciones disponibles para ventas y sus detalles asociados.
public class VentaService {

    private final VentaRepository ventaRepository;

    
    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    // ========== LISTAR TODAS LAS VENTAS ==========
    public List<Venta> listarVentas() {
        // Devuelve todas las ventas encontradas en la base de datos.
        return ventaRepository.findAll();
    }

    // ========== BUSCAR POR ID ==========
    public Venta buscarVentaPorId(Long id) {
        // Una venta inexistente se convierte en un error entendible para quien la solicitó.
        return ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + id));
    }

    // ========== GUARDAR VENTA (con sus detalles) ==========
    public Venta guardarVenta(Venta venta) {
        // JPA guarda también los detalles relacionados según la configuración de la entidad.
        return ventaRepository.save(venta);
    }

    // ========== ELIMINAR VENTA ==========
    public void eliminarVenta(Long id) {
        // La comprobación previa evita informar un borrado que realmente no ocurrió.
        if (!ventaRepository.existsById(id)) {
            throw new RuntimeException("Venta no encontrada con ID: " + id);
        }
        ventaRepository.deleteById(id);
    }
}