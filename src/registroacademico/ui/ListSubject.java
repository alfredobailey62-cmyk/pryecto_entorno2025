package registroacademico.ui;

import registroacademico.Model.Career;
import registroacademico.Model.EnrollmentAndSubject;
import registroacademico.Model.Subject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;

public class ListSubject extends JPanel {
    private JTable table;
    private DefaultTableModel model;


    private final ArrayList<Subject> subjects;
    private final ArrayList<Career> careers;
    private final ArrayList<EnrollmentAndSubject> enrollmentAndSubjects;

    public ListSubject(ArrayList<Subject> subjects, ArrayList<Career> careers, ArrayList<EnrollmentAndSubject> enrollmentAndSubjects) {
        this.subjects = subjects;
        this.careers = careers;
        this.enrollmentAndSubjects = enrollmentAndSubjects;
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

        if (subjects == null || subjects.isEmpty()) {
            model.addRow(new Object[]{"(sin datos)", "", "",});
            return;
        }

        for (var c : subjects) {
            var enAndSub = EnrollmentAndSubject.findBySubjectCode(enrollmentAndSubjects, c.getCode());
            Optional<Career> career = Optional.empty();
            if (enAndSub.isPresent()) {
                career = Career.findByCode(careers, enAndSub.get().getCareerCode());
            }

            model.addRow(new Object[]{
                    c.getCode(),
                    c.getName(),
                    career.isPresent() ? career.get().getName() : "No esta",
            });
        }
    }
}
