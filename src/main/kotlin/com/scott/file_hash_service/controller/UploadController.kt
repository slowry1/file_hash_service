package com.scott.file_hash_service.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

@Controller
class UploadController {

    //Save the uploaded file to this folder /Users/rod/Documents/BAE Systems/PlushSamurai/TestingDirectory/TestDrop
    //private static String UPLOADED_FOLDER = "F://temp//";
    private val UPLOADED_FOLDER = "/Users/slowr/Desktop/upload_example_text/saved_from_frontend/"


    @GetMapping("/")
    fun index(): String? {
        return "upload"
    }

    @PostMapping("/upload") // //new annotation since 4.3
    fun singleFileUpload(
        @RequestParam("file") file: MultipartFile//,
//        redirectAttributes: RedirectAttributes
    ): String? {
        if (file.isEmpty) {
//            redirectAttributes.addFlashAttribute("message", "Please select a file to upload")
            return "redirect:uploadStatus"
        }
        try {

            // Get the file and save it somewhere
            val bytes = file.bytes
            val path = Paths.get(UPLOADED_FOLDER + file.originalFilename)
            Files.write(path, bytes)
//            redirectAttributes.addFlashAttribute(
//                "message",
//                "You successfully uploaded '" + file.originalFilename + "'"
//            )
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return "redirect:/uploadStatus"
    }

    @GetMapping("/uploadStatus")
    fun uploadStatus(): String? {
        return "uploadStatus"
    }

}