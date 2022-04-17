package com.lundify.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.lundify.data.models.Song

object DemoLibraryProvider {

    @Composable
    fun userLibrary() = listOf(
        Song(
            image = painterResource("songcovers/main_theme.jpg"),
            title = "GOT main theme",
            artist = "Ramin Djawadi",
            genre = "Soundtrack",
            length = "1:46".toSeconds()
        ),
        Song(
            image = painterResource("songcovers/winter_is_coming.jpg"),
            title = "Winter is coming",
            artist = "Ramin Djawadi",
            genre = "Soundtrack",
            length = "2:41".toSeconds()
        ),
        Song(
            image = painterResource("songcovers/winds_of_winter.jpg"),
            title = "Winds of Winter (Extended)",
            artist = "Ramin Djawadi",
            genre = "Soundtrack",
            length = "8:46".toSeconds()
        ),
        Song(
            image = painterResource("songcovers/light_of_the_seven.jpg"),
            title = "Light of the seven",
            artist = "Ramin Djawadi",
            genre = "Soundtrack",
            length = "9:50".toSeconds()
        ),
        Song(
            image = painterResource("songcovers/a_lannister_always_pays_his_debts.jpg"),
            title = "A Lannister Always Pays His Debts",
            artist = "Ramin Djawadi",
            genre = "Soundtrack",
            length = "2:52".toSeconds()
        ),
        Song(
            image = painterResource("songcovers/dance_of_dragons.jpg"),
            title = "Dance of Dragons",
            artist = "Ramin Djawadi",
            genre = "Soundtrack",
            length = "3:08".toSeconds()
        ),
        Song(
            image = painterResource("songcovers/blood_of_my_blood.jpg"),
            title = "Blood of My Blood",
            artist = "Ramin Djawadi",
            genre = "Soundtrack",
            length = "3:36".toSeconds()
        ),
    )

    @Composable
    fun bigUserLibrary(numSongs: Int = 28): List<Song> {
        val list = mutableListOf<Song>()
        repeat(numSongs / 7) {
            list += userLibrary()
        }
        return list + userLibrary().take(numSongs % 7)
    }

    private fun String.toSeconds(): Int {
        val split = split(":")
        return split[0].toInt() * 60 + split[1].toInt()
    }
}