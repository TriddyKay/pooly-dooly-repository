package com.tridda.poolydooly

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class BaseIntegrationTest {
  @LocalServerPort
  private val port: Int = 8000

  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  @Autowired
  lateinit var mockMvc: MockMvc
  var objectMapper: ObjectMapper = jacksonObjectMapper()

  fun getRequest(uri: String): ResultActionsDsl {
    return mockMvc.get(createURLWithPort(uri))
  }

  fun postRequest(uri: String, postBody: Any): ResultActionsDsl {
    return mockMvc.post(createURLWithPort(uri)) {
      contentType = MediaType.APPLICATION_JSON
      content = objectMapper.writeValueAsString(postBody)
    }
  }

  fun deleteRequest(uri: String): ResultActionsDsl {
    return mockMvc.delete(createURLWithPort(uri))
  }

  private fun createURLWithPort(uri: String): String {
    return "http://localhost:$port$uri"
  }
}
