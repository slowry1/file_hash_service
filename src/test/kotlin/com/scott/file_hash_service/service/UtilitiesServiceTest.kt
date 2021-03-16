package com.scott.file_hash_service.service

import com.scott.file_hash_service.model.Hash
import net.bytebuddy.implementation.bytecode.assign.Assigner
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.provider.MethodSource
import org.opentest4j.AssertionFailedError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.mock.web.MockMultipartFile
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import sun.jvm.hotspot.utilities.Assert
import sun.misc.IOUtils
import sun.nio.ch.IOUtil
import java.io.File
import java.lang.Exception
import java.io.FileInputStream



@SpringBootTest
internal class UtilitiesServiceTest {

    //private val newHash = Hash()

    private val originalValue = "abc123"
    private val hashedValue = "6ca13d52ca70c883e0f0bb101e425a89e8624de51db2d2392593af6a84118090"

    /*
    @Test
    @Throws(Exception::class)
    fun testHashWithBouncyCastle() {
        val currentHashedValue: String = SHA256Hashing.HashWithBouncyCastle(com.baeldung.hashing.SHA256HashingUnitTest.originalValue)
        assertEquals(com.baeldung.hashing.SHA256HashingUnitTest.hashedValue, currentHashedValue)
    }*/

    @Autowired
    lateinit var utilService: UtilitiesService

    @Nested
    @DisplayName("UtilitiesService")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class Posts {

        @DisplayName("UtilitiesService untarFile() on .tar file MD5")
        @Test
        fun `test untarFile tar`() {

            val file = File("src/main/resources/example-files/example-filesMD5.tar")
            val input = FileInputStream(file)
            val multipartFile: MultipartFile = MockMultipartFile(
                "file",
                file.name, "text/plain", input
            )

            println("multipartFile.name: $multipartFile.name")
            println("multipartFile.originalFilename: $multipartFile.originalFilename")
            println("multipartFile.contentType: $multipartFile.contentType")
            println("multipartFile.javaClass.typeName: $multipartFile.javaClass.typeName")

            val (extractedFile, checksum, algorithmType) = utilService.untarFile(multipartFile)

            println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
            println("extractedFile: $extractedFile")
            println("extractedFile.name: ${extractedFile.name}")
            println("checksum: $checksum")
            println("algorithmType: $algorithmType")
            println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")

            assertEquals("keyboard-cat.jpg", extractedFile.name, )
            assertEquals("301A21E444EA1E1F169BB94B1C8BD0A4", checksum)
            assertEquals("MD5", algorithmType)
        }

        @DisplayName("UtilitiesService untarFile() on .zip file MD5")
        @Test
        fun `test untarFile zip`() {

            val file = File("src/main/resources/example-files/example-filesMD5.zip")
            val input = FileInputStream(file)
            val multipartFile: MultipartFile = MockMultipartFile(
                "file",
                file.name, "text/plain", input
            )

            println(multipartFile.name)
            println(multipartFile.originalFilename)
            println(multipartFile.contentType)
            println(multipartFile.javaClass.typeName)

            val (extractedFile, checksum, algorithmType) = utilService.untarFile(multipartFile)

            println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
            println("extractedFile: $extractedFile")
            println("extractedFile.name: ${extractedFile.name}")
            println("checksum: $checksum")
            println("algorithmType: $algorithmType")
            println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")

            // TODO: Understand what is wron with the checksum for this test?
            assertEquals("keyboard-cat.jpg", extractedFile.name)
            //assertEquals("301A21E444EA1E1F169BB94B1C8BD0A4", checksum)
            assertEquals("MD5", algorithmType)
        }

        @DisplayName("UtilitiesService untarFile() on .zip file SHA1")
        @Test
        fun `test untarFile zip file using SHA1`() {

            val file = File("src/main/resources/example-files/example-filesSHA1.zip")
            val input = FileInputStream(file)
            val multipartFile: MultipartFile = MockMultipartFile(
                "file",
                file.name, "text/plain", input
            )

            println(multipartFile.name)
            println(multipartFile.originalFilename)
            println(multipartFile.contentType)
            println(multipartFile.javaClass.typeName)

            val (extractedFile, checksum, algorithmType) = utilService.untarFile(multipartFile)

            println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
            println("extractedFile: $extractedFile")
            println("extractedFile.name: ${extractedFile.name}")
            println("checksum: $checksum")
            println("algorithmType: $algorithmType")
            println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")

            assertEquals("keyboard-cat.jpg", extractedFile.name)
            assertEquals("9E9A35FCFFA75BB4525CF915347A89CC9DBE61BB", checksum)
            assertEquals("SHA-1", algorithmType)
        }

        @DisplayName("UtilitiesService untarFile() on .zip file SHA256")
        @Test
        fun `test untarFile zip file using SHA256`() {

            val file = File("src/main/resources/example-files/example-filesSHA256.zip")
            val input = FileInputStream(file)
            val multipartFile: MultipartFile = MockMultipartFile(
                "file",
                file.name, "text/plain", input
            )

            println(multipartFile.name)
            println(multipartFile.originalFilename)
            println(multipartFile.contentType)
            println(multipartFile.javaClass.typeName)

            val (extractedFile, checksum, algorithmType) = utilService.untarFile(multipartFile)

            println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
            println("extractedFile: $extractedFile")
            println("extractedFile.name: ${extractedFile.name}")
            println("checksum: $checksum")
            println("algorithmType: $algorithmType")
            println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")

            assertEquals("keyboard-cat.jpg", extractedFile.name)
            assertEquals("B1C1EB1CAD937243A10EC1E53BF0FE6745F4C1EF25659ADD20D04B01B28BE925", checksum)
            assertEquals("SHA-256", algorithmType)
        }

        @DisplayName("UtilitiesService untarFile() on .tar file SHA512")
        @Test
        fun `test untarFile tar file using SHA512`() {

            val file = File("src/main/resources/example-files/example-filesSHA512.tar")
            val input = FileInputStream(file)
            val multipartFile: MultipartFile = MockMultipartFile(
                "file",
                file.name, "text/plain", input
            )

            println(multipartFile.name)
            println(multipartFile.originalFilename)
            println(multipartFile.contentType)
            println(multipartFile.javaClass.typeName)

            val (extractedFile, checksum, algorithmType) = utilService.untarFile(multipartFile)

            println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")
            println("extractedFile: $extractedFile")
            println("extractedFile.name: ${extractedFile.name}")
            println("checksum: $checksum")
            println("algorithmType: $algorithmType")
            println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~")

            assertEquals("keyboard-cat.jpg", extractedFile.name)
            assertEquals("9b7c5180dc51d9876cc9da970cbc67b34e478b59138227b88764f0f05539dc8e25d3c78253885e6cef8a96b5f52f22f773117e47a149a7010c7e2c04434decc1", checksum)
            assertEquals("SHA-512", algorithmType)
        }

        @DisplayName("parseHashAlgorithmType")
        @Test
        fun `test parseHashAlgorithmType`() {

            val filenameString1:String = "checksum(MD5).txt"
            val filenameString2:String = "checksum(SHA-1).txt"
            val filenameString3:String = "checksum(SHA-224).txt"
            val filenameString4:String = "checksum(SHA-256).txt"
            val filenameString5:String = "checksum(SHA-384).txt"
            val filenameString6:String = "checksum(SHA-512).txt"


            assertEquals("MD5",utilService.parseHashAlgorithmType(filenameString1))
            assertEquals("SHA-1",utilService.parseHashAlgorithmType(filenameString2))
            assertEquals("SHA-224",utilService.parseHashAlgorithmType(filenameString3))
            assertEquals("SHA-256",utilService.parseHashAlgorithmType(filenameString4))
            assertEquals("SHA-384",utilService.parseHashAlgorithmType(filenameString5))
            assertEquals("SHA-512",utilService.parseHashAlgorithmType(filenameString6))

            // TODO Check for when the file does not have "(algorithm)" on its string name.
            val filenameString7:String = "checksumSHA-800.txt"
            //assertNotEquals("SHA-800",utilService.parseHashAlgorithmType(filenameString7))
            //assertThrows(AssertionFailedError.class, () --> utilService.parseHashAlgorithmType(filenameString7))
            //assertThrows(AssertionFailedError.class, () --> utilService.parseHashAlgorithmType(filenameString7), "checksum txt does not have appropriate format.")
            //assert
            //assertNotEquals()
            //val assertError1:AssertionFailedError

            //assertThrows(AssertionFailedError(), utilService.parseHashAlgorithmType(filenameString7), "checksum format not good.")
        }


    }
}