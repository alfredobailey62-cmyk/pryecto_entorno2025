package registroacademico.controller;

import registroacademico.Model.Career;

import java.util.List;

public class CareerController extends BaseController<Career, Integer>{

    public CareerController(List<Career> listOfModel) {
        super(listOfModel);
    }

    @Override
    protected Integer getModelId(Career model) {
        return model.getCode();
    }
}
