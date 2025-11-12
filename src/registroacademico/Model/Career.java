package registroacademico.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Carrera
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Career {
    /**
     * Nombre de carrera
     */
    private String name;
    /**
     * Facultad de la carrera
     */
    private Faculty faculty;
    /**
     * Codigo de la carrera
     */
    private int code;

    /**
     * Solo se muestra el nombre, para que en los combobox solo se vea el nombre
     *
     * @return
     */
    @Override
    public String toString() {
        return this.name;
    }

}
