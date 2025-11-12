package registroacademico.controller;

import registroacademico.Model.Student;
import registroacademico.repository.StudentRepository;


public class StudentController extends BaseController<Student, String> {

    public StudentController() {
        super(new StudentRepository());
    }


    @Override
    protected String getModelId(Student model) {
        return model.getID();
    }
}
