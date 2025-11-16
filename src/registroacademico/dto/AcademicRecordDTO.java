package registroacademico.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcademicRecordDTO {
    private String name;
    private int code;
    private int average;

    @Override
    public String toString() {
        return name;
    }
}
