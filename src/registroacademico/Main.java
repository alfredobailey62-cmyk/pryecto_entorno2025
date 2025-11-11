package registroacademico;

import com.formdev.flatlaf.FlatLightLaf;


public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            FlatLightLaf.setup();

            // Facultades
//            var careers = List.of(
//                    // Facultad de Ciencias de la Educación
//                    new Career("Licenciatura en Educación Primaria", Faculty.CE, 1000),
//                    new Career("Licenciatura en Educación Preescolar", Faculty.CE, 1001),
//                    new Career("Profesorado en Educación Primaria", Faculty.CE, 1002),
//                    new Career("Profesorado en Educación Media", Faculty.CE, 1003),
//
//                    // Facultad de Humanidades
//                    new Career("Licenciatura en Español", Faculty.H, 1004),
//                    new Career("Licenciatura en Inglés", Faculty.H, 1005),
//                    new Career("Licenciatura en Geografía e Historia", Faculty.H, 1006),
//                    new Career("Licenciatura en Educación Física", Faculty.H, 1007),
//                    new Career("Licenciatura en Humanidades con Énfasis en Turismo Geográfico Ecológico", Faculty.H, 1008),
//
//                    // Facultad de Derecho y Ciencias Políticas
//                    new Career("Licenciatura en Derecho y Ciencias Políticas", Faculty.DCP, 1009)
//            );
//
//            // Materias
//            var subjects = List.of(
//                    // Comunes
//                    new Subject("Lengua Española I", 1300),
//                    new Subject("Lengua Española II", 1301),
//                    new Subject("Psicología Educativa", 1302),
//                    new Subject("Didáctica General", 1303),
//                    new Subject("Sociología de la Educación", 1304),
//                    new Subject("Tecnologías Aplicadas a la Educación", 1305),
//
//                    // Educación Primaria y Preescolar
//                    new Subject("Desarrollo Infantil", 1306),
//                    new Subject("Metodología de la Enseñanza de la Lectura y Escritura", 1307),
//                    new Subject("Planeamiento Educativo", 1308),
//                    new Subject("Evaluación del Aprendizaje", 1309),
//
//                    // Humanidades
//                    new Subject("Gramática Española Avanzada", 1310),
//                    new Subject("Literatura Hispanoamericana", 1311),
//                    new Subject("Fonética y Fonología del Inglés", 1312),
//                    new Subject("Historia Universal Contemporánea", 1313),
//                    new Subject("Turismo Ecológico Sostenible", 1314),
//                    new Subject("Educación Física y Salud", 1315),
//
//                    // Derecho y Ciencias Políticas
//                    new Subject("Introducción al Derecho", 1316),
//                    new Subject("Derecho Constitucional", 1317),
//                    new Subject("Derecho Civil", 1318),
//                    new Subject("Derecho Penal", 1319),
//                    new Subject("Derecho Administrativo", 1320),
//                    new Subject("Teoría del Estado", 1321)
//            );
//
//            // Relación Materias ↔ Carreras
//            var subjectsToCareers = List.of(
//                    // Comunes a todas las carreras
//                    new EnrollmentAndSubject(1000, 1300),
//                    new EnrollmentAndSubject(1001, 1300),
//                    new EnrollmentAndSubject(1004, 1300),
//                    new EnrollmentAndSubject(1005, 1300),
//                    new EnrollmentAndSubject(1006, 1300),
//                    new EnrollmentAndSubject(1009, 1300),
//
//                    // Educación Primaria
//                    new EnrollmentAndSubject(1000, 1306),
//                    new EnrollmentAndSubject(1000, 1307),
//                    new EnrollmentAndSubject(1000, 1308),
//                    new EnrollmentAndSubject(1000, 1309),
//
//                    // Educación Preescolar
//                    new EnrollmentAndSubject(1001, 1306),
//                    new EnrollmentAndSubject(1001, 1307),
//                    new EnrollmentAndSubject(1001, 1309),
//
//                    // Humanidades - Español
//                    new EnrollmentAndSubject(1004, 1310),
//                    new EnrollmentAndSubject(1004, 1311),
//
//                    // Humanidades - Inglés
//                    new EnrollmentAndSubject(1005, 1312),
//
//                    // Humanidades - Geografía e Historia
//                    new EnrollmentAndSubject(1006, 1313),
//
//                    // Humanidades - Turismo Geográfico Ecológico
//                    new EnrollmentAndSubject(1008, 1314),
//
//                    // Humanidades - Educación Física
//                    new EnrollmentAndSubject(1007, 1315),
//
//                    // Derecho y Ciencias Políticas
//                    new EnrollmentAndSubject(1009, 1316),
//                    new EnrollmentAndSubject(1009, 1317),
//                    new EnrollmentAndSubject(1009, 1318),
//                    new EnrollmentAndSubject(1009, 1319),
//                    new EnrollmentAndSubject(1009, 1320),
//                    new EnrollmentAndSubject(1009, 1321)
//            );
//
//            var repo = new CareerRepository();
//            var subjectRepository = new SubjectRepository();
//            var enrollmentAndSubjectRepository = new EnrollmentAndSubjectRepository();
//            subjectRepository.save(subjects);
//            repo.save(careers);
//            enrollmentAndSubjectRepository.save(subjectsToCareers);
//
//            IO.println(repo.read());

//            new MainFrame().setVisible(true);

            new Dashboard().setVisible(true);
        });
    }
}

