package registroacademico.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa a un estudiante
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    /**
     * Nombre del estudiante
     */
    private String firstName;
    /**
     * Apellido del estudiante
     */
    private String lastName;
    /**
     * Cedula del estudiante
     */
    private String ID;
    /**
     * Indica si el estudiante esta activo
     */
    private boolean isActive;

}

