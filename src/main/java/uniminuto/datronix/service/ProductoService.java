package uniminuto.datronix.service;

import uniminuto.datronix.dto.ProductoDTO;
import uniminuto.datronix.entity.Producto;
import uniminuto.datronix.entity.Proveedor;
import uniminuto.datronix.exception.ProductoNotFoundException;
import uniminuto.datronix.exception.ProveedorNotFoundException;
import uniminuto.datronix.mapper.ProductoMapper;
import uniminuto.datronix.repository.ProductoRepository;
import uniminuto.datronix.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
// Reúne el flujo de productos y resuelve la relación entre cada producto y su proveedor.
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final ProveedorRepository proveedorRepository;

    public ProductoService(ProductoRepository productoRepository, ProveedorRepository proveedorRepository) {
        this.productoRepository = productoRepository;
        this.proveedorRepository = proveedorRepository;
    }

    @Transactional(readOnly = true)
    public List<ProductoDTO> listarProductos() {
        // Lee los productos y convierte cada entidad al formato que consume el frontend.
        return productoRepository.findAll()
                .stream()
                .map(ProductoMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProductoDTO buscarProductoPorId(Long id) {
        // Si el producto no existe, se lanza una excepción específica para devolver un error claro.
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));
        return ProductoMapper.toDto(producto);
    }

   public ProductoDTO guardarProducto(ProductoDTO dto) {
    // Nos aseguramos de que la BD genere el ID, ignorando cualquier valor que venga del cliente.
    dto.setIdProducto(null);

    Proveedor proveedor = proveedorRepository.findById(dto.getIdProveedor())
            .orElseThrow(() -> new ProveedorNotFoundException(dto.getIdProveedor()));

    Producto producto = ProductoMapper.toEntity(dto, proveedor);
    Producto guardado = productoRepository.save(producto);
    return ProductoMapper.toDto(guardado);
}

    public ProductoDTO actualizarProducto(Long id, ProductoDTO dto) {
        // Se edita el registro existente y se conserva su identidad en la base de datos.
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));

        // Actualizar campos
        productoExistente.setNombreProducto(dto.getNombreProducto());
        productoExistente.setCategoriaProducto(dto.getCategoriaProducto());
        productoExistente.setMarcaProducto(dto.getMarcaProducto());
        productoExistente.setModeloProducto(dto.getModeloProducto());
        productoExistente.setDescripcionProducto(dto.getDescripcionProducto());
        productoExistente.setEspecificacionesProducto(dto.getEspecificacionesProducto());
        productoExistente.setPrecioCostoProducto(dto.getPrecioCostoProducto());
        productoExistente.setPrecioVentaProducto(dto.getPrecioVentaProducto());
        productoExistente.setStockProducto(dto.getStockProducto());
        productoExistente.setStockMinimoProducto(dto.getStockMinimoProducto());
        productoExistente.setSkuProducto(dto.getSkuProducto());

        // Si se cambió el proveedor, buscarlo y actualizarlo
        if (dto.getIdProveedor() != null) {
            // La relación solo se cambia cuando el formulario envía un nuevo proveedor.
            Proveedor proveedor = proveedorRepository.findById(dto.getIdProveedor())
                    .orElseThrow(() -> new ProveedorNotFoundException(dto.getIdProveedor()));
            productoExistente.setProveedor(proveedor);
        }

        Producto actualizado = productoRepository.save(productoExistente);
        return ProductoMapper.toDto(actualizado);
    }

    public void eliminarProducto(Long id) {
        // Primero se confirma la existencia para que un ID inválido produzca un mensaje útil.
        if (!productoRepository.existsById(id)) {
            throw new ProductoNotFoundException(id);
        }
        productoRepository.deleteById(id);
    }
}