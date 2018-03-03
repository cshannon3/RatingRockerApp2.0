package com.ratingrocker.ratingrockerapp.DataObjects

data class spotifysong(
        var song_name:String,
        var song_id: String,
        var artist: String,
        var popularity: Int,
        var spotify_data: String,
        var spotify_genre: String



) {
    constructor() : this("", "", "", 0, "", "")
}