import java.util.List;
import java.util.Optional;

import com.example.tarea3_3ventas.domain.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServicio {

    @Autowired
    private FabricanteDAO fabricanteDAO;

    public List<Cliente> listAll() {

        return fabricanteDAO.getAll();

    }

    public Cliente one(Integer id) {
        Optional<Cliente> optFab = fabricanteDAO.find(id);
        if (optFab.isPresent())
            return optFab.get();
        else
            return null;
    }

    public void newFabricante(Cliente cliente) {

        fabricanteDAO.create(cliente);

    }

    public void replaceFabricante(Cliente cliente) {

        fabricanteDAO.update(cliente);

    }

    public void deleteFabricante(int id) {

        fabricanteDAO.delete(id);

    }

}