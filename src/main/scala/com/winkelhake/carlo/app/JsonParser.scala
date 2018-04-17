package com.winkelhake.carlo.app

import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.annotation.JsonInclude

import java.util.Map.Entry
import collection.JavaConverters._

/*
 * mapper.setSerializationInclusion(Include.NON_NULL)
 * Serialization including NON_NULL not working because of a direct conversion to Json Node
 */

object JsonParser {
  
  private lazy val mapper = new ObjectMapper()
  
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
  
//  private def cleanJsonNode(node: JsonNode): Unit = {
//
//    val iterator: Iterator[Entry[String, JsonNode]] = node.fields().asScala
//    
//    while(iterator.hasNext) {
//      val entry: Entry[String, JsonNode] = iterator.next()
//      println(s"${entry.getKey}=${entry.getValue} ofType: ${entry.getValue.getClass}")
//      if(entry.getValue.isNull) {
//        println(s"IS NULL: ${entry.getKey}=${entry.getValue}")
////        println(node.asInstanceOf[ObjectNode].remove(entry.getKey))
////        x.remove(entry.getKey)
//      } else {
//        cleanJsonNode(entry.getValue)
//      }
//    }
//  }
}