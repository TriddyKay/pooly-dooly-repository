package com.tridda.poolydooly.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ControllerAdvice
class ExceptionController {

  @ExceptionHandler
  fun handleIllegalStateException(ex: Exceptions): ResponseEntity<ErrorMessage> {
    val errorMessage = ErrorMessage(
      ex.httpStatus,
      ex.message
    )

    return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
  }
}

open class Exceptions(
  var httpStatus: Int?,
  override var message: String?,
): RuntimeException(message) {

  class VenueNotFoundException(httpStatus: Int = HttpStatus.NOT_FOUND.value(), message: String = "Venue not found")
    : Exceptions(httpStatus, message)

  class BigWinnersCompNotFoundException(httpStatus: Int = HttpStatus.NOT_FOUND.value(), message: String = "Comp not found")
    : Exceptions(httpStatus, message)
}
