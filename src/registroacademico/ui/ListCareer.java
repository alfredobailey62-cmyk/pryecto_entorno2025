package registroacademico.ui;

import registroacademico.controller.CareerController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ListCareer extends JPanel {
    private JTable table;
    private DefaultTableModel model;

    private final CareerController careerController;

    public ListCareer(CareerController careerController) {
        this.careerController = careerController;

        init();

        loadModel();

        careerController.addEventListener(this::loadModel);
    }


    private void init() {
        this.setLayout(new BorderLayout());

        // Columnas de la tabla
        var columnNames = new String[]{"Codigo", "Nombre", "Facultad"};

        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // bloquea edición directa
            }
        };

        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        var scrollPane = new JScrollPane(table);

        this.add(scrollPane, BorderLayout.CENTER);

    }

    /**
     * Llena la tabla con los datos de los estudiantes actuales.
     */
    private void loadModel() {
        model.setRowCount(0);

        var careers = careerController.getAll();

        if (careers == null || careers.isEmpty()) {
            model.addRow(new Object[]{"(sin datos)", "", "",});
            return;
        }

        for (var c : careers) {
            model.addRow(new Object[]{
                    c.getCode(),
                    c.getName(),
                    c.getFaculty().getTitle(),
            });
        }
    }
}
