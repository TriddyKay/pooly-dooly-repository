package com.tridda.poolydooly.bigwinnerscomp

import com.tridda.poolydooly.exceptions.Exceptions
import de.flapdoodle.os.common.types.Either
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
class BigWinnersCompResource {

  @Autowired
  private lateinit var bigWinnersCompService: BigWinnersCompService

  @GetMapping("/big-winners")
  fun findAll(): List<BigWinnersComp> {
    return bigWinnersCompService.findAll()
  }

  @GetMapping("/big-winners/{id}")
  //Put exception handler here
  @ExceptionHandler(Exceptions.BigWinnersCompNotFoundException::class)
  fun findByVenueId(@PathVariable id: String): BigWinnersComp? {
    try {
//      return ResponseEntity.ok(categoryService.findAll())
      return bigWinnersCompService.findByVenueId(id)
    } catch (exception: Exception) {
      throw exception
    }
  }

  @PostMapping("/big-winners/insert")
  fun insertVenue(@RequestBody bigWinner: BigWinnersComp): Any {
    return bigWinnersCompService.insertBigWinnerComp(bigWinner)
  }

  @DeleteMapping("big-winners/delete/{id}")
  fun deleteVenue(@PathVariable id: String): ResponseEntity<Unit> {
    try {
      return bigWinnersCompService.deleteBigWinnersComp(id)
    } catch (e: Exception) {
      throw
    }
  }

//  try {
//    if (dogRepository.exists(id)) {
//      dogRepository.delete(id)
//    }
//    return ResponseEntity.ok().build()
//  } catch (e: Exception) {
//    return ResponseEntity.notFound().build()
//  }
}
