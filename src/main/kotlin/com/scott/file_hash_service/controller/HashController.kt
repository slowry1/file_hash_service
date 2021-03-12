package com.scott.file_hash_service.controller

import com.scott.file_hash_service.HashAlgorithmType
import com.scott.file_hash_service.model.Hash
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import com.scott.file_hash_service.service.HashingService
import com.scott.file_hash_service.service.UtilitiesService
import org.springframework.web.multipart.MultipartFile
import java.io.IOException

@RestController
@RequestMapping("/api")
class HashController {
    @Autowired
    lateinit var hashingService: HashingService

    @Autowired
    lateinit var utilService: UtilitiesService

    @PostMapping("/hash")
//    fun addHash(@RequestBody hash: Hash ) : String {
    fun addHash(@RequestBody hashEntity: Hash ) : Hash {
        println("This is the algorithm to use for calculating hash: ${hashEntity.hashAlgorithmType}")
//        val hashOutput = hashingService.calculateHash(hashEntity.file, hashEntity.hashAlgorithmType) //This can be used if we return the hash string
        val hashOutput = hashingService.calculateHashReturningEntity(hashEntity)

        return hashOutput
    }

    @PostMapping("/enum")
    fun testEnum(@RequestBody payload: Map<String, String>): Boolean { // also look at jackson library to handle json stuff    algorithmType: String): Boolean {//enum: Enum<HashAlgorithmType>) : String {
        val temp: Boolean = utilService.enumContains(payload["algorithmType"]!!)//algorithmType)
        return temp//enumContains<HashAlgorithmType>(algorithmType)
    }

    @PostMapping("/unzip")
    //fun testUnzip(@RequestBody hash : Hash) : String { ///TODO I think this would take in @RequestParam("file") file: MultipartFile since it would be one tar
    fun unzip(@RequestParam("file") file: MultipartFile) : String {
        if (!file.isEmpty) {
            println("inside not empty")
            try {
                val (extractedFile, checksum, algorithmType) = utilService.untarFile(file)
                println("Extracted File: $extractedFile     This is the checksum: $checksum     This is the algoType: $algorithmType")
                if (!utilService.enumContains(algorithmType)){
                    throw EnumConstantNotPresentException(HashAlgorithmType::class.java, algorithmType)
                }
                val hash = hashingService.calculateHash(extractedFile, algorithmType)
                val hashesMatch: Boolean = hashingService.compareHashes(checksum, hash)
                println("Do the hashes match? $hashesMatch")
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: EnumConstantNotPresentException){
                e.printStackTrace()
            }
        }
        //val hash = hashingService.calculateHash(hash.fileInput, hash.hashAlgorithmType) // we will take in a file and list of algorithms later.
//        println("This is the algorithm to use for unzipping: ${hash.stringInput}")
//        val unTar = unTarService.untarFile(hash.stringInput)
        return "UNZIP"
    }

    //    Example Request Post Body
//
//    {
//        "fileInput" : "/Users/slowr/Desktop/upload_example_text/example1.txt",
//        "hashAlgorithmType": "MD5"
//    }
}