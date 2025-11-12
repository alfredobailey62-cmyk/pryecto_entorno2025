package registroacademico.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Matricula de Estudiante
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentAndCareer {
    /**
     * Cedula de Estudiante
     */
    private String studentID;
    /**
     * Codigo de Carrea
     */
    private int careerCode;

}
