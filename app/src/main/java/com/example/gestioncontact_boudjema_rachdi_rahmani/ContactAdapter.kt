package com.example.gestioncontact_boudjema_rachdi_rahmani

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gestioncontact_boudjema_rachdi_rahmani.models.Contact

class ContactAdapter(private var contacts: List<Contact>, private val listener: OnContactClickListener) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val currentContact = contacts[position]
        holder.bind(currentContact)
    }

    override fun getItemCount() = contacts.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateContacts(newContacts: List<Contact>) {
        contacts = newContacts
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newContacts: List<Contact>) {
        contacts = newContacts
        notifyDataSetChanged()
    }

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val contactImage: ImageView = itemView.findViewById(R.id.contact_image)
        private val contactName: TextView = itemView.findViewById(R.id.contact_name)
        private val contactPhone: TextView = itemView.findViewById(R.id.contact_phone)

        fun bind(contact: Contact) {
            contactName.text = contact.name
            contactPhone.text = contact.phone
            // Utilisez Glide pour charger l'image. Assurez-vous d'ajouter la dépendance Glide à votre build.gradle
            Glide.with(itemView.context).load(contact.photo).into(contactImage)

            itemView.setOnClickListener {
                listener.onContactClicked(contact)
            }
        }
    }

    interface OnContactClickListener {
        fun onContactClicked(contact: Contact)
    }
}
