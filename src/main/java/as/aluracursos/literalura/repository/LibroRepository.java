package as.aluracursos.literalura.repository;

import as.aluracursos.literalura.model.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libros,Long> {

    List<Libros> findByAutorContainsIgnoreCase(String nombreAutor);

    @Query("SELECT l FROM Libros l WHERE l.anioNacimiento <= :anio AND (l.anioFallecimiento > :anio OR l.anioFallecimiento IS NULL)")
    List<Libros> autoresVivosEnDeterminadoAnio(int anio);

    @Query("SELECT l FROM Libros l WHERE l.idioma = :idioma")
    List<Libros> buscarLibrosPorIdioma(String idioma);
}
