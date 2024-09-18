package org.iplacex.discografia.artista;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("artista")
public class Artista {
@Id
public String _id;
public String nombre;
public List<String> estilos;
public int anioFundacion;
public boolean estaActivo;
}
