package registroacademico.controller;

import registroacademico.Model.Subject;

import java.util.List;

public class SubjectController extends BaseController<Subject, Integer>{
    public SubjectController(List<Subject> listOfModel) {
        super(listOfModel);
    }

    @Override
    protected Integer getModelId(Subject model) {
        return model.getCode();
    }
}
