package com.example.gestioncontact_boudjema_rachdi_rahmani

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.recyclerview.widget.RecyclerView
import com.example.gestioncontact_boudjema_rachdi_rahmani.models.Contact
import com.example.gestioncontact_boudjema_rachdi_rahmani.ui.theme.GestionContact_Boudjema_Rachdi_RahmaniTheme
import android.content.Intent
import androidx.activity.viewModels
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity(), ContactAdapter.OnContactClickListener {
    private lateinit var contactsRecyclerView: RecyclerView
    private lateinit var contactAdapter: ContactAdapter

    private val contactViewModel: ContactViewModel by viewModels {
        ContactViewModelFactory((application as ContactsApplication).contactDao)
    }

    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialisation de RecyclerView
        val contactsRecyclerView: RecyclerView = findViewById(R.id.contactsRecyclerView)
        contactsRecyclerView.layoutManager = LinearLayoutManager(this)

        // Initialisation de l'adaptateur avec une liste vide et 'this' comme listener
        contactAdapter = ContactAdapter(emptyList(), this)
        contactsRecyclerView.adapter = contactAdapter

        // Exemple d'utilisation de updateContacts quand de nouveaux contacts sont disponibles
        contactViewModel.allContacts.observe(this) { newContacts ->
            contactAdapter.updateContacts(newContacts)
        }


        // Gestion du FloatingActionButton pour ajouter un contact
        val fab: FloatingActionButton = findViewById(R.id.fab_add_contact)
        fab.setOnClickListener {
            val intent = Intent(this, AddEditContactActivity::class.java)
            startActivity(intent)
        }

        contactViewModel.allContacts.observe(this) { contacts ->
            contactAdapter.submitList(contacts)
        }
    }

    override fun onContactClicked(contact: Contact) {
        val intent = Intent(this, ContactDetailActivity::class.java).apply {
            intent.putExtra("CONTACT_ID", contact.id) // Assurez-vous que `id` est correctement défini et récupéré
            startActivity(intent)
            // Passez les détails du contact en tant qu'extra
            putExtra("CONTACT_NAME", contact.name)
            putExtra("CONTACT_PHONE", contact.phone)
        }
        startActivity(intent)    }
}
