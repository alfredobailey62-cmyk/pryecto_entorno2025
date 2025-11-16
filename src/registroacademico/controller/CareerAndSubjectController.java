package registroacademico.controller;

import registroacademico.Model.CareerAndSubject;
import registroacademico.repository.CareerAndSubjectRepository;

import java.util.List;
import java.util.Optional;

public class CareerAndSubjectController extends FKController<CareerAndSubject> {
    public CareerAndSubjectController() {
        super(new CareerAndSubjectRepository());
    }

    public Optional<CareerAndSubject> getByCareerCode(Integer careerCode) {
        return listOfModel.stream().filter(e -> e.getCareerCode() == careerCode).findFirst();
    }

    public Optional<CareerAndSubject> getBySubjectCode(Integer subjectCode) {
        return listOfModel.stream().filter(e -> e.getSubjectCode() == subjectCode).findFirst();
    }

    public List<CareerAndSubject> getAllByCareerCode(Integer careerCode) {
        return listOfModel.stream().filter(e -> e.getCareerCode() == careerCode).toList();
    }

    public List<CareerAndSubject> getAllBySubjectCode(Integer subjectCode) {
        return listOfModel.stream().filter(e -> e.getSubjectCode() == subjectCode).toList();
    }

    public void removeByCareerCode(Integer careerCode) {
        listOfModel.removeIf(e -> e.getCareerCode() == careerCode);
        fireChange();
    }

    public void removeBySubjectCode(Integer subjectCode) {
        listOfModel.removeIf(e -> e.getSubjectCode() == subjectCode);
        fireChange();
    }
}
