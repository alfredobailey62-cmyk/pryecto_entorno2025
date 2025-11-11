package registroacademico.repository;

import registroacademico.Model.StudentEnrollment;

import java.io.File;

public class StudentEnrollmentRepository extends Repository<StudentEnrollment> {
    public StudentEnrollmentRepository() {
        super(new File("data/student_enrollment.txt"));
    }

    @Override
    String parseToString(StudentEnrollment e) {
        return e.getCareerCode() + "," + e.getStudentID();
    }

    @Override
    StudentEnrollment parseFromString(String s) {
        String[] fields = s.split(",");
        if (fields.length == 2) {
            return new StudentEnrollment(fields[1], Integer.parseInt(fields[0]));
        }
        return null;
    }
}
