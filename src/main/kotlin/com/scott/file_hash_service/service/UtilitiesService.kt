package com.scott.file_hash_service.service

import org.rauschig.jarchivelib.*
import org.springframework.stereotype.Service
import java.io.File
import java.util.*
import org.rauschig.jarchivelib.ArchiverFactory

import org.springframework.web.multipart.MultipartFile


@Service
class UtilitiesService {


    fun untarFile(fileInput: MultipartFile /*, algorithm: String*/) {
        /*
        *  fileInput: Expected parameter is an archive or compressed archive.
        *  TODO: Function still needs exception handlers in case it is not an archive. Checks for tar.
        * */

        println("Entering UtilitiesService untarFile function")
        println(fileInput)
        println(fileInput.javaClass.name)

        val destDir:File = File("/Users/rod/Documents/BAE Systems/PlushSamurai/TestingDirectory/TestDropTAR")

        println(destDir)
        println(destDir.javaClass.name)

        // SOURCE INFO: README from https://github.com/thrau/jarchivelib
        //Create a new Archiver to handle tar archives.
        // Works on archive1.tar example.
        //val archiver = ArchiverFactory.createArchiver(ArchiveFormat.TAR)

        // The ArchiveFactory can also detect archive types based on file extensions and hand you the correct Archiver.
        // Tested and can be prone to failure because not all compressed archives have gz ending.
        // val archiver = ArchiverFactory.createArchiver(fileInput)

        // TODO: Identify whether or not input file is compressed to identify which archiver to use.

        // Create a new Archiver to handle tar archives with gzip compression
        // Works on compressed_archive1.tar example.
        val archiver = ArchiverFactory.createArchiver(ArchiveFormat.TAR, CompressionType.GZIP)


//        fileInput.transferTo(destDir) we would need to do this to save the file to a location to create it as a File then pass that into the extract()
        // Untars/unzips the file and drops it inside the destDir directory.
        archiver.extract(fileInput, destDir) // TODO the fileInput is a MultipartFile which allows you to work on it before saving it somewhere. we would need to convert it to a File to send it to extract().

        val dir: File = destDir
        val directoryListing = dir.listFiles()
        if (directoryListing != null) {

            // Iterates each of the files.
            for (child in directoryListing) {
                // Do something with child
                println(child.name)
                println(child.javaClass.name)

                // Reads content of file.
                val sc = Scanner(child)
                while (sc.hasNextLine())
                    println(sc.nextLine());
            }
        } else {
            // Handle the case where dir is not really a directory.
            // Checking dir.isDirectory() above would not be sufficient
            // to avoid race conditions with another process that deletes
            // directories.
        }

        // OR can extract on the spot using stream.
        /*val stream : ArchiveStream = archiver.stream(fileInput)
        var entry: ArchiveEntry?

        while( stream.nextEntry  != null){
            println("test")
            println(stream.currentEntry.name)
        }
        stream.close()*/


        println("Finished extraction.")
    }

    fun helperFunction(){
        //println("Testing helper function...")
    }
}