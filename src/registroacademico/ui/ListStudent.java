package registroacademico.ui;

import registroacademico.Model.Career;
import registroacademico.controller.CareerController;
import registroacademico.controller.StudentAndCareerController;
import registroacademico.controller.StudentController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Optional;
import java.util.function.Consumer;

public class ListStudent extends JPanel {
    private JButton btnRefresh;
    private JButton btnDelete;
    private JButton btnSearch;
    private JTable table;
    private DefaultTableModel model;

    private final StudentController studentController;
    private final CareerController careerController;
    private final StudentAndCareerController studentAndCareerController;


    public ListStudent(Consumer<String> modifyStudent, StudentController studentController, CareerController careerController, StudentAndCareerController studentAndCareerController) {
        this.studentController = studentController;
        this.careerController = careerController;
        this.studentAndCareerController = studentAndCareerController;

        init();

        loadModel();

        studentController.addEventListener(this::loadModel);

        // Ejemplo: botón guardar
        btnRefresh.addActionListener(_ -> loadModel());

        // Ejemplo: botón eliminar
        btnDelete.addActionListener(_ -> delete());

        btnSearch.addActionListener(_ -> {
            var id = getSelectedId();

            if (id.isPresent()) {
                modifyStudent.accept(id.get());
            }
        });
    }


    /**
     * Elimina el estudiante seleccionado y actualiza la tabla.
     */
    private void delete() {
        var id = getSelectedId();

        if (id.isPresent()) {
            studentController.remove(id.get());
            JOptionPane.showMessageDialog(this, "Estudiante eliminado correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "No se encontro el estudiante.");
        }
    }

    private Optional<String> getSelectedId() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un estudiante para eliminar.");
            return Optional.empty();
        }

        return Optional.of((String) model.getValueAt(row, 2));
    }

    /**
     * Llena la tabla con los datos de los estudiantes actuales.
     */
    private void loadModel() {
        model.setRowCount(0);

        var students = studentController.getAll();

        if (students.isEmpty()) {
            model.addRow(new Object[]{"(sin datos)", "", "", "", ""});
            return;
        }

        for (var student : students) {
            var studentAndCareer = studentAndCareerController.get(student.getID());

            Optional<Career> career = Optional.empty();

            if (studentAndCareer.isPresent()) {
                career = careerController.get(studentAndCareer.get().getCareerCode());
            }

            model.addRow(new Object[]{
                    student.getFirstName(),
                    student.getLastName(),
                    student.getID(),
                    student.isActive() ? "Activo" : "Inactivo",
                    career.isPresent() ? career.get().getName() : "No esta"
            });
        }

        model.fireTableDataChanged();
    }

    private void init() {
        this.setLayout(new BorderLayout());

        var columnNames = new String[]{"Nombre", "Apellido", "Cédula", "Estado", "Carrera"};

        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // bloquea edición directa
            }
        };

        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);


        btnRefresh = new JButton("Recargar");
        btnDelete = new JButton("Eliminar");
        btnSearch = new JButton("Modificar");

        var pnFooter = new JPanel(new GridLayout(3, 1));
        pnFooter.add(btnRefresh);
        pnFooter.add(btnSearch);
        pnFooter.add(btnDelete);
        add(pnFooter, BorderLayout.EAST);

    }

}
