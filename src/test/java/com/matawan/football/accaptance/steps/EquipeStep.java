package com.matawan.football.accaptance.steps;

import com.matawan.football.domaine.dto.EquipeDTO;
import com.matawan.football.domaine.dto.JoueurDTO;
import com.matawan.football.domaine.model.Equipe;
import com.matawan.football.domaine.service.EquipeService;
import com.matawan.football.domaine.service.JoueurService;
import com.matawan.football.domaine.util.ExceptionUtil;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class EquipeStep {
    private final JoueurService joueurService;
    private final EquipeService equipeService;

    public EquipeStep(EquipeService  equipeService, JoueurService joueurService){
        this.joueurService = joueurService;
        this.equipeService = equipeService;
    }
    @Given("equipe existante:")
    public void equipeExistante(DataTable table) {

        List<Map<String, String>> dataMap = table.asMaps(String.class, String.class);

        dataMap.forEach(equipe -> {
            Long idEquipe = Long.parseLong(equipe.get("idEquipe"));
            String nameEquipe = equipe.get("nameEquipe");
            String acronyme = equipe.get("acronyme");
            int budget = Integer.parseInt(equipe.get("budget"));

            List<String> joueurNoms = Arrays.stream(equipe.get("joueurs").split(","))
                    .map(String::trim)
                    .toList();

            List<JoueurDTO> joueurs = joueurNoms.stream()
                    .map(nom -> joueurService.getJoueurByLastName(nom)
                            .orElseThrow(() -> new RuntimeException("Joueur introuvable avec ID: " + nom)))
                    .collect(Collectors.toList());

            EquipeDTO equipeDto = new EquipeDTO(idEquipe, nameEquipe, acronyme, joueurs, budget);
            equipeService.addEquipe(equipeDto);

            // Vérification
            Equipe equipeEnregistre = equipeService.getAllEquipe().stream()
                    .filter(p -> Objects.equals(p.getIdEquipe(), idEquipe))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(ExceptionUtil.EQUIPE_MISSING));

            Assertions.assertEquals(nameEquipe, equipeEnregistre.getNameEquipe());
            Assertions.assertEquals(acronyme, equipeEnregistre.getAcronyme());
            Assertions.assertEquals(joueurs.size(), equipeEnregistre.getJoueurs().size());
        });
    }
}
