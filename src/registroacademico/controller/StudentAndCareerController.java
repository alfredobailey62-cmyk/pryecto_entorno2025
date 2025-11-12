package registroacademico.controller;

import registroacademico.Model.StudentAndCareer;
import registroacademico.repository.StudentAndCareerRepository;

import java.util.Optional;

/**
 * El id es la cedula del estudiante ya que esta no cambia nunca y es unica.
 */
public class StudentAndCareerController extends BaseController<StudentAndCareer, String> {

    public StudentAndCareerController() {
        super(new StudentAndCareerRepository());
    }

    @Override
    protected String getModelId(StudentAndCareer model) {
        return model.getStudentID();
    }

    @Override
    public Optional<StudentAndCareer> get(String studentID) {
        return super.get(studentID);
    }

    public Optional<StudentAndCareer> get(int careerCode) {
        return mapOfModel.values().stream().filter(e -> e.getCareerCode() == careerCode).findFirst();
    }
}
