package edutechcursos.main.models;

public class Curso {
    private Integer idCurso;
    private String nombreCurso;
    private String codigoCurso;
    private String descCurso;
    private String instructorCurso;
    private Boolean disponibilidadCurso;
        
    public Curso() {
    }

    public Curso(Integer idCurso, String nombreCurso, String codigoCurso, String descCurso, String instructorCurso,
            Boolean disponibilidadCurso) {
        this.idCurso = idCurso;
        this.nombreCurso = nombreCurso;
        this.codigoCurso = codigoCurso;
        this.descCurso = descCurso;
        this.instructorCurso = instructorCurso;
        this.disponibilidadCurso = disponibilidadCurso;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(String codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public String getDescCurso() {
        return descCurso;
    }

    public void setDescCurso(String descCurso) {
        this.descCurso = descCurso;
    }

    public String getInstructorCurso() {
        return instructorCurso;
    }

    public void setInstructorCurso(String instructorCurso) {
        this.instructorCurso = instructorCurso;
    }

    public Boolean getDisponibilidadCurso() {
        return disponibilidadCurso;
    }

    public void setDisponibilidadCurso(Boolean disponibilidadCurso) {
        this.disponibilidadCurso = disponibilidadCurso;
    }

    @Override
    public String toString() {
        return "Curso [idCurso=" + idCurso + ", nombreCurso=" + nombreCurso + ", codigoCurso=" + codigoCurso
                + ", descCurso=" + descCurso + ", instructorCurso=" + instructorCurso + ", disponibilidadCurso="
                + disponibilidadCurso + "]";
    }
    
    
    
}
