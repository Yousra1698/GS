package com.projets.applicationgestionclients.Activities

import com.projets.applicationgestionclients.Client.Client

interface InteractionClient {
    fun onClickItemClient(client: Client)
    fun onClickIconHistorique(client: Client)
}