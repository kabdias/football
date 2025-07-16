Feature: Gestion des équipes de football


  Background:
    Given  joueur existant:
      | id  | firstName | lastName | position   |
      | 123 | Said      | KABENE   | Gardien    |
      | 456 | Eventhia  | test     | Défensseur |

    Given equipe existante:
      | idEquipe | nameEquipe | acronyme | joueurs     | budget    |
      | 123      | Nice       | FCN      | KABENE,test | 120000000 |


  Scenario: Ajouter une équipe avec des joueurs
    Given une équipe nommée "<Name>" avec  un budget de "<budget>"
    When je soumets la création de l'équipe
    Then l'équipe est enregistrée avec succès
    And elle contient 2 joueurs

  Scenario: Récupérer la liste des équipes triée par nom
    Given plusieurs équipes existent dans la base
    When je demande la liste des équipes triée par nom
    Then je reçois une liste d’équipes triées alphabétiquement