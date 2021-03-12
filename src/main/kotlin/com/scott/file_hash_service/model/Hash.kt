package com.scott.file_hash_service.model

import com.scott.file_hash_service.HashAlgorithmType
import java.io.File
import javax.persistence.*
import javax.print.attribute.standard.Destination

//TODO: Goal is to replace hashAlgorithmType with hashName and @Enumerated.
class Hash (
    //@Enumerated(EnumType.STRING)     //CANT USE - IN ENUM CLASS SO SHA-256 WONT WORK
    //val hashName : HashAlgorithmType,
    val file: File,
    val hashAlgorithmType: String
    //val destination: Destination
    ) {
    var currentHashes: HashMap<String, String> = HashMap<String, String>() // this might need to be <enum, Hex or something>
    var multipleHashAlgorithmTypes: Array<String> = emptyArray() //This will be used when dealing with multiple algorithmTypes

}