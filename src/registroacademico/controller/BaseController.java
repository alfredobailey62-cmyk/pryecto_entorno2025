package registroacademico.controller;

import registroacademico.repository.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 *
 * @param <T>  Modelo
 * @param <ID> Id del Modelo
 */
public abstract class BaseController<T, ID> extends Controller<T> {

    /**
     * Colecion de Modelo, se optiene del .txt y se asocia a su id unico para mejorar las busquedas
     */
    protected final HashMap<ID, T> mapOfModel;

    public BaseController(Repository<T> repository) {
        super(repository);
        this.mapOfModel = new HashMap<>();

        // Carga los modelos del .txt y los agrega al mapa
        for (T model : repository.read()) {
            mapOfModel.put(getModelId(model), model);
        }
    }

    @Override
    public void save() {
        repository.save(mapOfModel.values().stream().toList());
    }

    /**
     * Obtiene el Modelo mediante el ID
     *
     * @param id Id del modelo
     * @return
     */
    public Optional<T> get(ID id) {
        return Optional.ofNullable(mapOfModel.get(id));
    }

    /**
     * Verifica la existencia de un Modelo
     *
     * @param id
     * @return true si existe
     */
    public boolean exists(ID id) {
        return mapOfModel.containsKey(id);
    }

    /**
     * Obtiene la lista del Modelo
     *
     * @return
     */
    public List<T> getAll() {
        return mapOfModel.values().stream().toList();
    }

    /**
     * Funcion auxiliar para obtener el ID de un Modelo
     *
     * @param model
     * @return
     */
    protected abstract ID getModelId(T model);

    /**
     * Funcion para agregar un nuevo elemento a la coleccion
     *
     * @param model
     */
    public void add(T model) {
        mapOfModel.put(getModelId(model), model);
        fireChange();
    }

    /**
     * Funcion para eliminar un elemento de la coleccion
     *
     * @param id
     */
    public void remove(ID id) {
        mapOfModel.remove(id);
        fireChange();
    }
}
