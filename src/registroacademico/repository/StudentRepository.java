package registroacademico.repository;

import registroacademico.Model.Student;

import java.io.File;

public class StudentRepository extends Repository<Student> {
    public StudentRepository() {
        super(new File("data/students.txt"));
    }

    @Override
    String parseToString(Student e) {
        return e.getFirstName() + "," + e.getLastName() + "," + e.getID() + "," + e.isActive();
    }

    @Override
    Student parseFromString(String s) {
        String[] fields = s.split(",");
        if (fields.length == 4) {
            return new Student(fields[0], fields[1], fields[2], fields[3].equals("true"));
        }
        return null;
    }
}
