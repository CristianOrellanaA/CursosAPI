package edutechcursos.main.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class CursoEntity {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer idCurso;

    @Column(name ="nombre")
    private String nombreCurso;
    private String codigoCurso;
    private String descCurso;
    private String instructorCurso;
    private Boolean disponibilidadCurso;
    
}
