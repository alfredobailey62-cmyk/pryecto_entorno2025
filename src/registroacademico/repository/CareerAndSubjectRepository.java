package registroacademico.repository;

import registroacademico.Model.CareerAndSubject;

public class CareerAndSubjectRepository extends Repository<CareerAndSubject> {
    public CareerAndSubjectRepository() {
        super("enrollment_and_subject");
    }

    @Override
    String parseToString(CareerAndSubject e) {
        return e.getCareerCode() + "," + e.getSubjectCode();
    }

    @Override
    CareerAndSubject parseFromString(String s) {
        String[] fields = s.split(",");
        if (fields.length == 2) {
            return new CareerAndSubject(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]));
        }
        return null;
    }
}
