package registroacademico.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Notas de estudiante en cada materia
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcademicRecord {
    /**
     * Cedula de estudiante
     */
    String studentID;
    /**
     * Codigo de asignatura
     */
    int subjectCode;
    /**
     * Promedio de estudiante en la materia
     */
    int average;
}
