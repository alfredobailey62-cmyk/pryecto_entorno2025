package registroacademico.ui;

import registroacademico.Model.*;
import registroacademico.controller.*;
import registroacademico.dto.AcademicRecordDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
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
    private JTable tbAcademicRecord;
    private DefaultTableModel academicRecordModel;

    private final StudentController studentController;
    private final CareerController careerController;
    private final StudentAndCareerController studentAndCareerController;
    private final SubjectController subjectController;
    private final AcademicRecordController academicRecordController;
    private final CareerAndSubjectController careerAndSubjectController;

    private Optional<Student> student = Optional.empty();
    private Optional<Career> career = Optional.empty();
    private Optional<StudentAndCareer> studentEnrollment = Optional.empty();
    private ArrayList<CareerAndSubject> careerAndSubjects = new ArrayList<>();
    private ArrayList<Subject> subjects = new ArrayList<>();
    private ArrayList<AcademicRecord> records = new ArrayList<>();

    private ArrayList<AcademicRecordDTO> recordsDTO = new ArrayList<>();
    private JComboBox<AcademicRecordDTO> cbxCareerDTO;
    private DefaultComboBoxModel<AcademicRecordDTO> cbxCareerDTOModel;


    public ModifyStudent(StudentController studentController, CareerController careerController, StudentAndCareerController studentAndCareerController, SubjectController subjectController, AcademicRecordController academicRecordController, CareerAndSubjectController careerAndSubjectController) {
        this.studentController = studentController;
        this.careerController = careerController;
        this.studentAndCareerController = studentAndCareerController;
        this.subjectController = subjectController;
        this.academicRecordController = academicRecordController;
        this.careerAndSubjectController = careerAndSubjectController;

        init();

        //carga la carreras en el combobox
        loadCbxModel();

        loadModel();

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


        careerAndSubjects.clear();
        careerAndSubjects.addAll(careerAndSubjectController.getAllByCareerCode(career.get().getCode()));

        IO.println(careerAndSubjects);

        subjects.clear();
        subjects.addAll(careerAndSubjects.stream().map(careerAndSubject -> subjectController.getMapOfModel().get(careerAndSubject.getSubjectCode())).toList());

        IO.println(subjects);

        records.clear();
        records.addAll(academicRecordController.getAll(student.getID()));

        IO.println(records);

        if (records.isEmpty()) {
            IO.println("Nuevo edit");
            records.addAll(subjects.stream().map(subject -> new AcademicRecord(student.getID(), subject.getCode(), 0)).toList());
        }

        loadModel();

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

            academicRecordController.remove(st.getID());

            academicRecordController.addAll(records);

            studentController.fireChange();
            studentAndCareerController.fireChange();
            careerController.fireChange();

            prepareToEdit(st);

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
        loadModel();
        if (cbxCareerModel.getSize() > 0)
            cbxCareer.setSelectedIndex(0);
    }

    private void loadCbxModel() {
        cbxCareerModel.removeAllElements();
        cbxCareerModel.addAll(careerController.getAll());
    }

    /**
     * Llena la tabla con los datos de los estudiantes actuales.
     */
    private void loadModel() {
        academicRecordModel.setRowCount(0);

        if (records.isEmpty()) {
            academicRecordModel.addRow(new Object[]{"(sin datos)", "", ""});
            IO.println("Es vacio");
            academicRecordModel.fireTableDataChanged();
            return;
        }

        var total = 0;

        for (var record : records) {
            var optionalSubject = subjectController.get(record.getSubjectCode());

            if (optionalSubject.isEmpty()) {
                academicRecordController.remove(record.getStudentID());
                break;
            }

            var subject = optionalSubject.get();

            total += record.getAverage();

            academicRecordModel.addRow(new Object[]{
                    subject.getCode(),
                    subject.getName(),
                    record.getAverage(),
            });
        }

        var average = total / records.size();

        var index = ((double) average / 100) * 3.00;


        academicRecordModel.addRow(new Object[]{
                "",
                "Indice: ",
                String.format("%.2f", index),
        });

        academicRecordModel.fireTableDataChanged();
    }


    private void init() {
        this.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        this.setLayout(new GridLayout(2, 1, 2, 4));
        txtLastName = new JTextField();
        txtFirstName = new JTextField();
        txtId = new JTextField();
        cbxCareerModel = new DefaultComboBoxModel<>();
        cbxCareer = new JComboBox<>(cbxCareerModel);
        cbActive = new JCheckBox();

        btnSave = new JButton("Guardar Cambios");
        btnSearch = new JButton("Buscar");
        btnRevert = new JButton("Revertir");

        var pnEditForm = new JPanel(new GridLayout(8, 2, 2, 4));
        pnEditForm.setBorder(BorderFactory.createTitledBorder("Editar Estudiante"));

        pnEditForm.add(new JLabel("Cédula:"));
        pnEditForm.add(txtId);
        pnEditForm.add(new JLabel(""));
        pnEditForm.add(btnSearch);
        pnEditForm.add(new JLabel("Nombre:"));
        pnEditForm.add(txtFirstName);
        pnEditForm.add(new JLabel("Apellido:"));
        pnEditForm.add(txtLastName);
        pnEditForm.add(new JLabel("Esta Activo:"));
        pnEditForm.add(cbActive);
        pnEditForm.add(new JLabel("Carrera:"));
        pnEditForm.add(cbxCareer);
        pnEditForm.add(btnRevert);
        pnEditForm.add(btnSave);
        this.add(pnEditForm);

//        var pnAcademicRecord = new JPanel();
//        pnAcademicRecord.setBorder(BorderFactory.createTitledBorder("Asignar notas"));
//        pnAcademicRecord.setLayout(new GridLayout(2, 2, 2, 2));
//
//        pnAcademicRecord.add(new JLabel("z"));
//        pnAcademicRecord.add(new JLabel("x"));
//        pnAcademicRecord.add(new JLabel("c"));
//        pnAcademicRecord.add(new JLabel("a"));
//
//        this.add(pnAcademicRecord);

        var columnNames = new String[]{"Código", "Materia", "Promedio"};

        academicRecordModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 2 && row != (getRowCount() - 1);
            }

            @Override
            public void setValueAt(Object aValue, int row, int column) {
                super.setValueAt(aValue, row, column);

                // Solo columna promedio (2), y evitar la fila TOTAL
                if (column == 2 && row < records.size()) {

                    try {
                        int nuevoPromedio = Integer.parseInt(aValue.toString());

                        if (nuevoPromedio < 0 || nuevoPromedio > 100) {
                            JOptionPane.showMessageDialog(null, "La nota debe estar entre 0 y 100");
                            return;
                        }

                        // Sincronizar con tu lista de AcademicRecord
                        records.get(row).setAverage(nuevoPromedio);

                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Debe ser un número entero.");
                    }
                }
            }
        };


        tbAcademicRecord = new JTable(academicRecordModel);
        tbAcademicRecord.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(tbAcademicRecord);
        this.add(scrollPane);
    }

}
