package com.solodeveloper.tournaments

class Tournament{
    var tournamentName:String = ""
    var tournamentDescription:String=""
    var imgaeid:String = ""
    var date:String = ""

     constructor(
        tournamentName: String,
        tournamentDescription: String,
        imgaeid: String,
        date: String
    ) {
        this.tournamentName = tournamentName
        this.tournamentDescription = tournamentDescription
        this.imgaeid = imgaeid
        this.date = date
    }
}