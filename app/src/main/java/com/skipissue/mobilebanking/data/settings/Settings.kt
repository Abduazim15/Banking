package com.skipissue.mobilebanking.data.settings

interface Settings {
    var temporaryToken: String?
    var code: String?
    var sigInToken:String?
    var screenPassword:String?
    var policy:Int
    var auth:Int
    var phone_number: String?
}