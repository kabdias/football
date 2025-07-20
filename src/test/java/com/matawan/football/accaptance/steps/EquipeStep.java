package com.matawan.football.accaptance.steps;

import com.matawan.football.domaine.dto.EquipeDTO;
import com.matawan.football.domaine.dto.JoueurDTO;
import com.matawan.football.domaine.model.Equipe;
import com.matawan.football.domaine.model.Joueur;
import com.matawan.football.domaine.service.EquipeService;
import com.matawan.football.domaine.service.JoueurService;
import com.matawan.football.domaine.util.ExceptionUtil;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

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

            EquipeDTO equipeDto = new EquipeDTO( nameEquipe, acronyme, joueurs, budget);
            equipeService.addEquipe(equipeDto);

            // Vérification
            Equipe equipeEnregistre = equipeService.getAllEquipe().stream()
                    .filter(p -> Objects.equals(p.getNameEquipe(), nameEquipe))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(ExceptionUtil.EQUIPE_MISSING));

            Assertions.assertEquals(nameEquipe, equipeEnregistre.getNameEquipe());
            Assertions.assertEquals(acronyme, equipeEnregistre.getAcronyme());
            Assertions.assertEquals(joueurs.size(), equipeEnregistre.getJoueurs().size());
        });
    }

    @Given("une nouvelle équipe nommée {string}")
    public void equipeNommeeAvecUnBudget(String name ) {
        Optional<Equipe> equipeOpt   = equipeService.getAllEquipe().stream()
                .filter(equipe -> equipe.getNameEquipe().equals(name)).findFirst();
        Assertions.assertFalse(equipeOpt .isPresent());
    }

    @When("je soumets la création de l'équipe avec les informations nom : {string}, acronyme : {string} , joueur : {string} et budget : {string}")
    public void creationEquipe(String name, String acronyme, String joueur, String budgetString) {

        JoueurDTO j = joueurService.getJoueurByLastName(joueur)
                .orElseThrow(() -> new RuntimeException("Le joueur avec l'ID " + joueur + " est introuvable"));

        EquipeDTO equipeDTO = new EquipeDTO(name, acronyme, List.of(j),  Integer.parseInt(budgetString));

        equipeService.addEquipe(equipeDTO);
        Equipe equipeEnregistre = equipeService.getAllEquipe().stream()
                .filter(p -> Objects.equals(p.getNameEquipe(), name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(ExceptionUtil.EQUIPE_MISSING));
        Assertions.assertEquals(name, equipeEnregistre.getNameEquipe());
    }

    @Then("l'équipe {string} est enregistrée avec succès")
    public void lEquipeEstEnregistreeAvecSucces(String name) {
        Optional<EquipeDTO> equipe = equipeService.findByNameEquipe(name);
        assertTrue(equipe.isPresent());
    }
}
