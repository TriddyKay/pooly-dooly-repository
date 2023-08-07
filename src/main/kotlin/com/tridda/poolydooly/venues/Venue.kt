package com.tridda.poolydooly.venues

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document("venues")
data class Venue(
  val id: String = ObjectId().toString(),
  val name: String,
  val rating: Int
)
