package com.scott.file_hash_service.service

import com.scott.file_hash_service.HashAlgorithmType
import org.rauschig.jarchivelib.*
import org.springframework.stereotype.Service
import java.io.File
import java.util.*
import org.rauschig.jarchivelib.ArchiverFactory
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.web.multipart.MultipartFile
import javax.servlet.ServletContext
import kotlin.collections.HashMap


@Service
class UtilitiesService {

    @Autowired
    lateinit var servletContext: ServletContext

    fun untarFile(fileInput: MultipartFile): Triple<File, String, String>{
        /*
        *  fileInput: Expected parameter is an archive or compressed archive.
        *  TODO: Function still needs exception handlers in case it is not an archive. Checks for tar.
        * */
        println("Entering UtilitiesService untarFile function")
        println(fileInput)
        println(fileInput.javaClass.name)
        val destDir:File = File("/Users/slowr/Desktop/file_hash_temp/extracted_files")//"/Users/rod/Documents/BAE Systems/PlushSamurai/TestingDirectory/TestDropTAR")

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

        val originalFilename = fileInput.originalFilename
//        val fileInputLocalSave: File = File("/Users/slowr/Documents/$originalFilename")//"/Users/rod/Documents/BAE Systems/PlushSamurai/TestingDirectory/holder.txt")
        val fileInputLocalSave: File = File("$originalFilename")//"/Users/rod/Documents/BAE Systems/PlushSamurai/TestingDirectory/holder.txt")
        //val fileIn = fileInput.transferTo(fileInputDirectoryHolder)

        fileInput.transferTo(fileInputLocalSave)

        // Untars/unzips the file and drops it inside the destDir directory.
        archiver.extract(fileInputLocalSave, destDir)

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
        val extractedFile = fileInputLocalSave
        val hashAlgorithmType: String = parseHashAlgorithmType("checksum(MD5).txt")
        return Triple(extractedFile, "", hashAlgorithmType)
    }

    fun parseHashAlgorithmType(fileName: String) : String {
        val hashAlgoType: String = fileName.substringAfter("(").substringBefore(")")
        return hashAlgoType
    }

    /**
     * Returns `true` if enum T contains an entry with the specified name.
     */
    fun enumContains(algorithmType: String): Boolean {
        val enumExists: Boolean = enumValues<HashAlgorithmType>().any {
            println("THis is it: ${it.algorithmType}")
            println("This is algotyhpe passed in: $algorithmType")
            it.algorithmType == algorithmType
        }
        return enumExists
    }



    fun helperFunction(){
        //println("Testing helper function...")
    }
}