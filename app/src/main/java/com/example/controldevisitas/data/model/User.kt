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
    constructor(id: String, username: String, name: String, phone:String, email:String, isGuard:Boolean) {
        this.id = id
        this.username = username
        this.name = name
        this.phone = phone
        this.email = email
        this.isGuard = isGuard
    }
    override fun toString() : String {
        return "id: ${id}, username:${username}, name:${name}, phone:${phone}, email:${email}, isGuard:${isGuard}"
    }
}