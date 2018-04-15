package com.winkelhake.carlo.app

import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}
import com.github.fge.jsonschema.main.JsonSchemaFactory


object JsonValidator {
 
  private lazy val mapper = new ObjectMapper()
  private lazy val jsonSchemaFactory = JsonSchemaFactory.byDefault
  
  def validateJsonStringAgainstSchemaString(schemaString: String, jsonString: String) = {
//    
//    val schema =  jsonSchemaFactory.getJsonSchema(parseJsonString(schemaString))
//    val json =  parseJsonString(jsonString)
//    
//    val report = schema.validate(json)
//    println(report.isSuccess)
//    println(report)
  }
  
  def test(test: String): Boolean = {
    
    
    val testString = """{
    "action": "uploadSchema",
    "id": "config-schema",
    "status": "success"
    }"""
    
    var jsonObj: Option[JsonNode] = None
    
    try {
      jsonObj = parseJsonString(testString) 
      true
    
    } catch {
      case e: Exception => {
        println(e)
        false
      }
    }    
    
    
  }

//  def isJsonNode(x: Any): Boolean = x match {
//    case j: JsonNode => true
//    case _ => false
//  }
  
  def validJsonString(jsonString: String): Boolean = {
    parseJsonString(jsonString) != None
  }
  
  def validateJsonString(): Boolean = {
    true
  }
  
  def validateSchema(): Boolean = {
    true
  }
  
  def parseJsonString(data: String): Option[JsonNode] = {
    try {
      Some(mapper.readTree(data))  
    } catch {
      case e: Exception => None
    }
  }
}
