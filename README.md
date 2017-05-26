RestProject

###########################################################

Petit site internet réalisé sur l'architecture REST avec javascript côté cient.
Le site permet d'interroger une base de donnée MySQL distante.
adresse:
 - http://<ip>:8080/restex/zorglub.html
 
liste des fonctionnalités:
    recherche de contacts
    saisir un nouveau contact
    supprimer/modifier un contact

code SQL pour créer la base et la table :

CREATE DATABASE CARNETSCONTACTS;

USE CARNETSCONTACTS;
CREATE TABLE IF NOT EXISTS contacts ( contact_id INT(4) NOT NULL AUTO_INCREMENT, nom VARCHAR(45) DEFAULT 'unamed', prenom VARCHAR(45) DEFAULT 'unamed', numero VARCHAR(45) DEFAULT 'null', PRIMARY KEY (contact_id) );

###########################################################
