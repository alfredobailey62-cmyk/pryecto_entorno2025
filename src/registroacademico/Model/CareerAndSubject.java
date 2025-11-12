package registroacademico.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa la union de una carrera a una materia
 * <p>
 * La union es de muchos a muchos
 * 1. muchas materias pueden estar asignadas a muchas carreras
 * 2. muchas carreras pueden tener multiples materias
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CareerAndSubject {
    /**
     * Codigo de carrera
     */
    private int careerCode;
    /**
     * Codigo de materia
     */
    private int subjectCode;

}
