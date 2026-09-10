package uniminuto.datronix.service;

import uniminuto.datronix.dto.ProveedorDTO;
import uniminuto.datronix.entity.Proveedor;
import uniminuto.datronix.exception.ProveedorDuplicadoException;
import uniminuto.datronix.exception.ProveedorEmailDuplicadoException;
import uniminuto.datronix.exception.ProveedorIdDuplicadoException;
import uniminuto.datronix.exception.ProveedorNotFoundException;
import uniminuto.datronix.exception.ProveedorTelefonoDuplicadoException;
import uniminuto.datronix.mapper.ProveedorMapper;
import uniminuto.datronix.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
// Aplica el flujo de proveedores: convertir datos, consultar la base y devolver
// resultados.
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public List<ProveedorDTO> listarProveedores() {
        // Convierte cada proveedor guardado al formato que utiliza la interfaz.
        return proveedorRepository.findAll()
                .stream()
                .map(ProveedorMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProveedorDTO buscarProveedorPorId(String id) {
        // Si no existe el ID, la excepción específica permite responder con un mensaje
        // adecuado.
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new ProveedorNotFoundException(id));
        return ProveedorMapper.toDto(proveedor);
    }

    public ProveedorDTO guardarProveedor(ProveedorDTO dto) {

        if (proveedorRepository.existsById(dto.getIdProveedor())) {
            throw new ProveedorIdDuplicadoException(dto.getIdProveedor());

        }

        if (proveedorRepository.existsByNombreEmpresa(dto.getNombreEmpresa())) {
            throw new ProveedorDuplicadoException(dto.getNombreEmpresa());

        }

        if (proveedorRepository.existsByTelefonoProveedor(dto.getTelefonoProveedor())) {
            throw new ProveedorTelefonoDuplicadoException(dto.getTelefonoProveedor());
        }

        if (proveedorRepository.existsByEmailProveedor(dto.getEmailProveedor())) {

            throw new ProveedorEmailDuplicadoException(dto.getEmailProveedor());

        }
        // El DTO se transforma en entidad, se guarda y se vuelve a convertir para la
        // respuesta.
        Proveedor proveedor = ProveedorMapper.toEntity(dto);
        Proveedor guardado = proveedorRepository.save(proveedor);
        return ProveedorMapper.toDto(guardado);
    }

    public ProveedorDTO actualizarProveedor(String id, ProveedorDTO dto) {
        // Se conserva el proveedor encontrado y se actualizan sus datos editables.
        Proveedor proveedorExistente = proveedorRepository.findById(id)
                .orElseThrow(() -> new ProveedorNotFoundException(id));

        proveedorExistente.setNombreEmpresa(dto.getNombreEmpresa());
        proveedorExistente.setContactoProveedor(dto.getContactoProveedor());
        proveedorExistente.setTelefonoProveedor(dto.getTelefonoProveedor());
        proveedorExistente.setEmailProveedor(dto.getEmailProveedor());
        proveedorExistente.setDireccionProveedor(dto.getDireccionProveedor());

        Proveedor actualizado = proveedorRepository.save(proveedorExistente);
        return ProveedorMapper.toDto(actualizado);
    }

    public void eliminarProveedor(String id) {
        // Evita confirmar un borrado cuando el proveedor nunca estuvo registrado.
        if (!proveedorRepository.existsById(id)) {
            throw new ProveedorNotFoundException(id);
        }
        proveedorRepository.deleteById(id);
    }
}