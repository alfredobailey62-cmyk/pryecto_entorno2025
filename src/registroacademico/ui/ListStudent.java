package registroacademico.ui;

import registroacademico.Model.Career;
import registroacademico.Model.Student;
import registroacademico.Model.StudentEnrollment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Consumer;

public class ListStudent extends JPanel implements UIBase {
    private JButton btnRefresh;
    private JButton btnDelete;
    private JButton btnSearch;
    private JTable table;
    private DefaultTableModel model;


    private final ArrayList<Career> careers;
    private final ArrayList<StudentEnrollment> studentEnrollments;
    private final ArrayList<Student> students;


    public ListStudent(ArrayList<Career> careers, ArrayList<StudentEnrollment> studentEnrollments, ArrayList<Student> students, Consumer<Optional<Student>> modifyStudent) {
        this.careers = careers;
        this.studentEnrollments = studentEnrollments;
        this.students = students;
        init();

        // Ejemplo: botón guardar
        btnRefresh.addActionListener(_ -> refresh());

        // Ejemplo: botón eliminar
        btnDelete.addActionListener(_ -> delete());

        btnSearch.addActionListener(_ -> modifyStudent.accept(Student.findByID(students, getSelectedId())));
    }


    private void init() {
        this.setLayout(new BorderLayout());

        // Columnas de la tabla
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

        // Botones
        btnRefresh = new JButton("Recargar");
        btnDelete = new JButton("Eliminar");
        btnSearch = new JButton("Modificar");

        var pnFooter = new JPanel(new GridLayout(3, 1));
        pnFooter.add(btnRefresh);
        pnFooter.add(btnSearch);
        pnFooter.add(btnDelete);
        add(pnFooter, BorderLayout.EAST);

        // Cargar los estudiantes al iniciar
        loadModel();
    }

    /**
     * Recarga toda la tabla desde la lista actual de estudiantes.
     */
    @Override
    public void refresh() {
        loadModel();
        model.fireTableDataChanged(); // notifica que el modelo cambió
    }

    /**
     * Elimina el estudiante seleccionado y actualiza la tabla.
     */
    private void delete() {
        var id = getSelectedId();
        if (id == null) return;
        students.removeIf(s -> s.getID().equals(id));
        JOptionPane.showMessageDialog(this, "Estudiante eliminado correctamente.");
        refresh();
    }

    private String getSelectedId() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un estudiante para eliminar.");
            return null;
        }

        return (String) model.getValueAt(row, 2);
    }

    /**
     * Llena la tabla con los datos de los estudiantes actuales.
     */
    private void loadModel() {
        model.setRowCount(0); // limpia filas

        if (students == null || students.isEmpty()) {
            model.addRow(new Object[]{"(sin datos)", "", "", "", ""});
            return;
        }

        for (Student s : students) {
            Optional<StudentEnrollment> se = StudentEnrollment.findByStudentId(studentEnrollments, s.getID());

            Optional<Career> career = Optional.empty();

            if (se.isPresent()) {
                career = Career.findByCode(careers, se.get().getCareerCode());
            }

            model.addRow(new Object[]{
                    s.getFirstName(),
                    s.getLastName(),
                    s.getID(),
                    s.isActive() ? "Activo" : "Inactivo",
                    career.isPresent() ? career.get().getName() : "No esta"
            });
        }
    }
}
