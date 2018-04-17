package com.winkelhake.carlo.app

import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}
import com.fasterxml.jackson.annotation.JsonInclude

object JsonParser {
  
  private lazy val mapper = new ObjectMapper()
  mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
  
  def parseJsonString(data: String): Option[JsonNode] = {
    try {
      Some(mapper.readTree(data))
    } catch {
      case e: Exception => None
    }
  }
  
  def createResponseJson(action: String, id: String, status: String, message: String = ""): JsonNode = {
    
    var responseString: String = "{ \"action\" : \"" + action+"\", \"id\" : \"" + id+"\", \"status\" : \"" + status+"\""

    if (message != "") {
      responseString += ", \"message\" : \"" + message+"\"}"
    } else {
      responseString += "}"
    }
    
    mapper.readTree(responseString)
  }
}