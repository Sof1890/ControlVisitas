package com.example.controldevisitas.data.model

class User  {
    var id : String = ""
    var username : String = ""
    var name : String = ""
    var phone : String = ""
    var email : String = ""
    var isGuard : Boolean = false
    constructor(username:String, name:String, phone:String, email:String) {
        this.username = username
        this.name = name
        this.phone = phone
        this.email = email
        this.isGuard = false
    }
}