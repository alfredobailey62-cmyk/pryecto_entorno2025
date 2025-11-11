package registroacademico.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

/*
Clase de estudiante
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String firstName;
    private String lastName;
    private String ID;
    private boolean isActive;

    public static Optional<Student> findByID(List<Student> list, String ID) {
        return list.stream().filter(s -> s.getID().equals(ID)).findFirst();
    }
}

