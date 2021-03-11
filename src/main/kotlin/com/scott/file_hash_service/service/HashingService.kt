package com.scott.file_hash_service.service

import com.scott.file_hash_service.model.Hash
import org.bouncycastle.util.encoders.Hex
import org.springframework.stereotype.Service
import java.io.File
import java.security.MessageDigest

@Service
class HashingService {
    fun calculateHash(hashEntity: Hash): Hash { //fileInput:File, algorithm:String) : String {
        val fileByteArray: ByteArray = hashEntity.file.readBytes()

        for (algorithm in hashEntity.hashAlgorithmTypes) {
            // TODO: exception handle algorithm string in case it does not match desire hash algo list.
            val messageDigest: MessageDigest = MessageDigest.getInstance(algorithm)

            val hashBytes : ByteArray = messageDigest.digest(fileByteArray)  // toByteArray() defaults to StandardCharsets.UTF_8
            val hex = String(Hex.encode(hashBytes))
            hashEntity.currentHashes.put(algorithm, hex) // we can check if the hash already exist and move it to a historical hashmap if we want to try that later.
            println("This is the hashBytes: $hashBytes")
            println("This is the hashed valid in hexadecimal: $hex")
        }

        return hashEntity
    }

}