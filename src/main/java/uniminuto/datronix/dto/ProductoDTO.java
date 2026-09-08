package uniminuto.datronix.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// Define los datos de producto que viajan entre la pantalla y el backend.
public class ProductoDTO {
    private Long idProducto;
    private String nombreProducto;
    private String categoriaProducto;
    private String marcaProducto;
    private String modeloProducto;
    private String descripcionProducto;
    private String especificacionesProducto;
    private BigDecimal precioCostoProducto;
    private BigDecimal precioVentaProducto;
    private Long stockProducto;
    private Long stockMinimoProducto;
    private Long idProveedor;          // Solo el ID del proveedor
    private String skuProducto;
}