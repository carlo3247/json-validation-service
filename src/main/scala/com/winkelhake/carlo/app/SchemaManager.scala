package com.winkelhake.carlo.app

import com.fasterxml.jackson.databind.ObjectMapper
import java.io._

object SchemaManager {  
  
  private val PATH = "/src/main/resources/schemas/"
  private lazy val mapper = new ObjectMapper()

  def saveSchema(id: String, jsonString: String): Boolean = {
    try {
      mapper.writeValue(new File(System.getProperty("user.dir") + PATH + id + ".json"), jsonString)
      true
    } catch {
      case e: Exception => false
    } 
  }
  
  def getSchema(id: String): Option[String] = {
    try {
      Some(mapper.readValue(new File(System.getProperty("user.dir") + PATH + id + ".json"), classOf[String]))
    } catch {
      case e: Exception => None
    }
  }
}