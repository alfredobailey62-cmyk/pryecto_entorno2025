package registroacademico.controller;

import registroacademico.Model.StudentEnrollment;

import java.util.List;
import java.util.Optional;

/**
 * El id es la cedula del estudiante ya que esta no cambia nunca y es unica.
 */
public class StudentEnrollmentController extends BaseController<StudentEnrollment, String> {

    public StudentEnrollmentController(List<StudentEnrollment> listOfModel) {
        super(listOfModel);
    }

    @Override
    protected String getModelId(StudentEnrollment model) {
        return model.getStudentID();
    }

    @Override
    public Optional<StudentEnrollment> get(String studentID) {
        return super.get(studentID);
    }

    public Optional<StudentEnrollment> get(int careerCode) {
        return this.mapOfModel.values().stream().filter(e -> e.getCareerCode() == careerCode).findFirst();
    }
}
