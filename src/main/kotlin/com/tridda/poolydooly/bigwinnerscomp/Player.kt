package com.tridda.poolydooly.bigwinnerscomp

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document("big_winners_comp")
data class BigWinnersComp(
    val id: String = ObjectId().toString(),
    val compName: String,
    val players: List<Player>
)

data class Player (
    val id: String = ObjectId().toString(),
    val name: String,
    val wins: Int
)
