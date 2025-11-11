package registroacademico.ui;

import registroacademico.Model.Career;
import registroacademico.Model.Student;
import registroacademico.Model.StudentEnrollment;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Vector;

public class ModifyStudent extends JPanel {
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtId;
    private JCheckBox cbActive;
    private JComboBox<Career> cbxCareer;
    private JButton btnSave;
    private JButton btnSearch;

    private final ArrayList<Career> careers;
    private final ArrayList<StudentEnrollment> studentEnrollments;
    private final ArrayList<Student> students;
    private Optional<Student> student = Optional.empty();
    private Optional<Career> career = Optional.empty();
    private Optional<StudentEnrollment> studentEnrollment = Optional.empty();


    public ModifyStudent(ArrayList<Career> careers, ArrayList<StudentEnrollment> studentEnrollments, ArrayList<Student> students) {
        this.careers = careers;
        this.studentEnrollments = studentEnrollments;
        this.students = students;
        init();
        btnSave.addActionListener(_ -> save());
        btnSearch.addActionListener(_ -> setStudent(Student.findByID(students, txtId.getText())));
        setEnabledFields(false);
    }

    public boolean setStudent(Optional<Student> student) {
        if (student.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontro el estudiante.");
            setEnabledFields(false);
            return false;
        }

        var studentEnrollment = StudentEnrollment.findByStudentId(studentEnrollments, student.get().getID());

        if (studentEnrollment.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontro la carrera del estudiante.");
            setEnabledFields(false);
            return false;
        }

        var career = Career.findByCode(careers, studentEnrollment.get().getCareerCode());

        if (career.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontro la carrera del estudiante.");
            setEnabledFields(false);
            return false;
        }

        this.student = student;
        this.career = career;
        this.studentEnrollment = studentEnrollment;
        txtFirstName.setText(student.get().getFirstName());
        txtLastName.setText(student.get().getLastName());
        txtId.setText(student.get().getID());
        cbActive.setSelected(student.get().isActive());
        cbxCareer.setSelectedItem(career);

        setEnabledFields(true);
        return true;
    }


    private void save() {
        if (studentEnrollment.isEmpty() || career.isEmpty() || student.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Datos no encontrados");
            return;
        }

        try {

            var st = student.get();
            var se = studentEnrollment.get();
            var cr = career.get();

            st.setFirstName(txtFirstName.getText());
            st.setLastName(txtLastName.getText());
            st.setID(txtId.getText());
            st.setActive(cbActive.isSelected());
            cr = (Career) cbxCareer.getSelectedItem();

            se.setStudentID(st.getID());
            se.setCareerCode(cr.getCode());

            JOptionPane.showMessageDialog(this, "Estudiante registrado correctamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en los datos.");
        }
    }

    private void setEnabledFields(boolean enabled) {
        txtFirstName.setEnabled(enabled);
        txtLastName.setEnabled(enabled);
        cbxCareer.setEnabled(enabled);
        btnSave.setEnabled(enabled);
        if (!enabled) reset();
    }

    private void reset() {
        txtFirstName.setText("");
        txtLastName.setText("");
        txtId.setText("");
        cbActive.setSelected(false);
        cbxCareer.setSelectedIndex(0);
    }


    private void init() {
        this.setLayout(new GridLayout(8, 2, 2, 2));
        txtLastName = new JTextField();
        txtFirstName = new JTextField();
        txtId = new JTextField();
        cbxCareer = new JComboBox<>();
        cbActive = new JCheckBox();

        cbxCareer.setModel(new DefaultComboBoxModel<>(new Vector<>(careers)));

        btnSave = new JButton("Guardar Cambios");
        btnSearch = new JButton("Buscar");

        this.add(new JLabel("Cédula:"));
        this.add(txtId);
        this.add(new JLabel(""));
        this.add(btnSearch);
        this.add(new JLabel("Nombre:"));
        this.add(txtFirstName);
        this.add(new JLabel("Apellido:"));
        this.add(txtLastName);
        this.add(new JLabel("Esta Activo:"));
        this.add(cbActive);
        this.add(new JLabel("Carrera:"));
        this.add(cbxCareer);
        this.add(new JLabel(""));
        this.add(btnSave);
    }

}
