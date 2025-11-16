package registroacademico.controller;

import registroacademico.Model.AcademicRecord;
import registroacademico.repository.AcademicRecordRepository;

import java.util.List;
import java.util.Optional;

public class AcademicRecordController extends FKController<AcademicRecord> {
    public AcademicRecordController() {
        super(new AcademicRecordRepository());
    }

    public Optional<AcademicRecord> get(String studentId) {
        return listOfModel.stream().filter(e -> e.getStudentID().equals(studentId)).findFirst();
    }

    public Optional<AcademicRecord> get(Integer subjectCode) {
        return listOfModel.stream().filter(e -> e.getSubjectCode() == subjectCode).findFirst();
    }

    public List<AcademicRecord> getAll(String studentId) {
        return listOfModel.stream().filter(e -> e.getStudentID().equals(studentId)).toList();
    }

    public List<AcademicRecord> getAll(Integer subjectCode) {
        return listOfModel.stream().filter(e -> e.getSubjectCode() == subjectCode).toList();
    }

    public void remove(Integer subjectCode) {
        listOfModel.removeIf(e -> e.getSubjectCode() == subjectCode);
        fireChange();
    }

    public void remove(String studentId) {
        listOfModel.removeIf(e -> e.getStudentID().equals(studentId));
        fireChange();
    }
}
