package com.example.controldevisitas.data.model

import io.realm.kotlin.types.ObjectId
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class User : RealmObject {
    @PrimaryKey
    var _id : ObjectId = ObjectId.create()
    var name : String = ""
    var password : String = ""
    var phone : String = ""
    var email : String = ""
}