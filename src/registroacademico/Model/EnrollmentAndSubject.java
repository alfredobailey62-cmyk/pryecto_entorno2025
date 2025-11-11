package registroacademico.Model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EnrollmentAndSubject {
    int careerCode;
    int subjectCode;


    public static Optional<EnrollmentAndSubject> findByCareerCode(List<EnrollmentAndSubject> list, int careerCode) {
        return list.stream().filter(e -> e.getCareerCode() == careerCode).findFirst();
    }

    public static Optional<EnrollmentAndSubject> findBySubjectCode(List<EnrollmentAndSubject> list, int subjectCode) {
        return list.stream().filter(e -> e.getSubjectCode() == subjectCode).findFirst();
    }
}
