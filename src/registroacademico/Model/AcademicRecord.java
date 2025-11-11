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
    //estudiante
    String studentID;
    //codigo de asignatura
    int subjectCode;
    //promedio del estudiante en la asignatura
    int average;
}
