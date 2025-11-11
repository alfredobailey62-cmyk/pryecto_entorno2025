package registroacademico.repository;

import registroacademico.Model.AcademicRecord;

import java.io.File;

public class AcademicRecordRepository extends Repository<AcademicRecord> {
    public AcademicRecordRepository() {
        super(new File("data/academic_record.txt"));
    }

    @Override
    String parseToString(AcademicRecord e) {
        return e.getStudentID() + "," + e.getSubjectCode() + "," + e.getAverage();
    }

    @Override
    AcademicRecord parseFromString(String s) {
        String[] fields = s.split(",");
        if (fields.length == 3) {
            return new AcademicRecord(fields[0], Integer.parseInt(fields[1]), Integer.parseInt(fields[2]));
        }
        return null;
    }
}
