package com.scott.file_hash_service.service

import com.scott.file_hash_service.model.Hash
import net.bytebuddy.implementation.bytecode.assign.Assigner
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
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
import java.lang.AssertionError


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

            // TODO: Understand what is wrong with the checksum for the example-filesMD5.zip archive.
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

        @DisplayName("parseHashAlgorithmType Test")
        @Test
        fun `test parseHashAlgorithmType`() {
            /**
             * Test for string extraction that falls between "(" and ")" of the file name.
             */
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
        }

        //processIncomingFile
        @DisplayName("processIncomingFile Test")
        @Test
        fun `test processIncomingFile`() {
            /**
             * Test whether or not the hash of the checksum inside of these compressed archives is equivalent to the
             * hash that gets produced by bouncy castle computation.
             */

            val file1 = File("src/main/resources/example-files/example-filesMD5.tar")
            val multipartFile1 = returnMultiPartFile(file1)
            val file2 = File("src/main/resources/example-files/example-filesMD5.zip")
            val multipartFile2 = returnMultiPartFile(file2)
            val file3 = File("src/main/resources/example-files/example-filesSHA1.zip")
            val multipartFile3 = returnMultiPartFile(file3)
            val file4 = File("src/main/resources/example-files/example-filesSHA256.zip")
            val multipartFile4 = returnMultiPartFile(file4)
            val file5 = File("src/main/resources/example-files/example-filesSHA512.tar")
            val multipartFile5 = returnMultiPartFile(file5)

            println("multipartFile: ${multipartFile1.originalFilename}")
            println("multipartFile: ${multipartFile2.originalFilename}")
            println("multipartFile: ${multipartFile3.originalFilename}")
            println("multipartFile: ${multipartFile4.originalFilename}")
            println("multipartFile: ${multipartFile5.originalFilename}")

            assertEquals(true,utilService.processIncomingFile(multipartFile1))
            //assertEquals(true,utilService.processIncomingFile(multipartFile2))
            assertEquals(true,utilService.processIncomingFile(multipartFile3))
            assertEquals(true,utilService.processIncomingFile(multipartFile4))
            assertEquals(true,utilService.processIncomingFile(multipartFile5))
        }

        fun returnMultiPartFile(file:File) : MultipartFile {
            /**
             * Helper function used to take in a file type and convert it into MultipartFile type.
             */
            val input = FileInputStream(file)
            val multipartFile: MultipartFile = MockMultipartFile(
                "file",
                file.name, "text/plain", input
            )
            return multipartFile
        }


        @DisplayName("parseHashAlgorithmType Fail")
        @Test
        fun `test parseHashAlgorithmType Fail`(){
            /**
             * The parseHashAlgorithmType function accepts any type of string. Assumption for now needs to be that the
             * checksum file names include the "(algorithm)" in their string.
             */

            val filenameString1:String = "checksumSHA-800.txt"
            println(filenameString1)

            Assertions.assertThrows(AssertionFailedError::class.java){
                assertEquals("SHA-800",utilService.parseHashAlgorithmType(filenameString1))
            }

            // Instead of returning "SHA-800" it will return the entire string "checksumSHA-800.txt"
            val result = utilService.parseHashAlgorithmType(filenameString1)
            println("result: $result")
        }

        @DisplayName("processIncomingFile Fail")
        @Test
        fun `test processIncomingFile Fail`(){
            /**
             * The processIncomingFile function takes in MultipartFile as a parameter. Assumption for now needs to be
             * that the Multipart file it takes in is a compressed archive [.tar, .zip, etc..], if not it will fail
             * against the ArchiveFactory since that is what it is expecting.
             */

            val file1 = File("src/main/resources/example-files/keyboard-cat.jpg")
            val multipartFile1 = returnMultiPartFile(file1)

            println("multipartFile: ${multipartFile1.originalFilename}")

            // The IllegalArgumentException error is thrown by the ArchiverFactory within the untar function
            // because the ArchiverFactory will only accept archive types [.tar, zip, gzip, etc..]
            Assertions.assertThrows(IllegalArgumentException::class.java){
                utilService.processIncomingFile(multipartFile1)
            }
        }
    }
}