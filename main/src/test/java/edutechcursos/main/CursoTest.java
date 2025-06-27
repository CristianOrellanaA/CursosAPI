package edutechcursos.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edutechcursos.main.models.Curso;
import edutechcursos.main.models.entity.CursoEntity;
import edutechcursos.main.repository.CursoRepository;
import edutechcursos.main.service.CursoService;

public class CursoTest {

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private CursoService cursoService;

    private Curso curso;
    private CursoEntity cursoEntity;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        curso = new Curso(1, "Java Básico", "JB101", "Curso introductorio a Java", "Pedro Pérez", true);
        cursoEntity = new CursoEntity();
        cursoEntity.setIdCurso(1);
        cursoEntity.setNombreCurso("Java Básico");
        cursoEntity.setCodigoCurso("JB101");
        cursoEntity.setDescCurso("Curso introductorio a Java");
        cursoEntity.setInstructorCurso("Pedro Pérez");
        cursoEntity.setDisponibilidadCurso(true);
    }

    @Test
    public void testCrearCurso_nuevo() {
        when(cursoRepository.existsByCodigoCurso(curso.getCodigoCurso())).thenReturn(false);
        when(cursoRepository.save(any(CursoEntity.class))).thenReturn(cursoEntity);

        String resultado = cursoService.crearCurso(curso);
        assertEquals("Curso creado correctamente", resultado);
    }

    @Test
    public void testCrearCurso_existente() {
        when(cursoRepository.existsByCodigoCurso(curso.getCodigoCurso())).thenReturn(true);

        String resultado = cursoService.crearCurso(curso);
        assertEquals("El curso ya existe", resultado);
    }

    @Test
    public void testObtenerCurso_existe() {
        when(cursoRepository.findByCodigoCurso("JB101")).thenReturn(cursoEntity);

        Curso resultado = cursoService.obtenerCurso("JB101");
        assertNotNull(resultado);
        assertEquals("Java Básico", resultado.getNombreCurso());
    }

    @Test
    public void testObtenerCurso_noExiste() {
        when(cursoRepository.findByCodigoCurso("NOEXISTE")).thenReturn(null);

        Curso resultado = cursoService.obtenerCurso("NOEXISTE");
        assertNull(resultado);
    }

    @Test
    public void testActualizarCurso_existe() {
        when(cursoRepository.findById(1)).thenReturn(Optional.of(cursoEntity));
        when(cursoRepository.save(any(CursoEntity.class))).thenReturn(cursoEntity);

        Curso actualizado = new Curso(1, "Java Avanzado", "JB101", "Curso avanzado de Java", "Pedro Pérez", true);
        boolean resultado = cursoService.actualizarCurso(1, actualizado);
        assertEquals(true, resultado);
    }

    @Test
    public void testActualizarCurso_noExiste() {
        when(cursoRepository.findById(999)).thenReturn(Optional.empty());

        Curso actualizado = new Curso(999, "Otro Curso", "OC001", "Descripción", "Juan Pérez", true);
        boolean resultado = cursoService.actualizarCurso(999, actualizado);
        assertEquals(false, resultado);
    }

    @Test
    public void testBorrarCurso_existe() {
        when(cursoRepository.existsById(1)).thenReturn(true);
        doNothing().when(cursoRepository).deleteById(1);

        boolean resultado = cursoService.borrarCurso(1);
        assertEquals(true, resultado);
    }

    @Test
    public void testBorrarCurso_noExiste() {
        when(cursoRepository.existsById(999)).thenReturn(false);

        boolean resultado = cursoService.borrarCurso(999);
        assertEquals(false, resultado);
    }

    @Test
    public void testObtenerCursos_listaVacia() {
        when(cursoRepository.findAll()).thenReturn(Collections.emptyList());

        assertEquals(0, cursoService.obtenerCursos().size());
    }
}
