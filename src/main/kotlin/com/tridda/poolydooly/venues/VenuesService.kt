package com.tridda.poolydooly.venues

import com.tridda.poolydooly.exceptions.Exceptions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class VenuesService {

  @Autowired
  private lateinit var venuesDAO: VenuesDAO

  fun findAll(): List<Venue> {
      return venuesDAO.findAll()
  }

  fun findVenueById(id: String): Venue? {
    return venuesDAO.findVenueById(id) ?: throw Exceptions.VenueNotFoundException()
  }

  fun insertVenue(venue: Venue): Any {
    try {
      return venuesDAO.insert(venue)
    } catch(e: Exception) {
      throw e
    }
  }

  fun deleteVenue(id: String): Any {
    return venuesDAO.deleteById(id)
  }
}
