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
    var prenom = document.querySelector("#inputPrenom");
    var nom = document.querySelector("#inputNom");
    var numero = document.querySelector("#inputNumero");
    if ((prenom.value != "") && (nom.value != "") && (numero.value != "")) {
        var contact = new Contact(0, prenom, nom, numero);
        envoieContact(contact); // appel à la fonction qui va envoyer le contact au serveur
        prenom.value = "";
        nom.value = "";
        numero.value = "";
    }
}

function envoieContact(contact) {
    var xhr = new XMLHttpRequest();
    // @Path("nom-{nom}-prenom-{prenom}-numero-{numero}")
    var url = "http://localhost:8080/restex/rest/carnet/"
    xhr.onreadystatechange = function () {
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
    var url = ("http://localhost:8080/restex/rest/carnet/json");

    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) { // La constante DONE appartient à l'objet XMLHttpRequest,
            // elle n'est pas globale
            var contacts = JSON.parse(xhr.responseText);
            afficherContacts(contacts); // appel à la fonction qui va afficher les contacts dans un tableau
            console.log(xhr.getResponseHeader('Content-type'));
        }
    };
    xhr.open('GET', url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send();
}

function afficherContacts(contacts) { // affichage des contacts dans un tableau
    var display = document.querySelector("#affichageContacts");
    display.innerHTML += contacts;  
    for (var contact in contacts) {
        console.log("nouvelle ligne");
        var newLine = display.insertRow(-1);
        newLine.insertCell[0].innerHTML += contact.prenom;
        newLine.insertCell[1].innerHTML += contact.nom;
        newLine.insertCell[2].innerHTML += contact.numero;
    }

}