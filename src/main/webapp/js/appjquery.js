/**
 * 
 */
function Contact(contactId, prenom, nom, numero) {
    this.contactId = contactId;
    this.prenom = prenom;
    this.nom = nom;
    this.numero = numero;
}

var contactsListeDeroulante = [];
var selectedContact = null;

$('#styleGeneral').on('change', function() {
    $('body').css('background-color', this.value);
})

$('#listeContacts').on('change', function() {
    contactsListeDeroulante.forEach(function(contact) {
        if (contact.contactId == $('#listeContacts').val()) {
            selectedContact = contact;
            $('#modifyPrenom').val(contact.prenom);
            $('#modifyNom').val(contact.nom);
            $('#modifyNumero').val(contact.numero);
        }
    }, this);
})

function searchEngine() {
    searchByString($('#searchBox').val());
}

function searchByString(string) { //fonction appelée pour récupérer les contacts et les afficher dans un tableau
    var url = ('http://localhost:8080/restex/rest/contacts/' + string);
    $.getJSON(url, function(contacts) {
        contactsListeDeroulante = contacts; // permet de stocker en local le résultat de la recherche pour remplir 
        // dynamiquement les champs de modification ultérieurement.
        remplirListeDeroulanteContacts(contacts); // appel à la fonction qui va afficher les contacts dans une liste à déroulante
        remplirListeAPucesContacts(contacts); // appel à la fonction qui va afficher les contacts dans une liste à puces
    });
}

function remplirListeAPucesContacts(contacts) { // affichage des contacts dans une liste à puces
    $('#affichageContacts').html(''); // vide la liste à puces avant de la remplir.
    var listeAPuces = '<p>Contacts</p><ul>'; //initialise le html d'une liste à puce.

    contacts.forEach(function(contact) { // itérer sur la collection pour remplir compléter le html
        //  générant la liste à puces
        listeAPuces += ('<li>' + contact.prenom + ' ' + contact.nom + ' [ ' + contact.numero + ']</li>');
    }, this);
    listeAPuces += '</ul>'; // finalise la liste à puces
    $('#affichageContacts').append(listeAPuces); // ajoute la liste à puces à la div.

}

function remplirListeDeroulanteContacts(contacts) { // affichage des contacts dans une liste déroulante
    $('#listeContacts').find('option').remove(); // Vider la liste déroulante avant de la remplir
    $('#listeContacts').append('<option disabled selected value> -- select a contact -- </option>');

    contacts.forEach(function(contact) { // itérer sur la collection pour remplir la liste déroulante
        var value = contact.contactId;
        var text = (contact.prenom + ' ' + contact.nom + ' : ' + contact.numero);
        $('#listeContacts').append('<option value="' + value + '" >' + text + '</option>');
    }, this);

}


function createContact() { // méthode appelée lors de la création d'un contact
    var prenom = $('#inputPrenom').val();
    var nom = $('#inputNom').val();
    var numero = $('#inputNumero').val();
    if ((prenom != '') && (nom != '') && (numero != '')) {
        var contact = new Contact(0, prenom, nom, numero);
        $.ajax({ // appel à la fonction qui va envoyer le contact au serveur
            url: 'http://localhost:8080/restex/rest/contacts/',
            type: 'POST',
            data: JSON.stringify(contact),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: true,
            success: function(msg) {
                searchByString(''); //prendre en charge la réponse si besoin
                $('#inputPrenom').val('');
                $('#inputNom').val('');
                $('#inputNumero').val('');
            }
        });
    }
}

function alterContact() {
    if (selectedContact != null) {
        updateContactServer(selectedContact);
    }
}

function updateContactServer(contact) {
    var updatedContact = new Contact(contact.contactId, $('#modifyPrenom').val(), $('#modifyNom').val(), $('#modifyNumero').val());
    $.ajax({ // appel à la fonction qui va envoyer le contact au serveur
        url: ('http://localhost:8080/restex/rest/contacts/' + contact.nom),
        type: 'PUT',
        data: JSON.stringify(updatedContact),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        async: true,
        success: function(msg) {
            searchByString(''); //prendre en charge la réponse si besoin
            $('#modifyPrenom').val('');
            $('#modifyNom').val('');
            $('#modifyNumero').val('');
            selectedContact = null;
        }
    });
}

function delContact() {
    if (selectedContact != null) {
        delContactServer(selectedContact);
    }
}

function delContactServer(contact) { //fonction appelée pour récupérer les contacts et les afficher dans un tableau
    $.ajax({ // appel à la fonction qui va envoyer le contact au serveur
        url: 'http://localhost:8080/restex/rest/contacts/',
        type: 'DELETE',
        data: JSON.stringify(contact.contactId),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        async: true,
        success: function(msg) {
            searchByString(''); //prendre en charge la réponse si besoin
            $('#modifyPrenom').val('');
            $('#modifyNom').val('');
            $('#modifyNumero').val('');
            selectedContact = null;
        }
    });
}

function clearAlterFields() {
    $('#modifyPrenom').val('');
    $('#modifyNom').val('');
    $('#modifyNumero').val('');
    selectedContact = null;
}