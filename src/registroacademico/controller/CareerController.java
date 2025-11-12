package registroacademico.controller;

import registroacademico.Model.Career;
import registroacademico.repository.CareerRepository;

public class CareerController extends BaseController<Career, Integer> {

    public CareerController() {
        super(new CareerRepository());
    }

    @Override
    protected Integer getModelId(Career model) {
        return model.getCode();
    }
}
