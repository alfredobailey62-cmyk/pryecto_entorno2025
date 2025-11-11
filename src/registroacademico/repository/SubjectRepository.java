package registroacademico.repository;

import registroacademico.Model.Subject;

import java.io.File;

public class SubjectRepository extends Repository<Subject> {
    public SubjectRepository() {
        super(new File("data/subject.txt"));
    }

    @Override
    String parseToString(Subject e) {
        return e.getName() + "," + e.getCode();
    }

    @Override
    Subject parseFromString(String s) {
        String[] fields = s.split(",");
        if (fields.length == 2) {
            return new Subject(fields[0], Integer.parseInt(fields[1]));
        }
        return null;
    }
}
