package registroacademico.controller;

import java.util.ArrayList;

public abstract class Listener {
    private ArrayList<Runnable> eventListeners = new ArrayList<>();

    protected void fireChange() {
        eventListeners.forEach(Runnable::run);
    }

    public void removeListener(Runnable listener) {
        eventListeners.remove(listener);
    }

    public void addEventListener(Runnable r) {
        this.eventListeners.add(r);
    }

    public void removeEventListener(Runnable r) {
        this.eventListeners.remove(r);
    }

}
