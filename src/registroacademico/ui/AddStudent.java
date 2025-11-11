package registroacademico.ui;

import registroacademico.Model.Career;
import registroacademico.Model.Student;
import registroacademico.Model.StudentEnrollment;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class AddStudent extends JPanel {
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtId;
    private JCheckBox cbActive;
    private JComboBox<Career> cbxCareer;
    private JButton btnSave;

    private final ArrayList<Career> careers;
    private final ArrayList<StudentEnrollment> studentEnrollments;
    private final ArrayList<Student> students;

    public AddStudent(ArrayList<Career> careers, ArrayList<StudentEnrollment> studentEnrollments, ArrayList<Student> students) {
        this.careers = careers;
        this.studentEnrollments = studentEnrollments;
        this.students = students;
        init();

        btnSave.addActionListener(_ -> {
            try {
                if (Student.findByID(students, txtId.getText()).isPresent()) {
                    JOptionPane.showMessageDialog(this, "Ya existe un estudiante con esa cedula.");
                    return;
                }

                var career = (Career) cbxCareer.getSelectedItem();
                var st = new Student(
                        txtFirstName.getText(), txtFirstName.getText(),
                        txtId.getText(), cbActive.isSelected()
                );

                studentEnrollments.add(new StudentEnrollment(st.getID(), career.getCode()));
                students.add(st);


                JOptionPane.showMessageDialog(this, "Estudiante registrado correctamente.");
                txtFirstName.setText("");
                txtLastName.setText("");
                txtId.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error en los datos.");
            }
        });
    }

    private void init() {
        this.setLayout(new GridLayout(6, 2, 2, 2));
        txtLastName = new JTextField();
        txtFirstName = new JTextField();
        txtId = new JTextField();
        cbxCareer = new JComboBox<>();
        cbActive = new JCheckBox();

        cbxCareer.setModel(new DefaultComboBoxModel<>(new Vector<>(careers)));

        btnSave = new JButton("Registrar");
        this.add(new JLabel("Nombre:"));
        this.add(txtFirstName);
        this.add(new JLabel("Apellido:"));
        this.add(txtLastName);
        this.add(new JLabel("Cédula:"));
        this.add(txtId);
        this.add(new JLabel("Esta Activo:"));
        this.add(cbActive);
        this.add(new JLabel("Carrera:"));
        this.add(cbxCareer);
        this.add(new JLabel(""));
        this.add(btnSave);
    }
}
