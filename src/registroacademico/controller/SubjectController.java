package registroacademico.controller;

import registroacademico.Model.Subject;
import registroacademico.repository.SubjectRepository;

public class SubjectController extends BaseController<Subject, Integer> {
    public SubjectController() {
        super(new SubjectRepository());
    }

    @Override
    protected Integer getModelId(Subject model) {
        return model.getCode();
    }
}
