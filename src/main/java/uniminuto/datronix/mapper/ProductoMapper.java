package uniminuto.datronix.mapper;

import uniminuto.datronix.dto.ProductoDTO;
import uniminuto.datronix.entity.Producto;
import uniminuto.datronix.entity.Proveedor;

public class ProductoMapper {

    // Entity → DTO
    public static ProductoDTO toDto(Producto producto) {
    // La respuesta conserva el ID del proveedor, no todos sus datos.
        if (producto == null) return null;
        return ProductoDTO.builder()
                .idProducto(producto.getIdProducto())
                .nombreProducto(producto.getNombreProducto())
                .categoriaProducto(producto.getCategoriaProducto())
                .marcaProducto(producto.getMarcaProducto())
                .modeloProducto(producto.getModeloProducto())
                .descripcionProducto(producto.getDescripcionProducto())
                .especificacionesProducto(producto.getEspecificacionesProducto())
                .precioCostoProducto(producto.getPrecioCostoProducto())
                .precioVentaProducto(producto.getPrecioVentaProducto())
                .stockProducto(producto.getStockProducto())
                .stockMinimoProducto(producto.getStockMinimoProducto())
                .idProveedor(producto.getProveedor() != null ? producto.getProveedor().getIdProveedor() : null)
                .skuProducto(producto.getSkuProducto())
                .build();
    }

    // DTO → Entity (necesita el objeto Proveedor, no solo el ID)
    public static Producto toEntity(ProductoDTO dto, Proveedor proveedor) {
        // El servicio resuelve el proveedor antes de pedir esta conversión.
        if (dto == null) return null;
        return Producto.builder()
                .idProducto(dto.getIdProducto())
                .nombreProducto(dto.getNombreProducto())
                .categoriaProducto(dto.getCategoriaProducto())
                .marcaProducto(dto.getMarcaProducto())
                .modeloProducto(dto.getModeloProducto())
                .descripcionProducto(dto.getDescripcionProducto())
                .especificacionesProducto(dto.getEspecificacionesProducto())
                .precioCostoProducto(dto.getPrecioCostoProducto())
                .precioVentaProducto(dto.getPrecioVentaProducto())
                .stockProducto(dto.getStockProducto())
                .stockMinimoProducto(dto.getStockMinimoProducto())
                .proveedor(proveedor)
                .skuProducto(dto.getSkuProducto())
                .build();
    }
}