package com.matawan.football.accaptance.steps;

import com.matawan.football.domaine.dto.JoueurDTO;
import com.matawan.football.domaine.model.Joueur;
import com.matawan.football.domaine.model.Position;
import com.matawan.football.domaine.service.JoueurService;
import com.matawan.football.domaine.util.ExceptionUtil;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class JoueurStep {
    private JoueurService joueurService;

    public JoueurStep(JoueurService  joueurService){
        this.joueurService = joueurService;
    }
    @Given("joueur existant:")
    public void joueurExistant(DataTable table) {

        List<Map<String, String>> dataMaps = table.asMaps(String.class, String.class);

        dataMaps.forEach(dataMap -> {
            Long idJoueur = Long.parseLong(dataMap.get("id"));
            String firstName= dataMap.get("firstName");
            String lastName = dataMap.get("lastName");
            String position = dataMap.get("position");

            JoueurDTO joueurDTO = new JoueurDTO(idJoueur, firstName, lastName, Position.getPosition(position));

            joueurService.addJoueur(joueurDTO);

            List<Joueur> allJoueurs = joueurService.getAllJoueurs();
            Joueur joueurEnregistre = allJoueurs.stream()
                    .filter(j -> firstName.equals(j.getFirstName()) &&
                            lastName.equals(j.getLastName()) &&
                            j.getPosition() == Position.getPosition(position))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(ExceptionUtil.PLAYER_ABSENT));;

            Assertions.assertEquals(firstName, joueurEnregistre.getFirstName());
            Assertions.assertEquals(lastName, joueurEnregistre.getLastName());
            Assertions.assertEquals(Position.getPosition(position), joueurEnregistre.getPosition());
        });
    }
}
