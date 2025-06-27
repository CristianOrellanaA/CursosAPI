package edutechcursos.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edutechcursos.main.models.entity.CursoEntity;


@Repository
public interface CursoRepository extends JpaRepository<CursoEntity, Integer>{

    CursoEntity findByCodigoCurso(String codigoCurso);
    Boolean existsByCodigoCurso(String codigoCurso);
    void deleteByCodigoCurso(String codigoCurso);
    CursoEntity findByIdCurso(Integer idCurso);
        
}
