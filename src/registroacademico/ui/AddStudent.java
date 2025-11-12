package registroacademico.ui;

import registroacademico.Model.Career;
import registroacademico.Model.Student;
import registroacademico.Model.StudentAndCareer;
import registroacademico.controller.CareerController;
import registroacademico.controller.StudentAndCareerController;
import registroacademico.controller.StudentController;

import javax.swing.*;
import java.awt.*;

public class AddStudent extends JPanel {
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtId;
    private JCheckBox cbActive;
    private JComboBox<Career> cbxCareer;
    private DefaultComboBoxModel<Career> cbxCareerModel;
    private JButton btnSave;

    private final StudentController studentController;
    private final CareerController careerController;
    private final StudentAndCareerController studentAndCareerController;

    public AddStudent(StudentController studentController, CareerController careerController, StudentAndCareerController studentAndCareerController) {
        this.studentController = studentController;
        this.careerController = careerController;
        this.studentAndCareerController = studentAndCareerController;

        init();

        // Carga la carreras en el combobox
        loadCbxModel();

        // Para que cuando alla algun cambio en las careras, automaticamente se actualize el comobox
        careerController.addEventListener(this::loadCbxModel);

        btnSave.addActionListener(_ -> saveStudent());
    }

    private void saveStudent() {
        try {
            if (studentController.exists(txtId.getText())) {
                JOptionPane.showMessageDialog(this, "Ya existe un estudiante con esa cedula.");
                return;
            }

            var career = (Career) cbxCareer.getSelectedItem();

            var newStudent = new Student(
                    txtFirstName.getText(), txtFirstName.getText(),
                    txtId.getText(), cbActive.isSelected()
            );

            studentAndCareerController.add(new StudentAndCareer(newStudent.getID(), career.getCode()));
            studentController.add(newStudent);

            JOptionPane.showMessageDialog(this, "Estudiante registrado correctamente.");

            txtFirstName.setText("");
            txtLastName.setText("");
            txtId.setText("");

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error en los datos.");
        }
    }

    private void loadCbxModel() {
        cbxCareerModel.removeAllElements();
        cbxCareerModel.addAll(careerController.getAll());
    }

    private void init() {
        this.setLayout(new GridLayout(6, 2, 2, 2));
        txtLastName = new JTextField();
        txtFirstName = new JTextField();
        txtId = new JTextField();
        cbxCareerModel = new DefaultComboBoxModel<>();
        cbxCareer = new JComboBox<>(cbxCareerModel);
        cbActive = new JCheckBox();


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
