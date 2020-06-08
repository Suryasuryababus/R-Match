package com.solodeveloper.daily_matches

class DailyMatch{
    var channel:String
    var startTime:String
    var description:String
    var registeredPlayersCount:Int
    var id:String
    var imageId:String
    constructor(channel: String, startTime: String, description: String, registeredPlayersCount:Int, id: String,imageId:String) {
        this.channel = channel
        this.startTime = startTime
        this.description = description
        this.registeredPlayersCount = registeredPlayersCount
        this.id = id
        this.imageId=imageId
    }
}
