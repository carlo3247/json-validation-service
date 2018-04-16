package com.winkelhake.carlo.app

import com.fasterxml.jackson.databind.ObjectMapper
import java.io._

object SchemaManager {  
  
  private val PATH = "src/main/resources/schemas/"
  private lazy val mapper = new ObjectMapper()

  def saveSchema(id: String, jsonString: String) = {
    try {
      mapper.writeValue(new File(PATH + id + ".json"), jsonString)
    } catch {
      case e: Exception => println(e)
    } 
  }
  
  def getSchema(id: String): Option[String] = {
    try {
      Some(mapper.readValue(new File(PATH + id + ".json"), classOf[String]))
    } catch {
      case e: Exception => None
    }
  }
}