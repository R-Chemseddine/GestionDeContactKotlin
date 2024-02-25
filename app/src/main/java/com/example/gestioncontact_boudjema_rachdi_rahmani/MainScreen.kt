package com.example.gestioncontact_boudjema_rachdi_rahmani

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gestioncontact_boudjema_rachdi_rahmani.ui.theme.GestionContact_Boudjema_Rachdi_RahmaniTheme

@Composable
fun MainScreen(onAddContact: () -> Unit) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddContact) {
                Icon(Icons.Filled.Add, contentDescription = "Add Contact")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        content = { paddingValues ->
            ContactList(paddingValues = paddingValues)
        }
    )
}

@Composable
fun ContactList(paddingValues: PaddingValues) {
    // Ici, vous pouvez ajouter la logique pour afficher votre liste de contacts.
    // Pour cet exemple, nous allons simplement afficher du texte.
    Column(modifier = Modifier.padding(paddingValues)) {
        Text(text = "Liste des contacts")
        // Vous pouvez remplacer ceci par votre LazyColumn pour afficher les contacts.
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GestionContact_Boudjema_Rachdi_RahmaniTheme {
        MainScreen(onAddContact = {})
    }
}
