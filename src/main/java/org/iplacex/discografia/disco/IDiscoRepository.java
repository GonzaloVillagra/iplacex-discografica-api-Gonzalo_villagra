package org.iplacex.discografia.disco;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface IDiscoRepository extends MongoRepository<Disco, String> {

    List<Disco> findDiscosByIdArtista(String idArtista);
}
