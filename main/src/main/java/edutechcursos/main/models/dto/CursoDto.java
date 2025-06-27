package edutechcursos.main.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoDto {
    private String nombreCurso;
    private String descCurso;
    private String instructorCurso;
    private Boolean disponibilidadCurso;
}
