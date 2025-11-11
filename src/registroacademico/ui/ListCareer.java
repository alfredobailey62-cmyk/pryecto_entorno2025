package registroacademico.ui;

import registroacademico.Model.Career;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ListCareer extends JPanel {
    private JButton btnRefresh;
    private JButton btnDelete;
    private JButton btnSearch;
    private JTable table;
    private DefaultTableModel model;


    private final ArrayList<Career> careers;

    public ListCareer(ArrayList<Career> careers) {
        this.careers = careers;
        init();
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

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        loadModel();
    }

    /**
     * Llena la tabla con los datos de los estudiantes actuales.
     */
    private void loadModel() {
        model.setRowCount(0);

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
