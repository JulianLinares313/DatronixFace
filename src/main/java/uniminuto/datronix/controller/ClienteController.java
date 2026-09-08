package uniminuto.datronix.controller;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uniminuto.datronix.entity.Cliente;
import uniminuto.datronix.service.ClienteService;
import org.springframework.web.bind.annotation.PutMapping;

//Definimos esat cale como un constructor el cual es el encardo de recibir las peticiones http con su metodo enviasdos desde el html
@RestController
// agregamos la url base
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    // Creamos una variable fija la cual sera como intermediaio para recibir los
    // datos de la clase ClienteService
    // y usar sus herramienta no de forma directa si no mediante esta variable
    private final ClienteService clienteService;

    // Creamos un constructo el cual recibira los datos de la clase ClienteService
    // el cual nos dara los metodos encargados de realizar las peticiones al Jpa,
    // almacenamos estos datos en la variable craeda en este clase para utilizarlos
    // sin afectar los datos
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;

    }

    @GetMapping
    public List<Cliente> listarClientes() {

        return clienteService.listarClientes();

    }

    // recivimos el documento json enviada por el js con el metodo get para realizar
    // la solicitud recibir los datos y retornarlos al js
    @GetMapping("/{id}") // El documento json recibido lo convertimos a una variable mediante el
                         // PathVariable y envaimos la solictud para retornar el cliente
    public Cliente buscarPorId(@PathVariable String id) {

        return clienteService.buscarClientePorId(id);

    }

    // recivimos el documento json enviado por el js con el metodo post para
    // realidar la solicitud al jpa y retornar los datos
    @PostMapping // convertimos el documento enviado por el js a un objeto mediante el
                 // RequestBody y realizamos la solicitud para crear el nuevo cliente
    public Cliente guardarCliente(@RequestBody Cliente cliente) {

        return clienteService.guardarCliente(cliente);

    }

    // recivimos el documento json enviada por el js con el metodo Delete para
    // realizar la solicitud y eliminar el cliente al js
    @DeleteMapping("/{id}") // El documento json recibido lo convertimos a una variable mediante el
    public ResponseEntity<?> eliminarCliente(@PathVariable String id) { // PathVariable y envaimos la solictud para eliminar el cliente
                                                                        
        // el ResponseEntity nos permite devolver una peticion http personalizada
        try {

            clienteService.eliminarCliente(id);
            return ResponseEntity.ok().build();// retorna un mensaje http 200 si todo sale bien

        } catch (DataIntegrityViolationException e) {// el DataIntegrityViolationException cactura el error lanzado por
                                                     // el Jpa
            return ResponseEntity.status(HttpStatus.CONFLICT)// restorna como respuesta un 409 y muestra un mensaje de
                                                             // advertencia
                    .body("El cliente no se puede eliminar por que tiene compras asociadas");// retornamos el mensaje

        }

    }

    @PutMapping("/{id}")
    public Cliente actualizarCliente(@PathVariable String id, @RequestBody Cliente cliente) {

        return clienteService.actualizarCliente(id, cliente);

    }

}
