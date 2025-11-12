package registroacademico.repository;

import registroacademico.Model.Career;
import registroacademico.Model.Faculty;

public class CareerRepository extends Repository<Career> {
    public CareerRepository() {
        super("career");
    }

    @Override
    String parseToString(Career e) {
        return e.getName() + "," + e.getFaculty().name() + "," + e.getCode();
    }

    @Override
    Career parseFromString(String s) {
        String[] fields = s.split(",");
        if (fields.length == 3) {
            return new Career(fields[0], Faculty.valueOf(fields[1]), Integer.parseInt(fields[2]));
        }
        return null;
    }
}
