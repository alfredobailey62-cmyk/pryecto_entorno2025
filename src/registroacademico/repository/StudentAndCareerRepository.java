package registroacademico.repository;

import registroacademico.Model.StudentAndCareer;

public class StudentAndCareerRepository extends Repository<StudentAndCareer> {
    public StudentAndCareerRepository() {
        super("student_enrollment");
    }

    @Override
    String parseToString(StudentAndCareer e) {
        return e.getCareerCode() + "," + e.getStudentID();
    }

    @Override
    StudentAndCareer parseFromString(String s) {
        String[] fields = s.split(",");
        if (fields.length == 2) {
            return new StudentAndCareer(fields[1], Integer.parseInt(fields[0]));
        }
        return null;
    }
}
