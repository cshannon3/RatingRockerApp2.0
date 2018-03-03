package com.ratingrocker.ratingrockerapp.DataObjects

data class FullSongdata(
        var id: Long,
        var song_name:String,
        var song_id: String,
        var artist: String,
        var mygenres: String,
        var ratecount: Int,
        var ratingval1: Int,
        var ratingval2: Int,
        var ratingval3: Int,
        var freshval: Int,
        var score: Int,
        var exratings: Int,
        var favsec: Int,
        var popularity: Int,
        var spotify_data: String

) {
    constructor() : this(0,"","","","",0,0,0,0,0,0,1,1,0,"")
}