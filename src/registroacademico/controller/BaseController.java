package registroacademico.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public abstract class BaseController<T, ID> extends Listener {

    protected HashMap<ID, T> mapOfModel = new HashMap<>();

    public BaseController(List<T> listOfModel) {
        for (T model : listOfModel) {
            mapOfModel.put(getModelId(model), model);
        }

        IO.println(mapOfModel.toString());
    }

    public Optional<T> get(ID id) {
        return Optional.of(mapOfModel.get(id));
    }

    protected abstract ID getModelId(T model);

    public void add(T model) {
        mapOfModel.put(getModelId(model), model);
        fireChange();
    }

    public void remove(ID id) {
        mapOfModel.remove(id);
        fireChange();
    }
}
