package com.matawan.football.adapter.controller;

import com.matawan.football.domaine.dto.JoueurDTO;
import com.matawan.football.domaine.model.Joueur;
import com.matawan.football.domaine.service.JoueurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/joueurs")
public class JoueurController {

    private final JoueurService joueurService;

    public JoueurController(JoueurService joueurService) {
        this.joueurService = joueurService;
    }

    @Operation(
            summary = "Ajouter un joueur",
            description = "Crée un nouveau joueur avec les informations fournies."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created - Joueur créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Données invalides"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - Erreur serveur")
    })
    @PostMapping
    public ResponseEntity<Void> addJoueur(@RequestBody JoueurDTO joueurDTO) {
        joueurService.addJoueur(joueurDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = "Récupérer un joueur par ID",
            description = "Retourne les détails d'un joueur en fonction de son ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - Joueur trouvé",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Not Found - Joueur introuvable"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - Erreur serveur")
    })
    @GetMapping("/{id}")
    public ResponseEntity<JoueurDTO> getJoueurById(@PathVariable Long id) {
        Optional<Joueur> joueur = joueurService.getJoueurById(id);
        return joueur
                .map(value -> ResponseEntity.ok(joueurService.joueurMapper.toDto(value)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Récupérer la liste de tous les joueurs",
            description = "Retourne une liste de tous les joueurs enregistrés."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - Joueurs trouvés",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "204", description = "No Content - Aucun joueur trouvé"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - Erreur serveur")
    })
    @GetMapping
    public ResponseEntity<List<Joueur>> getAllJoueurs() {
        List<Joueur> joueurs = joueurService.getAllJoueurs();
        if (joueurs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(joueurs);
    }

    @Operation(
            summary = "Rechercher un joueur par nom de famille",
            description = "Retourne un joueur correspondant au nom de famille fourni."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - Joueur trouvé",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Not Found - Joueur introuvable"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - Erreur serveur")
    })
    @GetMapping("/search")
    public ResponseEntity<JoueurDTO> getJoueurByLastName(@RequestParam String lastName) {
        try {
            Optional<JoueurDTO> joueurDTO = joueurService.getJoueurByLastName(lastName);
            return joueurDTO
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
