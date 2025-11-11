package registroacademico.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

/**
 * Matricula de Estudiante
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentEnrollment {
    /**
     * Cedula de Estudiante
     */
    private String studentID;
    /**
     * Codigo de Carrea
     */
    private int careerCode;

    public static Optional<StudentEnrollment> findByCareerCode(List<StudentEnrollment> list, int careerCode) {
        return list.stream().filter(e -> e.getCareerCode() == careerCode).findFirst();
    }

    public static Optional<StudentEnrollment> findByStudentId(List<StudentEnrollment> list, String studentID) {
        return list.stream().filter(e -> e.getStudentID().equals(studentID)).findFirst();
    }
}
