package uniminuto.datronix.mapper;

import uniminuto.datronix.dto.ProveedorDTO;
import uniminuto.datronix.entity.Proveedor;

public class ProveedorMapper {

    public static ProveedorDTO toDto(Proveedor proveedor) {
    // Convierte la entidad persistida en el formato que usa la pantalla.
        if (proveedor == null) return null;
        return ProveedorDTO.builder()
                .idProveedor(proveedor.getIdProveedor())
                .nombreEmpresa(proveedor.getNombreEmpresa())
                .contactoProveedor(proveedor.getContactoProveedor())
                .telefonoProveedor(proveedor.getTelefonoProveedor())
                .emailProveedor(proveedor.getEmailProveedor())
                .direccionProveedor(proveedor.getDireccionProveedor())
                .build();
    }

    public static Proveedor toEntity(ProveedorDTO dto) {
        // Convierte los datos recibidos desde la interfaz en una entidad guardable.
        if (dto == null) return null;
        return Proveedor.builder()
                .idProveedor(dto.getIdProveedor())
                .nombreEmpresa(dto.getNombreEmpresa())
                .contactoProveedor(dto.getContactoProveedor())
                .telefonoProveedor(dto.getTelefonoProveedor())
                .emailProveedor(dto.getEmailProveedor())
                .direccionProveedor(dto.getDireccionProveedor())
                .build();
    }
}