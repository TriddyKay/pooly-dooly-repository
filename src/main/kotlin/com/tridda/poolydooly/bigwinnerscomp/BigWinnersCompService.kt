package com.tridda.poolydooly.bigwinnerscomp

import com.tridda.poolydooly.exceptions.Exceptions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BigWinnersCompService {

  @Autowired
  private lateinit var bigWinnersCompDAO: BigWinnersCompDAO

  fun findAll(): List<BigWinnersComp> {
    return bigWinnersCompDAO.findAll()
  }

  fun findByVenueId(id: String): BigWinnersComp? {
    return bigWinnersCompDAO.findBigWinnersCompById(id) ?: throw Exceptions.BigWinnersCompNotFoundException()
  }

  fun insertBigWinnerComp(bigWinnersComp: BigWinnersComp): BigWinnersComp {
    return bigWinnersCompDAO.insert(bigWinnersComp)
  }

  fun deleteBigWinnersComp(id: String): Any {
    return bigWinnersCompDAO.deleteById(id)
  }
}
