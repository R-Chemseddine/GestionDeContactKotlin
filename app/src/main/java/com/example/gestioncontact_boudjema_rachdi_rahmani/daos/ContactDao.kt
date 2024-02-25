package com.example.gestioncontact_boudjema_rachdi_rahmani.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.gestioncontact_boudjema_rachdi_rahmani.models.Contact

@Dao
interface ContactDao {
    @Query("SELECT * FROM contacts")
    fun getAll(): LiveData<List<Contact>>

    @Insert
    suspend fun insert(contact: Contact)

    @Update
    suspend fun update(contact: Contact)

    @Delete
    suspend fun delete(contact: Contact)

    @Query("SELECT * FROM contacts WHERE name LIKE :searchQuery")
    fun searchContacts(searchQuery: String): LiveData<List<Contact>>

    @Query("SELECT * FROM contacts ORDER BY name ASC")
    fun getAllContacts(): LiveData<List<Contact>>

    @Query("DELETE FROM contacts WHERE id = :contactId")
    suspend fun deleteContactById(contactId: Long)

}
