package com.scott.file_hash_service

import com.scott.file_hash_service.controller.HashController
import com.scott.file_hash_service.model.Hash
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FileHashServiceApplication

fun main(args: Array<String>) {
	runApplication<FileHashServiceApplication>(*args)

	// ROD Mock Trial  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/*val controller1 = HashController()
	val hash1 = Hash("algoType1", "stringInput1")

	controller1.addHash(hash1)*/
}
