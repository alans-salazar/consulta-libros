package as.aluracursos.literalura.service;

import tools.jackson.databind.ObjectMapper;

public class ConvierteDatos implements ConvierteDatosInterface {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json,clase);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
