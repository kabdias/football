package com.matawan.football.adapter.controller;

import com.matawan.football.domaine.dto.EquipeDTO;
import com.matawan.football.domaine.model.Equipe;
import com.matawan.football.domaine.service.EquipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/equipes")
public class EquipeController {

    private final EquipeService equipeService;

    public EquipeController(EquipeService equipeService) {
        this.equipeService = equipeService;
    }


    @Operation(
            summary = "Récupérer toutes les équipes",
            description = "Retourne une liste de toutes les équipes enregistrées."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - Équipes trouvées",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "204", description = "No Content - Aucune équipe trouvée"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - Erreur serveur")
    })
    @GetMapping
    public ResponseEntity<List<Equipe>> getAllEquipes() {
        List<Equipe> equipes = equipeService.getAllEquipe();
        return new ResponseEntity<>(equipes, HttpStatus.OK);
    }

    @Operation(
            summary = "Récupérer une équipe par son nom",
            description = "Cette méthode retourne les détails d'une équipe correspondant au nom fourni."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Équipe trouvée",
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = EquipeDTO.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Équipe non trouvée")
    })
    @GetMapping("/{name}")
    public ResponseEntity<EquipeDTO> getEquipeByName(@PathVariable String name) {
        Optional<EquipeDTO> equipe = equipeService.findByNameEquipe(name);
        return equipe.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Créer une équipe",
            description = "Crée une nouvelle équipe avec les informations fournies."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created - Équipe créée avec succès"),
            @ApiResponse(responseCode = "400", description = "Bad Request - Données invalides"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - Erreur serveur")
    })
    @PostMapping
    public ResponseEntity<Void> createEquipe(@RequestBody EquipeDTO equipeDTO) {
        equipeService.addEquipe(equipeDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
