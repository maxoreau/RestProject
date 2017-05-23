/**
 * 
 */
function Contact(contactId, prenom, nom, numero) {
    this.contactId = contactId;
    this.prenom = prenom;
    this.nom = nom;
    this.numero = numero;
}


function createContact() { // méthode appelée lors de la création d'un contact
    var prenom = document.querySelector("#inputPrenom").value;
    var nom = document.querySelector("#inputNom").value;
    var numero = document.querySelector("#inputNumero").value;
    if ((prenom != "") && (nom != "") && (numero != "")) {
        var contact = new Contact(0, prenom, nom, numero);
        envoieContact(contact); // appel à la fonction qui va envoyer le contact au serveur
        console.log(JSON.stringify(contact));
    }
    setTimeout(function() {
        getContacts(); //your code to be executed after 100 milliseconds
        // permet de rafraichir l'affichage dès que la suppression d'un contat a été réalisée
    }, 100);
    prenom = "";
    nom = "";
    numero = "";
}


function envoieContact(contact) {
    var xhr = new XMLHttpRequest();
    var url = "http://localhost:8080/restex/rest/contacts/";
    xhr.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) { // La constante DONE appartient à l'objet XMLHttpRequest,
            // elle n'est pas globale
            console.log(xhr.getResponseHeader('Content-type'));
        }
    };
    xhr.open('POST', url, true); // POST très important : c'est lui qui défini quelle fonction du service REST est appelée
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(JSON.stringify(contact));
}


function getContacts() { //fonction appelée pour récupérer les contacts et les afficher dans un tableau
    var xhr = new XMLHttpRequest();
    var url = ("http://localhost:8080/restex/rest/contacts");

    xhr.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) { // La constante DONE appartient à l'objet XMLHttpRequest,
            // elle n'est pas globale
            var contacts = JSON.parse(xhr.responseText);
            remplirListeDeroulanteContacts(contacts); // appel à la fonction qui va afficher les contacts dans une liste à déroulante
            remplirListeAPucesContacts(contacts); // appel à la fonction qui va afficher les contacts dans une liste à puces
            console.log(xhr.getResponseHeader('Content-type'));
        }
    };
    xhr.open('GET', url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send();
}

function searchEngine() {
    var string = document.querySelector("#searchBox").value;
    getContact(string);
}

function getContact(nom) { //fonction appelée pour récupérer les contacts et les afficher dans un tableau
    var xhr = new XMLHttpRequest();
    var url = ("http://localhost:8080/restex/rest/contacts/" + nom);

    xhr.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) { // La constante DONE appartient à l'objet XMLHttpRequest,
            // elle n'est pas globale
            var contacts = JSON.parse(xhr.responseText);
            remplirListeDeroulanteContacts(contacts); // appel à la fonction qui va afficher les contacts dans une liste à déroulante
            remplirListeAPucesContacts(contacts); // appel à la fonction qui va afficher les contacts dans une liste à puces
            console.log(xhr.getResponseHeader('Content-type'));
        }
    };
    xhr.open('GET', url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(nom);
}


function remplirListeAPucesContacts(contacts) { // affichage des contacts dans une liste à puces
    var display = document.querySelector("#affichageContacts");
    display.innerHTML = "<p>Contacts</p><ul>"; // initialise la liste à puces

    contacts.forEach(function(contact) { // itérer sur la collection pour remplir la liste à puces
        display.innerHTML += ("<li>" + contact.prenom + " " + contact.nom + " [ " + contact.numero + "]</li>")
        console.log(contact.contactId);
    }, this);
    display.innerHTML += "</ul>"; // finalise la liste à puces

}

function remplirListeDeroulanteContacts(contacts) { // affichage des contacts dans une liste déroulante
    var listeDeroulante = document.querySelector("#listeContacts");
    listeDeroulante.options.length = 0; // Vider la liste déroulante avant de la remplir

    contacts.forEach(function(contact) { // itérer sur la collection pour remplir la liste déroulante
        var option = document.createElement('option');
        option.value = contact.contactId;
        option.innerHTML = (contact.prenom + " " + contact.nom + " : " + contact.numero);
        listeDeroulante.appendChild(option);
    }, this);

}


function delContacts() { //fonction appelée pour récupérer les contacts et les afficher dans un tableau
    var xhr = new XMLHttpRequest();
    var url = ("http://localhost:8080/restex/rest/contacts/");
    var contactId = document.querySelector("#listeContacts").value;

    xhr.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) { // readystate == 4 : toutes les étapes se sont bien réalisées
            // status == 200 : le serveur dit que tout s'est bien passé
            setTimeout(function() {
                getContacts(); //your code to be executed after 100 milliseconds
                // permet de rafraichir l'affichage dès que la suppression d'un contat a été réalisée
            }, 100);
            console.log(xhr.getResponseHeader('Content-type'));
        }
    };
    xhr.open('DELETE', url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(contactId);
}