package com.tridda.poolydooly.venues

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
class VenuesResource {

  @Autowired
  private lateinit var venuesService: VenuesService

  @GetMapping("/venues")
  fun findAll(): List<Venue> {
    return venuesService.findAll()
  }

  @GetMapping("/venues/{id}")
  fun findVenueById(@PathVariable id: String): Venue? {
    try {
      return venuesService.findVenueById(id)
    } catch(e: Exception) {
      throw e
    }
  }

  @PostMapping("/venues/insert")
  fun insertVenue(@RequestBody venue: Venue): Venue {
    return venuesService.insertVenue(venue)
  }

  @DeleteMapping("venues/delete/{id}")
  fun deleteVenue(@PathVariable id: String): Any {
    return venuesService.deleteVenue(id)
  }
}
