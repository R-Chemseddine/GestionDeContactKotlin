package com.example.gestioncontact_boudjema_rachdi_rahmani

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class ContactDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        // Récupérez les données du contact
        val contactName = intent.getStringExtra("CONTACT_NAME")
        val contactPhone = intent.getStringExtra("CONTACT_PHONE")
        // Récupérez d'autres données si nécessaire

        // Trouvez les vues et assignez-leur les valeurs récupérées
        findViewById<TextView>(R.id.contact_name_detail).text = contactName
        findViewById<TextView>(R.id.contact_phone_detail).text = contactPhone

        val contactViewModel: ContactViewModel by viewModels {
            ContactViewModelFactory((application as ContactsApplication).contactDao)
        }

        val deleteButton: Button = findViewById(R.id.delete_contact_button)
        deleteButton.setOnClickListener {
            // Récupérez l'ID du contact ou un autre identifiant unique
            val contactId = intent.getLongExtra("CONTACT_ID", -1)
            if (contactId != -1L) {
                // Supprimez le contact de la base de données
                contactViewModel.deleteContactById(contactId)
                // Montrez un message de confirmation ou fermez l'activité
                Toast.makeText(this, "Contact supprimé", Toast.LENGTH_SHORT).show()
                finish() // Ferme l'activité et retourne à l'activité précédente
            } else {
                Toast.makeText(this, "Erreur lors de la suppression du contact", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
