package com.scott.file_hash_service.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.scott.file_hash_service.model.Hash
import com.scott.file_hash_service.service.UtilitiesService
import com.sun.xml.internal.messaging.saaj.packaging.mime.MultipartDataSource
import com.sun.xml.internal.messaging.saaj.soap.MultipartDataContentHandler
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity.noContent
import org.springframework.http.ResponseEntity.status
import org.springframework.http.codec.multipart.MultipartHttpMessageReader
import org.springframework.web.multipart.MultipartFile
import sun.jvm.hotspot.utilities.Assert
import org.springframework.test.web.servlet.setup.MockMvcBuilders

import org.springframework.test.web.servlet.MockMvc

import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.web.bind.annotation.RequestBody
import java.awt.PageAttributes
import java.lang.Exception
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.multipart.MultipartRequest
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders





@SpringBootTest
@AutoConfigureMockMvc
internal class HashControllerTest @Autowired constructor(
    //val stringUrl = "/api",
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
){

    @Test
    fun addHash() {
    }

    @Test
    fun testEnum() {
    }


    @Autowired
    private val webApplicationContext: WebApplicationContext? = null


    /*@Test
    @DisplayName("POST /api/unzip")
    //@TestInstance(TestInstance.Lifecycle.PER_CLASS)
    fun whenFileUploaded_thenVerifyStatus() {

        // Given
        val fileInput : MultipartFile
        val newHash = Hash("src/main/resources/example-files/example-filesMD5.tar", "MD5", "301a21e444ea1e1f169bb94b1c8bd0a4", ["MD5"])



        //Assert(UtilitiesService.untarFile())

        //val newBank = Bank("acc123", 31.415, 2)
        //val newHash = new Hash

        //val fileInput : MultipartFile



    }*/

/*
    @Test
    @DisplayName("POST /api/unzip")
    @Throws(Exception::class)
    fun unzipTest() {

        val stringUrl = "/api/unzip"

        val hashTest = HashController.addHash(hash1)

        // TODO: Try to see how to upload this.
        /*val file1 = MockMultipartFile(
            "file",
            "example-filesMD5.tar",
            MediaType.TEXT_PLAIN_VALUE,
            "Hello, World!".toByteArray()
        )*/

        //val file2 = MultipartDataContentHandler("/example-filesMD5.tar")

        // Creates the MultipartFile as a mock trial.
        val file = MockMultipartFile(
            "file",
            "hello.txt",
            MediaType.TEXT_PLAIN_VALUE,
            "Hello, World!!!".toByteArray()
        )


        val performPost = mockMvc.post(stringUrl){
            //contentType = MediaType.TEXT_PLAIN_VALUE
            contentType = MediaType.MULTIPART_FORM_DATA
            content = objectMapper.writeValueAsString(file)
        }

        performPost
            .andDo { print() }
            .andExpect { status { isNotFound() } }

    }*/

    @Test
    @DisplayName("POST /api/unzip")
    @Throws(Exception::class)
    fun test() {
        val firstFile = MockMultipartFile("data", "filename.txt", "text/plain", "some xml".toByteArray())
        val secondFile =
            MockMultipartFile("data", "other-file-name.data", "text/plain", "some other type".toByteArray())
        val jsonFile = MockMultipartFile("json", "", "application/json", "{\"json\": \"someValue\"}".toByteArray())
        val mockMvc2 = MockMvcBuilders.webAppContextSetup(webApplicationContext!!).build()
        mockMvc2.perform(
            MockMvcRequestBuilders.multipart("/api/unzip")
                .file(firstFile)
                .file(secondFile)
                .file(jsonFile)
                .param("some-random", "4")
        ).andExpect { status(200) }
            //.andExpect(status().`is`(200))
            //.andExpect(content().string("success"))
    }

    @Test
    @DisplayName("POST2 /api/unzip")
    @Throws(Exception::class)
    fun test2() {
        val stringUrl = "/api/unzip"

        val firstFile = MockMultipartFile("data", "filename.txt", "text/plain", "some xml".toByteArray())
        val secondFile =
            MockMultipartFile("data", "other-file-name.data", "text/plain", "some other type".toByteArray())
        val jsonFile = MockMultipartFile("json", "", "application/json", "{\"json\": \"someValue\"}".toByteArray())

        // when/then

        val performPost = mockMvc.post(stringUrl){
            contentType = MediaType.MULTIPART_FORM_DATA
            content = firstFile
            //content = objectMapper.writeValueAsString(firstFile)
        }

        performPost
            .andDo { print() }
            .andExpect {
                status { isBadRequest() }
            }
    }




    /*
    @Test
    @Throws(Exception::class)
    fun whenFileUploaded_thenVerifyStatus() {
        val file = MockMultipartFile(
            "file",
            "hello.txt",
            MediaType.TEXT_PLAIN_VALUE,
            "Hello, World!".toByteArray()
        )
        val mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext!!).build()
        mockMvc.perform(multipart("/upload").file(file))
            .andExpect(status().isOk())
    }
     */

}