package com.example.gestioncontact_boudjema_rachdi_rahmani

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.gestioncontact_boudjema_rachdi_rahmani.database.AppDatabase
import com.example.gestioncontact_boudjema_rachdi_rahmani.models.Contact
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddEditContactActivity : AppCompatActivity() {
    private lateinit var contactViewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_edit_contact_activity)

        // Initialisation des composants UI
        val nameEditText: EditText = findViewById(R.id.editTextContactName)
        val phoneEditText: EditText = findViewById(R.id.editTextContactPhone)
        val addressEditText: EditText = findViewById(R.id.editTextContactAddress)
        val photoEditText: EditText = findViewById(R.id.editTextContactPhoto)
        val saveButton: Button = findViewById(R.id.buttonSaveContact)

        // Obtenez une instance de la base de données et du DAO
        val contactDao = AppDatabase.getDatabase(application).contactDao()
        // Initialisez le ViewModel avec le ViewModelFactory
        val factory = ContactViewModelFactory(contactDao)
        contactViewModel = ViewModelProvider(this, factory).get(ContactViewModel::class.java)

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val phone = phoneEditText.text.toString().trim()
            val address = addressEditText.text.toString().trim()
            val photo = photoEditText.text.toString().trim()

            // Validation de base pour s'assurer que les champs ne sont pas vides
            if (name.isNotEmpty() && phone.isNotEmpty() && address.isNotEmpty()) {
                // Création d'un objet Contact

                val newContact = Contact(name = name, phone = phone, address = address, photo = photo)

                // Sauvegarde du contact dans la base de données en utilisant Room
                CoroutineScope(Dispatchers.IO).launch {
                    contactViewModel.insert(newContact)
                }

                Toast.makeText(this, "Contact ajouté avec succès", Toast.LENGTH_SHORT).show()
                finish() // Retour à l'activité précédente
            } else {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
