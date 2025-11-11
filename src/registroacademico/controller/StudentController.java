package registroacademico.controller;

import registroacademico.Model.Student;

import java.util.List;


public class StudentController extends BaseController<Student, String>{

    public StudentController(List<Student> listOfModel) {
        super(listOfModel);
    }


    @Override
    protected String getModelId(Student model) {
        return model.getID();
    }
}
