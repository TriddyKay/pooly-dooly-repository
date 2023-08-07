package com.tridda.poolydooly.venues

import org.springframework.data.mongodb.repository.MongoRepository

interface VenuesDAO : MongoRepository<Venue, String> {
  fun findVenueById(id: String): Venue?
  fun insert(restaurant: Venue): Venue
  override fun findAll(): List<Venue>
  override fun deleteById(id: String)
}
