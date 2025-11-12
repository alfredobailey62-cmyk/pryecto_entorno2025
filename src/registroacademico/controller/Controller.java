package registroacademico.controller;

import registroacademico.repository.Repository;

import java.util.ArrayList;

/**
 * Clase base de todos los controladores
 *
 * @param <T>
 */
public abstract class Controller<T> {
    /**
     * Funciones a ejecutar al realizarce una accion que alla modificado la lista de Modelos
     */
    private final ArrayList<Runnable> eventListeners;

    protected final Repository<T> repository;

    protected Controller(Repository<T> repository) {
        this.repository = repository;
        this.eventListeners = new ArrayList<>();
    }

    /**
     * Funcion que ejecuta la funcion save del repositorio
     */
    public abstract void save();

    /**
     * Ejecuta los listeners
     */
    public void fireChange() {
        eventListeners.forEach(Runnable::run);
    }


    /**
     * Agregar evento
     *
     * @param r
     */
    public void addEventListener(Runnable r) {
        this.eventListeners.add(r);
    }

    /**
     * Elimina un Evento
     *
     * @param r
     */
    public void removeEventListener(Runnable r) {
        this.eventListeners.remove(r);
    }

}
