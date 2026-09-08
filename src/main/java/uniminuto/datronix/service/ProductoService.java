package uniminuto.datronix.service;

import java.util.List;

import org.springframework.stereotype.Service;

import uniminuto.datronix.entity.Producto;
import uniminuto.datronix.repository.ProductoRepository;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;

    }

    public List<Producto> listarProductos() {

        return productoRepository.findAll();

    }

    public Producto buscarProductoPorId(Long id) {

        return productoRepository.findById(id).orElseThrow((() -> new RuntimeException("Producto no encontrado")));

    }

    public Producto guardarProducto(Producto producto) {

        return productoRepository.save(producto);

    }

    public void eliminarProducto(Long id) {

        if (!productoRepository.existsById(id)) {

            throw new RuntimeException("Producto no encontrado");

        } else {

            productoRepository.deleteById(id);
        }

    }

    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        return productoRepository.findById(id)
                .map(producto -> {
                    producto.setNombreProducto(productoActualizado.getNombreProducto());
                    producto.setCategoriaProducto(productoActualizado.getCategoriaProducto());
                    producto.setMarcaProducto(productoActualizado.getMarcaProducto());
                    producto.setModeloProducto(productoActualizado.getModeloProducto());
                    producto.setDescripcionProducto(productoActualizado.getDescripcionProducto());
                    producto.setEspecificacionesProducto(productoActualizado.getEspecificacionesProducto());
                    producto.setPrecioCostoProducto(productoActualizado.getPrecioCostoProducto());
                    producto.setPrecioVentaProducto(productoActualizado.getPrecioVentaProducto());
                    producto.setStockProducto(productoActualizado.getStockProducto());
                    producto.setStockMinimoProducto(productoActualizado.getStockMinimoProducto());
                    producto.setProveedor(productoActualizado.getProveedor());
                    producto.setSkuProducto(productoActualizado.getSkuProducto());

                    return productoRepository.save(producto);

                })
                .orElseThrow(() -> new RuntimeException("Producto no encotrado"));

    }

}
