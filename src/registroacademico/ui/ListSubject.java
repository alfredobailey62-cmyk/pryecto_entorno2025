package registroacademico.ui;

import registroacademico.Model.Career;
import registroacademico.controller.CareerAndSubjectController;
import registroacademico.controller.CareerController;
import registroacademico.controller.SubjectController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Optional;

public class ListSubject extends JPanel {
    private JTable table;
    private DefaultTableModel model;


    private final CareerController careerController;
    private final SubjectController subjectController;
    private final CareerAndSubjectController careerAndSubjectController;

    public ListSubject(CareerController careerController, SubjectController subjectController, CareerAndSubjectController careerAndSubjectController) {
        this.careerController = careerController;
        this.subjectController = subjectController;
        this.careerAndSubjectController = careerAndSubjectController;

        init();

    }


    private void init() {
        this.setLayout(new BorderLayout());

        // Columnas de la tabla
        var columnNames = new String[]{"Codigo", "Nombre", "Carrera"};

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

        loadModel();
    }

    /**
     * Llena la tabla con los datos de los estudiantes actuales.
     */
    private void loadModel() {
        model.setRowCount(0);

        var subjects = subjectController.getAll();

        if (subjects == null || subjects.isEmpty()) {
            model.addRow(new Object[]{"(sin datos)", "", "",});
            return;
        }

        for (var subject : subjects) {
            var enAndSub = careerAndSubjectController.getBySubjectCode(subject.getCode());

            Optional<Career> career = Optional.empty();

            if (enAndSub.isPresent()) {
                career = careerController.get(enAndSub.get().getCareerCode());
            }

            model.addRow(new Object[]{
                    subject.getCode(),
                    subject.getName(),
                    career.isPresent() ? career.get().getName() : "No esta",
            });
        }
    }
}
