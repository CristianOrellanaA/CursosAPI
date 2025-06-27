package edutechcursos.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edutechcursos.main.models.Curso;
import edutechcursos.main.models.dto.CursoDto;
import edutechcursos.main.service.CursoService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/cursos")
public class CursosController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<Curso>> listarCursos() {
        return ResponseEntity.ok(cursoService.obtenerCursos());
    }

    @GetMapping("/{codigoCurso}")
    public ResponseEntity<Curso> obtenerCursoPorCodigo(@PathVariable String codigoCurso) {
        Curso curso = cursoService.obtenerCurso(codigoCurso);
        if (curso != null) {
            return ResponseEntity.ok(curso);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/dto/id/{idCurso}")
    public ResponseEntity<CursoDto> obtenerCursoDtoPorId(@PathVariable Integer idCurso) {
        CursoDto dto = cursoService.obtenerCursoDto(idCurso);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/dto/codigo/{codigoCurso}")
    public ResponseEntity<CursoDto> obtenerCursoDtoPorCodigo(@PathVariable String codigoCurso) {
        return cursoService.obtenerCursoDto(codigoCurso);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo curso")
    public ResponseEntity<String> crearCurso(@RequestBody Curso curso) {
        String resultado = cursoService.crearCurso(curso);
        if ("Curso creado correctamente".equals(resultado)) {
            return ResponseEntity.ok(resultado);
        } else if ("El curso ya existe".equals(resultado)) {
            return ResponseEntity.badRequest().body(resultado);
        } else {
            return ResponseEntity.status(500).body(resultado);
        }
    }

    @PutMapping("/{idCurso}")
    public ResponseEntity<String> actualizarCurso(@PathVariable Integer idCurso, @RequestBody Curso cursoActualizado) {
        boolean actualizado = cursoService.actualizarCurso(idCurso, cursoActualizado);
        if (actualizado) {
            return ResponseEntity.ok("Curso actualizado correctamente");
        }
        return ResponseEntity.status(404).body("Curso no encontrado");
    }

    @DeleteMapping("/{idCurso}")
    public ResponseEntity<String> borrarCurso(@PathVariable Integer idCurso) {
        boolean borrado = cursoService.borrarCurso(idCurso);
        if (borrado) {
            return ResponseEntity.ok("Curso borrado correctamente");
        }
        return ResponseEntity.status(404).body("Curso no encontrado");
    }
}
