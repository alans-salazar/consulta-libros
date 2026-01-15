package as.aluracursos.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "literalura")
public class Libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private String autor;
    private Integer idLibro;
    private String idioma;
    private Integer descargas;
    private Integer anioNacimiento;
    private Integer anioFallecimiento;

    public Integer getAnioFallecimiento() {
        return anioFallecimiento;
    }

    public void setAnioFallecimiento(Integer anioFallecimiento) {
        this.anioFallecimiento = anioFallecimiento;
    }

    public Integer getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(Integer anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Integer idLibro) {
        this.idLibro = idLibro;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    public Libros() {}
    public Libros(DatosGenerales datos) {
        this.titulo = datos.resultados().get(0).titulo();
        this.autor = datos.resultados().get(0).autores().get(0).nombreAutor();
        this.idLibro = datos.resultados().get(0).idLibro();
        this.idioma = datos.resultados().get(0).idiomas().get(0);
        this.descargas = datos.resultados().get(0).numeroDescargas();
        this.anioNacimiento = datos.resultados().get(0).autores().get(0).anioNacimiento();
        this.anioFallecimiento = datos.resultados().get(0).autores().get(0).anioFallecimiento();
    }

    @Override
public String toString() {
    return "Libros{" +
            "id=" + id +
            ", titulo='" + titulo + '\'' +
            ", autor='" + autor + '\'' +
            ", idLibro=" + idLibro +
            ", idioma='" + idioma + '\'' +
            ", descargas=" + descargas +
            ", anioNacimiento=" + anioNacimiento +
            ", anioFallecimiento=" + anioFallecimiento +
            '}';
    }

}
