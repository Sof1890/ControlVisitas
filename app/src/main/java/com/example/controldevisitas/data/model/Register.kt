package com.example.controldevisitas.data.model

class Register {
    var uid:String = ""
    var visitor:String = ""
    var floor:Long = 0
    var requireParking:Boolean = false
    var purpose:String = ""
    var isInBuilding:Boolean = false
    var date:Long = 0
    var time:String = ""

    constructor(uid:String, visitor:String, floor:Long, requireParking:Boolean, purpose:String,
                isInBuilding:Boolean, date:Long, time:String) {
        this.uid = uid
        this.visitor = visitor
        this.floor = floor
        this.requireParking = requireParking
        this.purpose = purpose
        this.isInBuilding = isInBuilding
        this.date = date
        this.time = time
    }
}