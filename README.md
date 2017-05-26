RestProject

###########################################################

Petit site internet réalisé sur l'architecture REST avec javascript côté cient:

    afficher la liste de contacts (stockés dans la base de donnée)
    saisir un nouveau contact (dans la base de donéne)
    supprimer un contact (de la base de données)
    rechercher dans la base de données

code SQL pour créer la base et la table :

CREATE DATABASE CARNETSCONTACTS;

USE CARNETSCONTACTS;
CREATE TABLE IF NOT EXISTS contacts ( contact_id INT(4) NOT NULL AUTO_INCREMENT, nom VARCHAR(45) DEFAULT 'unamed', prenom VARCHAR(45) DEFAULT 'unamed', numero VARCHAR(45) NOT NULL, PRIMARY KEY (contact_id) );

###########################################################
