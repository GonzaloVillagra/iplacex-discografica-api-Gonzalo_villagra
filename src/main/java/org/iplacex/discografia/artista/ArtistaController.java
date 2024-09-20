package org.iplacex.discografia.artista;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ArtistaController {
    @Autowired
    private IArtistaRepository artistaRepo;
    
//Funcion para agregar un artista   

@PostMapping(
    value = "/artista",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
)
public ResponseEntity<Artista> handleInsertArtistaRequest(@RequestBody Artista artista) {
    Artista temp = artistaRepo.save(artista);
    return new ResponseEntity<>(temp, HttpStatus.CREATED);
}

//Funcion para Buscar a todos los artistas

    @GetMapping(
        value = "/artista/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Artista>> handleGetArtistasRequest() {
        List<Artista> artistas = artistaRepo.findAll();
        return new ResponseEntity<>(artistas, HttpStatus.OK);
    }

    public ResponseEntity<Artista> HandleGetArtistaRequest(@PathVariable("id") String id){
        Optional<Artista> temp = artistaRepo.findById(id);    

    if(!temp.isPresent()){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(temp.get(),null, HttpStatus.OK);
    }

    
    @GetMapping(
        value = "/artistas",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Artista>> handleGetAllArtistasRequest() {
        List<Artista> temp = artistaRepo.findAll();
    
        if (temp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }

//Funcion para Editar informacion
    @PutMapping(
    value = "/artista/{id}",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Artista> handleUpdateArtistaRequest(
        @PathVariable("id") String id,
        @RequestBody Artista artista
    ) {
        if (!artistaRepo.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        artista._id = id;
        Artista temp = artistaRepo.save(artista);
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }
    //Funcion para eliminar segun ID
    @DeleteMapping(
        value = "/artista/{id}"
    )
    public ResponseEntity<Artista> handleDeleteArtistaRequest(@PathVariable("id") String id) {
        if (!artistaRepo.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Artista temp = artistaRepo.findById(id).get();
        artistaRepo.deleteById(id);
        return new ResponseEntity<>(temp, HttpStatus.GONE);
    }

}
