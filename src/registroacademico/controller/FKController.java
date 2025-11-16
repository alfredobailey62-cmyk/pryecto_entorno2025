package registroacademico.controller;

import lombok.Getter;
import registroacademico.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public abstract class FKController<T> extends Controller<T> {
    @Getter
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

    public void addAll(List<T> models) {
        listOfModel.addAll(models);
    }

}
