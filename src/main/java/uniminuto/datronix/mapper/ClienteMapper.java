package uniminuto.datronix.mapper;

import uniminuto.datronix.dto.ClienteDTO;
import uniminuto.datronix.entity.Cliente;

public class ClienteMapper {

    // Convertir Entity → DTO
    public static ClienteDTO toDto(Cliente cliente) {
    // Prepara una respuesta sencilla para el controlador.
        if (cliente == null) return null;
        return ClienteDTO.builder()
                .idCliente(cliente.getIdCliente())
                .nombreCliente(cliente.getNombreCliente())
                .telefonoCliente(cliente.getTelefonoCliente())
                .emailCliente(cliente.getEmailCliente())
                .direccionCliente(cliente.getDireccionCliente())
                .tipoCliente(cliente.getTipoCliente())
                .build();
    }

    // Convertir DTO → Entity
    public static Cliente toEntity(ClienteDTO dto) {
        // Toma los datos del formulario y construye la entidad que JPA puede guardar.
        if (dto == null) return null;
        return Cliente.builder()
                .idCliente(dto.getIdCliente())
                .nombreCliente(dto.getNombreCliente())
                .telefonoCliente(dto.getTelefonoCliente())
                .emailCliente(dto.getEmailCliente())
                .direccionCliente(dto.getDireccionCliente())
                .tipoCliente(dto.getTipoCliente())
                .build();
    }
}