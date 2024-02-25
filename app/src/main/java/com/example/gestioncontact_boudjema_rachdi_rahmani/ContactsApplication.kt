package com.example.gestioncontact_boudjema_rachdi_rahmani

import android.app.Application
import com.example.gestioncontact_boudjema_rachdi_rahmani.database.AppDatabase

class ContactsApplication : Application() {
    private val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
    val contactDao by lazy { database.contactDao() }
}
