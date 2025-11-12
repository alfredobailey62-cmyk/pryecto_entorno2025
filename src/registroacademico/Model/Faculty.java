package registroacademico.Model;

import lombok.Getter;

/**
 * Facultades que la UP que se encuentran en Boca del Toro
 *
 */
@Getter
public enum Faculty {
    CE("Facultad de Ciencias de la Educación"),
    H("Facultad de Humanidades"),
    DCP("Facultad de Derecho y Ciencias Políticas");

    /**
     * Nombre de la Facultad
     */
    private final String title;

    Faculty(String title) {
        this.title = title;
    }
}
