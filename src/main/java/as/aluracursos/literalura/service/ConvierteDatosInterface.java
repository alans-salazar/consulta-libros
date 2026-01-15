package as.aluracursos.literalura.service;

public interface ConvierteDatosInterface {
    <T> T obtenerDatos(String json, Class<T> clase);
}
