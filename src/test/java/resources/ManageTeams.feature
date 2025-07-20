Feature: Gestion des équipes de football


  Background:
    Given  joueur existant:
      | firstName | lastName | position   |
      | Said      | KABENE   | Gardien    |
      | Eventhia  | test     | Défensseur |

    Given equipe existante:
      | nameEquipe | acronyme | joueurs     | budget    |
      | Nice       | FCN      | KABENE,test | 120000000 |


  Scenario Outline: Ajouter une équipe avec des joueurs
    Given une nouvelle équipe nommée "<name>"
    When je soumets la création de l'équipe avec les informations nom : "<name>", acronyme : "<acronyme>" , joueur : "<joueurs>" et budget : "<budget>"
    Then l'équipe "<name>" est enregistrée avec succès
    And elle contient 2 joueurs

    Examples:
      |  | name  | acronyme | joueurs | budget |
      |  | Paris | PSG      | KABENE  | 100000 |