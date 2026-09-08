package uniminuto.datronix.service;

import java.util.List;

import org.springframework.stereotype.Service;

import uniminuto.datronix.entity.Cliente;
import uniminuto.datronix.repository.ClienteRepository;

@Service
public class ClienteService {

    // Creamos una variable fija la cual sera como intermediaio para recibir los
    // datos del Jpa creado en ClienteRepository
    // y usar sus herramienta no de forma directa si no mediante esta variable
    private final ClienteRepository clienteRepository;

    // Creamos un constructo el cual recibira los datos de la clase
    // ClienteRepositoty el cual es el Jpa con el crup ya generado,
    // almacenamos estos datos en la variable craeda en este clase para utilizarlos
    // sin afectar los datos
    public ClienteService(ClienteRepository clienteRepository) {

        this.clienteRepository = clienteRepository;

    }

    // creamos un metodo el cual va retornar todos los datos que esten alamcenado en
    // nuestra base de datos
    // y lo recibiremos como una lista mediante el metodo findAll del Jpa
    public List<Cliente> listarClientes() {

        return clienteRepository.findAll();

    }

    // enviamos la variable reciba id para genarar una consulta a la base de datos
    // y si ese id existe la base de datos nos dara los datos que tiene ese cliente
    // buscado por su id mediante la herramienta findById y los retornamos
    public Cliente buscarClientePorId(String id) {

        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

    }

    public Cliente guardarCliente(Cliente cliente) {

        return clienteRepository.save(cliente);

    }

    // en este metodo verificamos que el dia del cliente sea existente y es true
    // aplicamos directamente la consulta para eliminarlo mediante el metodo del Jpa
    // deleteById
    // en el caso de que sea false el cual nos hace entender que no existe un
    // cliente registrado con ese id, cancelamos la operacion con un throw new
    // RuntimeException
    public void eliminarCliente(String id) {

        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente no encontrado");
        } else {

            clienteRepository.deleteById(id);

        }

    }

    public Cliente actualizarCliente(String id, Cliente clienteActualizado) {

        return clienteRepository.findById(id)
                .map(cliente -> {
                    cliente.setNombreCliente(clienteActualizado.getNombreCliente());
                    cliente.setEmailCliente(clienteActualizado.getEmailCliente());
                    cliente.setDireccionCliente(clienteActualizado.getDireccionCliente());
                    cliente.setTelefonoCliente(clienteActualizado.getTelefonoCliente());
                    return clienteRepository.save(cliente);

                }

                )
                .orElseThrow(() -> new RuntimeException("cliente no encontrado"));
    }

}
