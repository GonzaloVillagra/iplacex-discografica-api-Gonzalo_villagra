package org.iplacex.discografia.disco;

import java.util.List;
import java.util.Optional;

import org.iplacex.discografia.artista.IArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api")

public class DiscoController {

    private IDiscoRepository discoRepo;
    private IArtistaRepository iArtistaRepo;

@Autowired
    public DiscoController(IDiscoRepository discoRepo, IArtistaRepository iArtistaRepo) {
        this.discoRepo = discoRepo;
        this.iArtistaRepo = iArtistaRepo;
    }
    
//Funcion para ingresar disco

@PostMapping(
    value = "/disco",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
)
public ResponseEntity<Disco> HandlePostDiscoRequest(@RequestBody Disco disco) {
    if (!iArtistaRepo.existsById(disco.idArtista)) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    Disco temp = discoRepo.insert(disco);
    return new ResponseEntity<>(temp, HttpStatus.CREATED);
}

//Funcion para obtener loa discos de un artista

@GetMapping(
    value = "/discos",
    produces = MediaType.APPLICATION_JSON_VALUE
)
public ResponseEntity<List<Disco>> HandleGetDiscosRequest(){
    List<Disco> discos = discoRepo.findAll();    
    if (discos.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(discos, HttpStatus.OK);
}
//Funcion para obtener un disco por ID

@GetMapping(
    value = "/disco/{id}",
    produces = MediaType.APPLICATION_JSON_VALUE
)
public ResponseEntity<Disco> handleGetDiscoRequest(@PathVariable("id") String id) {
    Optional<Disco> temp = discoRepo.findById(id);
    if (!temp.isPresent()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(temp.get(), HttpStatus.OK);
}

//Funcion para borrar un registro por id

@GetMapping(
    value = "/discos/artista/{idArtista}",
    produces = MediaType.APPLICATION_JSON_VALUE
)
public ResponseEntity<List<Disco>> HandleGetDiscosByArtistaRequest(
    @PathVariable("idArtista") String idArtista) {
    List<Disco> discos = discoRepo.findDiscosByIdArtista(idArtista);
    if (discos.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(discos, HttpStatus.OK);
}
}


        

