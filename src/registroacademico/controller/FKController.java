package registroacademico.controller;

import registroacademico.repository.Repository;

import java.util.ArrayList;

public abstract class FKController<T> extends Controller<T> {
    /**
     * Coleccion del modelo
     */
    protected final ArrayList<T> listOfModel;

    public FKController(Repository<T> repository) {
        super(repository);
        this.listOfModel = new ArrayList<>(repository.read());
    }

    @Override
    public void save() {
        repository.save(listOfModel);
    }

    /**
     * Funcion para agregar un elemento a la coleccion
     *
     * @param model
     */
    public void add(T model) {
        listOfModel.add(model);
        fireChange();
    }

}
