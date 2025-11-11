package registroacademico;

import registroacademico.Model.Faculty;
import registroacademico.Model.Student;
import registroacademico.controller.BaseController;
import registroacademico.controller.ControllerContext;
import registroacademico.controller.StudentController;
import registroacademico.repository.*;
import registroacademico.ui.*;

import javax.swing.*;
import java.util.*;
import java.util.function.Consumer;

public class Dashboard extends JFrame {

    public Dashboard() {

        setTitle("Sistema Académico - Registro de Estudiantes");
        setSize(650, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        IO.println(Faculty.valueOf("H").getTitle());
        JTabbedPane tabs = new JTabbedPane();


//        var repositories = new ControllerContext();




        // crea los Repositorios
        var careerRepo = new CareerRepository();
        var subjectRepo = new SubjectRepository();
        var enrollmentAndSubjectRepo = new EnrollmentAndSubjectRepository();
        var academicRecordRepo = new AcademicRecordRepository();
        var studentEnrollmentRepo = new StudentEnrollmentRepository();
        var studentRepo = new StudentRepository();

        //busca las listas en los archivos
        var careers = new ArrayList<>(careerRepo.read());
        var subjects = new ArrayList<>(subjectRepo.read());
        var enrollmentAndSubjects = new ArrayList<>(enrollmentAndSubjectRepo.read());
        var academicRecords = new ArrayList<>(academicRecordRepo.read());
        var studentEnrollments = new ArrayList<>(studentEnrollmentRepo.read());
        var students = new ArrayList<>(studentRepo.read());

        var studentController = new StudentController(studentRepo.read());

        var contect = new ControllerContext<>();


        //al cerrarse el programa, actualiza los archivos con los cambios
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            careerRepo.save(careers);
            subjectRepo.save(subjects);
            studentRepo.save(students);
            studentEnrollmentRepo.save(studentEnrollments);
            academicRecordRepo.save(academicRecords);
        }));


        // --- Agregar pestañas ---
        var addStudent = new AddStudent(careers, studentEnrollments, students);
        var modifyStudent = new ModifyStudent(careers, studentEnrollments, students);

        Consumer<Optional<Student>> findAndModify = (student) -> {
            if (modifyStudent.setStudent(student))
                tabs.setSelectedComponent(modifyStudent);
        };

        var listStudent = new ListStudent(careers, studentEnrollments, students, findAndModify);
        var listCareer = new ListCareer(careers);
        var listSubject = new ListSubject(subjects, careers, enrollmentAndSubjects);
        tabs.add("Registrar", addStudent);

        tabs.add("Listar", listStudent);

        tabs.add("Buscar/Modificar", modifyStudent);
        tabs.add("Carreras", listCareer);
        tabs.add("Materias", listSubject);

        tabs.addChangeListener(_ -> {
            listStudent.refresh();
        });

        add(tabs);
//
//        // --- Panel Registrar ---
//        JPanel registrar = new JPanel(new GridLayout(6, 2, 2, 2));
//        JTextField tNombre = new JTextField();
//        JTextField tApellido = new JTextField();
//        JTextField tCedula = new JTextField();
//        var cbCareer = new JComboBox<Career>();
//
//        cbCareer.setModel(new DefaultComboBoxModel<>(new Vector<>()));
//
//
//        JButton btnGuardar = new JButton("Registrar");
//
//        registrar.add(new JLabel("Nombre:"));
//        registrar.add(tNombre);
//        registrar.add(new JLabel("Apellido:"));
//        registrar.add(tApellido);
//        registrar.add(new JLabel("Cédula:"));
//        registrar.add(tCedula);
//        registrar.add(new JLabel("Carrera:"));
//        registrar.add(cbCareer);
//        registrar.add(new JLabel(""));
//        registrar.add(btnGuardar);
//
//        btnGuardar.addActionListener((ActionEvent e) -> {
//            var career = (Career) cbCareer.getSelectedItem();
//            try {
//                Student est = new Student(
//                        tNombre.getText(), tApellido.getText(),
//                        tCedula.getText(), career.code
//                );
//                sistema.agregar(est);
//                JOptionPane.showMessageDialog(this, "Estudiante registrado correctamente.");
//            } catch (Exception ex) {
//                JOptionPane.showMessageDialog(this, "Error en los datos.");
//            }
//        });
//
//        // --- Panel Listar ---
//        JPanel listar = new JPanel(new BorderLayout());
//        JTextArea area = new JTextArea();
//        JButton btnListar = new JButton("Actualizar Lista");
//        listar.add(new JScrollPane(area), BorderLayout.CENTER);
//        listar.add(btnListar, BorderLayout.SOUTH);
//
//        btnListar.addActionListener(e -> {
//            List<Student> lista = sistema.listar();
//            area.setText("");
//            for (Student est : lista) {
//                area.append(est.toString() + "\n");
//            }
//        });
//
//        // --- Subjects Panel ---
//        JPanel pnSubjet = new JPanel(new BorderLayout());
//        JTextArea taSubject = new JTextArea();
//        JButton btnSubject = new JButton("Actualizar Lista");
//        pnSubjet.add(new JScrollPane(taSubject), BorderLayout.CENTER);
//        pnSubjet.add(btnSubject, BorderLayout.SOUTH);
//
//        btnSubject.addActionListener(e -> {
//            taSubject.setText("");
//            for (var sub : subjects) {
//                taSubject.append(sub.getName() + " " + sub.getCode() + "\n");
//            }
//        });
//
//        // --- Subjects Panel ---
//        JPanel pnCareer = new JPanel(new BorderLayout());
//        JTextArea taCareer = new JTextArea();
//        JButton btnCareer = new JButton("Actualizar Lista");
//        pnCareer.add(new JScrollPane(taCareer), BorderLayout.CENTER);
//        pnCareer.add(btnCareer, BorderLayout.SOUTH);
//
//        btnCareer.addActionListener(e -> {
//            taCareer.setText("");
//            for (var car : careers) {
//                taCareer.append(car.getName() + " " + car.getCode() + "\n");
//            }
//        });
//
//        // --- Panel Buscar/Modificar ---
//        JPanel buscar = new JPanel(new GridLayout(5, 2, 5, 5));
//        JTextField tBuscar = new JTextField();
//        JButton btnBuscar = new JButton("Buscar");
//        JTextField tNuevoProm = new JTextField();
//        JButton btnModificar = new JButton("Actualizar Promedio");
//
//        buscar.add(new JLabel("Cédula:"));
//        buscar.add(tBuscar);
//        buscar.add(new JLabel("Nuevo Promedio:"));
//        buscar.add(tNuevoProm);
//        buscar.add(new JLabel(""));
//        buscar.add(btnBuscar);
//        buscar.add(new JLabel(""));
//        buscar.add(btnModificar);
//
//        btnBuscar.addActionListener(e -> {
//            Student e1 = sistema.buscar(tBuscar.getText());
//            if (e1 != null)
//                JOptionPane.showMessageDialog(this, e1.toString());
//            else
//                JOptionPane.showMessageDialog(this, "No encontrado.");
//        });
//
//        btnModificar.addActionListener(e -> {
//            Student e1 = sistema.buscar(tBuscar.getText());
//            if (e1 != null) {
/// /                e1.setPromedio(Double.parseDouble(tNuevoProm.getText()));
//                JOptionPane.showMessageDialog(this, "Promedio actualizado.");
//            } else {
//                JOptionPane.showMessageDialog(this, "Estudiante no encontrado.");
//            }
//        });
//
//        // --- Panel Agregar Promedios ---
//
//        JPanel pnAddProm = new JPanel(new GridLayout(5, 2, 5, 5));
//        JTextField txtSearch = new JTextField();
//        JButton btnSearch = new JButton("Buscar");
////        Student studentToAddNote = null;
//
//        var cbSubjects = new JComboBox<Subject>();
//        cbSubjects.setModel(new DefaultComboBoxModel<>());
//
//        btnSearch.addActionListener(_ -> {
//            Student e1 = sistema.buscar(tBuscar.getText());
//            if (e1 != null)
////var subjectsToCompo = new ArrayList<Subject>();
////                ((DefaultComboBoxModel)cbSubjects.getModel()).addAll(subjects.stream().filter());
//                JOptionPane.showMessageDialog(this, e1.toString());
//
//            else
//                JOptionPane.showMessageDialog(this, "No encontrado.");
//        });
//
//        JTextField tNewProm = new JTextField();
//        JButton btnEdit = new JButton("Actualizar Promedio");
//        pnAddProm.add(new JLabel("Cédula:"));
//        pnAddProm.add(txtSearch);
//        pnAddProm.add(new JLabel("Nuevo Promedio:"));
//        pnAddProm.add(tNewProm);
//        pnAddProm.add(new JLabel(""));
//        pnAddProm.add(btnSearch);
////
////
//
//
//        pnAddProm.add(new JLabel(""));
//        pnAddProm.add(btnEdit);
////
////        btnBuscar.addActionListener(e -> {
////            Student e1 = sistema.buscar(tBuscar.getText());
////            if (e1 != null)
////                JOptionPane.showMessageDialog(this, e1.toString());
////            else
////                JOptionPane.showMessageDialog(this, "No encontrado.");
////        });
////
////        btnModificar.addActionListener(e -> {
////            Student e1 = sistema.buscar(tBuscar.getText());
////            if (e1 != null) {
//////                e1.setPromedio(Double.parseDouble(tNuevoProm.getText()));
////                JOptionPane.showMessageDialog(this, "Promedio actualizado.");
////            } else {
////                JOptionPane.showMessageDialog(this, "Estudiante no encontrado.");
////            }
////        });
//
//        // --- Panel Eliminar ---
//        JPanel eliminar = new JPanel(new GridLayout(2, 2, 5, 5));
//        JTextField tEliminar = new JTextField();
//        JButton btnEliminar = new JButton("Eliminar");
//        eliminar.add(new JLabel("Cédula:"));
//        eliminar.add(tEliminar);
//        eliminar.add(new JLabel(""));
//        eliminar.add(btnEliminar);
//
//        btnEliminar.addActionListener(e -> {
//            boolean ok = sistema.eliminar(tEliminar.getText());
//            JOptionPane.showMessageDialog(this, ok ? "Eliminado correctamente." : "No se encontró el estudiante.");
//        });


    }
}

