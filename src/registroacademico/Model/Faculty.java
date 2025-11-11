package registroacademico.Model;

import lombok.Getter;

@Getter
public enum Faculty {
    CE("Facultad de Ciencias de la Educación"),
    H("Facultad de Humanidades"),
    DCP("Facultad de Derecho y Ciencias Políticas");

    private final String title;

    Faculty(String title) {
        this.title = title;
    }
}
