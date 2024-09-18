package org.iplacex.discografia.disco;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("disco")
public class Disco {
    @Id
    public String _id;
    public String idArtista;
    public String nombre;
    public int anioLanzamiento;
    public List<String> canciones;
}
