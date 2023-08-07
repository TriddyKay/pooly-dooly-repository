package com.tridda.poolydooly

import com.fasterxml.jackson.module.kotlin.readValue
import com.tridda.poolydooly.venues.Venue
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PoolyDoolyApplicationTests: BaseIntegrationTest() {

	private var venue = Venue("tridda", "Tridda's House", 1)

	@BeforeEach
	fun beforeEach() {
		postRequest("/venues/insert", venue)
	}

	@AfterEach
	fun afterEach() {
		deleteRequest("/venues/delete/tridda")
	}

	@Test
	@DirtiesContext
	fun shouldFindVenue() {
		val response = getRequest("/venues/tridda").andReturn().response
		val venueFromDB = objectMapper.readValue<Venue>(response.contentAsString)

		assert(venueFromDB.id == venue.id)
	}

	@Test
	@DirtiesContext
	fun shouldCreateThenRemoveVenue() {
		val venueToAdd = Venue("newVenue", "New Venue", 3)
		postRequest("/venues/insert", venueToAdd).andExpect { status { isOk() } }

		val response = getRequest("/venues/newVenue").andReturn().response
		val venueFromDB = objectMapper.readValue<Venue>(response.contentAsString)
		assert(venueFromDB.id == venueToAdd.id)

		deleteRequest("/venues/delete/newVenue")

		val exception = getRequest("/venues/newVenue").andReturn().resolvedException
		assert(exception!!.message == "Venue not found")
	}
}
