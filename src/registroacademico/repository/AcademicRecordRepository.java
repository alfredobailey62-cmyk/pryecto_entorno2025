package registroacademico.repository;

import registroacademico.Model.AcademicRecord;

public class AcademicRecordRepository extends Repository<AcademicRecord> {
    public AcademicRecordRepository() {
        super("academic_record");
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
