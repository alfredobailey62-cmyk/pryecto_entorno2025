package registroacademico.ui;

import registroacademico.Model.Career;
import registroacademico.Model.Student;
import registroacademico.Model.StudentAndCareer;
import registroacademico.controller.CareerController;
import registroacademico.controller.StudentAndCareerController;
import registroacademico.controller.StudentController;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class ModifyStudent extends JPanel {
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtId;
    private JCheckBox cbActive;
    private JComboBox<Career> cbxCareer;
    private DefaultComboBoxModel<Career> cbxCareerModel;
    private JButton btnSave;
    private JButton btnSearch;
    private JButton btnRevert;

    private final StudentController studentController;
    private final CareerController careerController;
    private final StudentAndCareerController studentAndCareerController;

    private Optional<Student> student = Optional.empty();
    private Optional<Career> career = Optional.empty();
    private Optional<StudentAndCareer> studentEnrollment = Optional.empty();


    public ModifyStudent(StudentController studentController, CareerController careerController, StudentAndCareerController studentAndCareerController) {
        this.studentController = studentController;
        this.careerController = careerController;
        this.studentAndCareerController = studentAndCareerController;

        init();

        //carga la carreras en el combobox
        loadCbxModel();

        //Para que cuando alla algun cambio en las careras, automaticamente se actualize el comobox
        careerController.addEventListener(this::loadCbxModel);

        btnSave.addActionListener(_ -> save());

        btnSearch.addActionListener(_ -> setStudent(txtId.getText()));

        btnRevert.addActionListener(_ -> setDataToField());

        setEnabledFields(false);
    }

    public boolean setStudent(String id) {
        var optionalId = studentController.get(id);

        if (optionalId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontro el estudiante.");
            setEnabledFields(false);
            return false;
        }

        return prepareToEdit(optionalId.get());
    }

    public boolean prepareToEdit(Student student) {

        var studentEnrollment = studentAndCareerController.get(student.getID());

        if (studentEnrollment.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontro la carrera del estudiante.");
            setEnabledFields(false);
            return false;
        }

        var career = careerController.get(studentEnrollment.get().getCareerCode());

        if (career.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontro la carrera del estudiante.");
            setEnabledFields(false);
            return false;
        }

        this.student = Optional.of(student);
        this.career = career;
        this.studentEnrollment = studentEnrollment;

        setDataToField();
        setEnabledFields(true);
        return true;
    }

    private void setDataToField() {
        txtFirstName.setText(student.get().getFirstName());
        txtLastName.setText(student.get().getLastName());
        txtId.setText(student.get().getID());
        cbActive.setSelected(student.get().isActive());
        cbxCareer.setSelectedItem(career.get());
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
            st.setActive(cbActive.isSelected());
            cr = (Career) cbxCareer.getSelectedItem();

            se.setCareerCode(cr.getCode());

            studentController.fireChange();
            studentAndCareerController.fireChange();
            careerController.fireChange();

            JOptionPane.showMessageDialog(this, "Datos registrado correctamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en los datos.");
        }
    }

    private void setEnabledFields(boolean enabled) {
        txtFirstName.setEnabled(enabled);
        txtLastName.setEnabled(enabled);
        cbxCareer.setEnabled(enabled);
        btnSave.setEnabled(enabled);
        btnRevert.setEnabled(enabled);
        if (!enabled) reset();
    }

    private void reset() {
        student = Optional.empty();
        career = Optional.empty();
        studentEnrollment = Optional.empty();
        txtFirstName.setText("");
        txtLastName.setText("");
        txtId.setText("");
        cbActive.setSelected(false);
        cbxCareer.setSelectedIndex(0);
    }

    private void loadCbxModel() {
        cbxCareerModel.removeAllElements();
        cbxCareerModel.addAll(careerController.getAll());
    }


    private void init() {
        this.setLayout(new GridLayout(8, 2, 2, 4));
        txtLastName = new JTextField();
        txtFirstName = new JTextField();
        txtId = new JTextField();
        cbxCareerModel = new DefaultComboBoxModel<>();
        cbxCareer = new JComboBox<>(cbxCareerModel);
        cbActive = new JCheckBox();

        btnSave = new JButton("Guardar Cambios");
        btnSearch = new JButton("Buscar");
        btnRevert = new JButton("Revertir");

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
        this.add(btnRevert);
        this.add(btnSave);
    }

}
