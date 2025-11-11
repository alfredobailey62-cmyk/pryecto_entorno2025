package registroacademico.ui;

import registroacademico.Model.Career;
import registroacademico.Model.Student;
import registroacademico.Model.StudentEnrollment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ListStudent extends JPanel {
    private JButton btnSave;
    private JTable table;
    private DefaultTableModel model;

    private ArrayList<Career> careers;
    private ArrayList<StudentEnrollment> studentEnrollments;
    private ArrayList<Student> students;

    public ListStudent(ArrayList<Career> careers, ArrayList<StudentEnrollment> studentEnrollments, ArrayList<Student> students) {
        this.careers = careers;
        this.studentEnrollments = studentEnrollments;
        this.students = students;
        init();
        btnSave.addActionListener(_ -> {
            JOptionPane.showMessageDialog(this, "Datos guardados correctamente (ejemplo).");
        });
    }

    private void init() {
        this.setLayout(new BorderLayout());

        // Columnas de la tabla
        var columnNames = new String[]{
                "Nombre",
                "Apellido",
                "Cédula",
                "Carrera"
        };

        // Modelo de la tabla
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

        // Agregar tabla dentro de un JScrollPane (para que se pueda hacer scroll)
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane, BorderLayout.CENTER);

        // Botón inferior
        btnSave = new JButton("Registrar");
        this.add(btnSave, BorderLayout.SOUTH);

        // Llenar la tabla con los estudiantes
        cargarEstudiantes();
    }

    /**
     * Llena la tabla con los datos de los estudiantes actuales.
     */
    private void cargarEstudiantes() {
        // Limpia la tabla antes de llenar
        model.setRowCount(0);

        if (students == null || students.isEmpty()) {
            model.addRow(new Object[]{"(sin datos)", "", ""});
            return;
        }


        // Agrega cada estudiante como fila
        for (Student s : students) {

            int code = -1;

            for (var e : studentEnrollments) {
                if (e.getStudentID().equals(s.getID())) {
                    code = e.getCareerCode();
                }
            }


            Career career = null;
            if (code != -1)
                for (var e : careers) {
                    if (e.getCode() == code) {
                        career = e;
                    }
                }
            model.addRow(new Object[]{
                    s.getFirstName(),
                    s.getLastName(),
                    s.getID(),
                    career != null ? career.getName() : "no hay",
            });
        }
    }
}
