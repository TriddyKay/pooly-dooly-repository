package com.tridda.poolydooly.bigwinnerscomp

import org.springframework.data.mongodb.repository.MongoRepository

interface BigWinnersCompDAO : MongoRepository<BigWinnersComp, String> {
  fun findBigWinnersCompById(id: String): BigWinnersComp?
  fun insert(bigWinnersComp: BigWinnersComp): BigWinnersComp
  override fun findAll(): List<BigWinnersComp>
  override fun deleteById(id: String)
}
