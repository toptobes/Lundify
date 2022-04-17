package com.lundify.data.models

import java.io.Serializable

data class UserLibrary(
    val songs: List<Song>
) : Serializable
