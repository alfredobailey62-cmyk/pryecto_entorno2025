package registroacademico;

import registroacademico.controller.*;
import registroacademico.ui.*;

import javax.swing.*;
import java.util.function.Consumer;

public class Dashboard extends JFrame {

    public Dashboard() {
        setTitle("Sistema Académico - Registro de Estudiantes");
        setSize(650, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Barra de navegacion
        JTabbedPane tabs = new JTabbedPane();

        //Crear controladores y optener los datos de los .txt
        var studentController = new StudentController();
        var careerController = new CareerController();
        var subjectController = new SubjectController();
        var careerAndSubjectController = new CareerAndSubjectController();
        var academicRecordController = new AcademicRecordController();
        var studentAndCareerController = new StudentAndCareerController();


        //al cerrarse el programa, actualiza los archivos con los cambios
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            studentController.save();
            careerController.save();
            subjectController.save();
            academicRecordController.save();
            careerAndSubjectController.save();
            studentAndCareerController.save();
        }));

        // Creacion de JPanels
        var addStudent = new AddStudent(studentController, careerController, studentAndCareerController);
        var modifyStudent = new ModifyStudent(studentController, careerController, studentAndCareerController);

        Consumer<String> findAndModify = (id) -> {
            if (modifyStudent.setStudent(id))
                tabs.setSelectedComponent(modifyStudent);
        };

        var listStudent = new ListStudent(findAndModify, studentController, careerController, studentAndCareerController);
        var listCareer = new ListCareer(careerController);
        var listSubject = new ListSubject(careerController, subjectController, careerAndSubjectController);

        // Agrega paneles a el menu
        tabs.add("Registrar", addStudent);
        tabs.add("Listar", listStudent);
        tabs.add("Buscar/Modificar", modifyStudent);
        tabs.add("Carreras", listCareer);
        tabs.add("Materias", listSubject);

        // Agrega el menu al JFrame
        this.add(tabs);
    }
}

