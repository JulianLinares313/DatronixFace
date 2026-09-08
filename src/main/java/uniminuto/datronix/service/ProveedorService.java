package uniminuto.datronix.service;

import uniminuto.datronix.entity.Proveedor;
import uniminuto.datronix.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public List<Proveedor> listarProveedores() {
        return proveedorRepository.findAll();
    }

    public Proveedor buscarProveedorPorId(Long id) {
        return proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
    }

    public Proveedor guardarProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public Proveedor actualizarProveedor(Long id, Proveedor proveedorActualizado) {
        return proveedorRepository.findById(id)
                .map(proveedor -> {
                    proveedor.setNombreEmpresa(proveedorActualizado.getNombreEmpresa());
                    proveedor.setContactoProveedor(proveedorActualizado.getContactoProveedor());
                    proveedor.setTelefonoProveedor(proveedorActualizado.getTelefonoProveedor());
                    proveedor.setEmailProveedor(proveedorActualizado.getEmailProveedor());
                    proveedor.setDireccionProveedor(proveedorActualizado.getDireccionProveedor());
                    return proveedorRepository.save(proveedor);
                })
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
    }

    public void eliminarProveedor(Long id) {
        if (!proveedorRepository.existsById(id)) {
            throw new RuntimeException("Proveedor no encontrado");
        }
        proveedorRepository.deleteById(id);
    }
}