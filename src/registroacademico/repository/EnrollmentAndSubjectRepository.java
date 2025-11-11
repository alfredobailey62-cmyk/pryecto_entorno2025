package registroacademico.repository;

import registroacademico.Model.EnrollmentAndSubject;

import java.io.File;

public class EnrollmentAndSubjectRepository extends Repository<EnrollmentAndSubject> {
    public EnrollmentAndSubjectRepository() {
        super(new File("data/enrollment_and_subject.txt"));
    }

    @Override
    String parseToString(EnrollmentAndSubject e) {
        return e.getCareerCode() + "," + e.getSubjectCode();
    }

    @Override
    EnrollmentAndSubject parseFromString(String s) {
        String[] fields = s.split(",");
        if (fields.length == 2) {
            return new EnrollmentAndSubject(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]));
        }
        return null;
    }
}
