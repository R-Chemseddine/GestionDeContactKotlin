package com.example.gestioncontact_boudjema_rachdi_rahmani

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestioncontact_boudjema_rachdi_rahmani.daos.ContactDao
import com.example.gestioncontact_boudjema_rachdi_rahmani.models.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactViewModel(private val contactDao: ContactDao) : ViewModel() {

    // Fonction pour insérer un contact
    fun insert(contact: Contact) = viewModelScope.launch(Dispatchers.IO) {
        contactDao.insert(contact)
    }

    val allContacts: LiveData<List<Contact>> = contactDao.getAllContacts()

    fun deleteContactById(contactId: Long) = viewModelScope.launch {
        contactDao.deleteContactById(contactId)
    }
}
