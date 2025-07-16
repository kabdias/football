
Guide d'installation du projet footBall-KABENE

Ce projet est une application Java Spring Boot de gestion de joueurs de football.
Il utilise JPA/Hibernate pour la persistance, MapStruct pour les mappings d'entités et Cucumber pour les tests d'acceptation.

Prérequis
Java 21+

Maven 3.8+

Git

H2

Étapes d'installation
git clone https://github.com/votre-utilisateur/footBall-KABENE.git

cd footBall-KABENE

Configuration Base de donnée H2
spring.h2.console.enabled=true

spring.h2.console.path=/h2-console

spring.datasource.url=jdbc:h2:mem:testdb

spring.datasource.driver-class-name=org.h2.Driver

spring.datasource.username=sa

spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update


Compilez le projet
mvn clean install
mvn spring-boot:run

Technologies utilisées
Java 21

Spring Boot

Spring Data JPA

MapStruct

Cucumber + JUnit 5

H2 / MySQL

Maven