package registroacademico.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentAndSubject {
    private int careerCode;
    private int subjectCode;


    public static Optional<EnrollmentAndSubject> findByCareerCode(List<EnrollmentAndSubject> list, int careerCode) {
        return list.stream().filter(e -> e.getCareerCode() == careerCode).findFirst();
    }

    public static Optional<EnrollmentAndSubject> findBySubjectCode(List<EnrollmentAndSubject> list, int subjectCode) {
        return list.stream().filter(e -> e.getSubjectCode() == subjectCode).findFirst();
    }
}
