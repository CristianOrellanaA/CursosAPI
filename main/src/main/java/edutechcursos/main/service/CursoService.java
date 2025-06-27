package edutechcursos.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import edutechcursos.main.models.Curso;
import edutechcursos.main.models.dto.CursoDto;
import edutechcursos.main.models.entity.CursoEntity;
import edutechcursos.main.repository.CursoRepository;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    private final List<Curso> cursos = new ArrayList<>();
    
    public CursoService(){
        cursos.add(new Curso(1, "Manipulacion de alimentos", "MDA1", "Curso enfocado al desarrollo de profesionales de la cocina", "Manuel Rodriguez", true));
    }

    public String crearCurso(Curso curso){
        try{
            Boolean estado = cursoRepository.existsByCodigoCurso(curso.getCodigoCurso());
            if(!estado){
                CursoEntity cursoNuevo = new CursoEntity();
                cursoNuevo.setIdCurso(curso.getIdCurso());
                cursoNuevo.setNombreCurso(curso.getNombreCurso());
                cursoNuevo.setCodigoCurso(curso.getCodigoCurso());
                cursoNuevo.setDescCurso(curso.getDescCurso());
                cursoNuevo.setInstructorCurso(curso.getInstructorCurso());
                cursoNuevo.setDisponibilidadCurso(curso.getDisponibilidadCurso());
                cursoRepository.save(cursoNuevo);
                return "Curso creado correctamente";
            }
            return "El curso ya existe";
        }catch (Exception e){
            e.printStackTrace();
            return "Error al crear Curso" + e.getMessage();
        }
    }


public List<Curso> obtenerCursos() {
    List<CursoEntity> cursosEntity = cursoRepository.findAll();
    List<Curso> cursosBD = new ArrayList<>();

    for (CursoEntity cursoEntity : cursosEntity) {
        cursosBD.add(new Curso(
            cursoEntity.getIdCurso(),
            cursoEntity.getNombreCurso(),
            cursoEntity.getCodigoCurso(),
            cursoEntity.getDescCurso(),
            cursoEntity.getInstructorCurso(),
            cursoEntity.getDisponibilidadCurso()
        ));
    }
    return cursosBD;
}


    public boolean borrarCurso(Integer id){
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Curso obtenerCurso(String codigo) {
    try {
        CursoEntity cursoEntity = cursoRepository.findByCodigoCurso(codigo);
        if (cursoEntity != null) {
            return new Curso(
                cursoEntity.getIdCurso(),
                cursoEntity.getNombreCurso(),
                cursoEntity.getCodigoCurso(),
                cursoEntity.getDescCurso(),
                cursoEntity.getInstructorCurso(),
                cursoEntity.getDisponibilidadCurso()
            );
        }
        return null;
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

    public boolean actualizarCurso(Integer idCurso, Curso cursoActualizado) {
    Optional<CursoEntity> cursoExistente = cursoRepository.findById(idCurso);
    if (cursoExistente.isPresent()) {
        CursoEntity curso = cursoExistente.get();
        curso.setNombreCurso(cursoActualizado.getNombreCurso());
        curso.setCodigoCurso(cursoActualizado.getCodigoCurso());
        curso.setDescCurso(cursoActualizado.getDescCurso());
        curso.setInstructorCurso(cursoActualizado.getInstructorCurso());
        curso.setDisponibilidadCurso(cursoActualizado.getDisponibilidadCurso());
        cursoRepository.save(curso);
        return true;
    }
    return false;
}

public CursoDto obtenerCursoDto(Integer idCurso){
    try{
        CursoEntity curso = cursoRepository.findByIdCurso(idCurso);
        CursoDto nuevoCurso = new CursoDto(
            curso.getNombreCurso(),
            curso.getDescCurso(),
            curso.getInstructorCurso(),
            curso.getDisponibilidadCurso()
        );
        return nuevoCurso;
    }catch(Exception e){
        return null;
    }
}

public ResponseEntity<CursoDto> obtenerCursoDto(@PathVariable String codigoCurso){
    Boolean estado = cursoRepository.existsByCodigoCurso(codigoCurso);
    if(estado){
        CursoEntity nuevoCurso = cursoRepository.findByCodigoCurso(codigoCurso);
        CursoDto cursoResponse = new CursoDto(
            nuevoCurso.getNombreCurso(),
            nuevoCurso.getDescCurso(),
            nuevoCurso.getInstructorCurso(),
            nuevoCurso.getDisponibilidadCurso()
        );
        return ResponseEntity.ok(cursoResponse);
    }
    return ResponseEntity.notFound().build();
}
}
