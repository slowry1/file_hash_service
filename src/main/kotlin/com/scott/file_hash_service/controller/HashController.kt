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
    lateinit var unTarService: UtilitiesService

    //    THIS IS BROKEN NOW THAT calculateHash returns a Hash entity
    @PostMapping("/hash")
//    fun addHash(@RequestBody hash: Hash ) : String {
    fun addHash(@RequestBody hashEntity: Hash ) : Hash {
        println("This is the algorithm to use for calculating hash: ${hashEntity.hashAlgorithmTypes}")
//        val hashOutput = hashingService.calculateHash(hash.stringInput, hash.hashAlgorithmType) // we will take in a file and list of algorithms later.
        val hashOutput = hashingService.calculateHash(hashEntity) // we will take in a file and list of algorithms later.

        //val unTar = untarService.untarFile(hash.stringInput)

        return hashOutput
    }

    /**
     * Returns `true` if enum T contains an entry with the specified name.
     */
//    private inline fun <reified T : Enum<T>> enumContains(algorithmType: String): Boolean {
    fun enumContains(algorithmType: String): Boolean {
        val temp: Boolean = enumValues<HashAlgorithmType>().any {
            println("THis is it: ${it.algorithmType}")
            println("This is algotyhpe passed in: $algorithmType")
            it.algorithmType == algorithmType
        }
        return temp //enumValues<T>().any { it.name == name}
    }

    @PostMapping("/enum")
    fun testEnum(@RequestBody payload: Map<String, String>): Boolean { // also look at jackson library to handle json stuff    algorithmType: String): Boolean {//enum: Enum<HashAlgorithmType>) : String {
        val temp: Boolean = enumContains(payload["algorithmType"]!!)//algorithmType)
        return temp//enumContains<HashAlgorithmType>(algorithmType)
    }

    @PostMapping("/package_upload")
    fun createHashByPackage(@RequestParam("file") file: MultipartFile) : String {
        if (!file.isEmpty) {
            println("inside not empty")
            try {
//                val hashEntity: Hash = extractFiles(file) //val (file, hashAlgorithmTypes) = extractFiles(file)//call unilservice to unpack the zip/tar  hopefully this will get returned Hash(file = fileThatWasInTheTar, hashAlgorithmTypes = arrayOf("MD5", "SHA-256")) maybe we will keep it a Hash and then pass it to the Hashing Service to calculate
//                val hash = hashingService.calculateHash(HashEntity)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        //val hash = hashingService.calculateHash(hash.fileInput, hash.hashAlgorithmType) // we will take in a file and list of algorithms later.

        return "Done Doing Something"
    }

    @PostMapping("/unzip")
    //fun testUnzip(@RequestBody hash : Hash) : String { ///TODO I think this would take in @RequestParam("file") file: MultipartFile since it would be one tar
    fun testUnzip(@RequestParam("file") file: MultipartFile) : String {

        println("This is the algorithm to use for unzipping: ${file.name}")
        println(file.name)
        println(file.originalFilename)
        println(file.javaClass.name)

//        val unTar = unTarService.untarFile(hash.stringInput)
        val unTar = unTarService.untarFile(file)
        return "UNZIP"
    }

    //    Example Request Post Body
//
//    {
//        "fileInput" : "/Users/slowr/Desktop/upload_example_text/example1.txt",
//        "hashAlgorithmType": "MD5"
//    }
}