package com.scott.file_hash_service.controller

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.multipart.MultipartException
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@ControllerAdvice
class GlobalExceptionHandler {
    //https://jira.spring.io/browse/SPR-14651
    //4.3.5 supports RedirectAttributes redirectAttributes
    @ExceptionHandler(MultipartException::class)
    fun handleError1(e: MultipartException, redirectAttributes: RedirectAttributes): String? {
        redirectAttributes.addFlashAttribute("message", e.cause!!.message)
        return "redirect:/uploadStatus"
    }
}