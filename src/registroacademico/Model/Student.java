package registroacademico.Model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Optional;

/*
Clase de estudiante
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {
    String firstName;
    String lastName;
    String ID;
    boolean isActive;

    public static Optional<Student> findByID(List<Student> list, String ID) {
        return list.stream().filter(s -> s.getID().equals(ID)).findFirst();
    }
}

