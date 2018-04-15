package com.winkelhake.carlo.app

import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}
import com.github.fge.jsonschema.main.JsonSchemaFactory


object JsonValidator {
 
  private lazy val mapper = new ObjectMapper()
  private lazy val jsonSchemaFactory = JsonSchemaFactory.byDefault
  
  def validateJsonStringAgainstSchemaString(schemaString: String, jsonString: String) = {
    
    val schema =  jsonSchemaFactory.getJsonSchema(parseJsonString(schemaString))
    val json =  parseJsonString(jsonString)
    
    val report = schema.validate(json)
    println(report.isSuccess)
    println(report)
  }
  
  def test(test: String): JsonNode = {
    parseJsonString(test)
  }
  
  def validateJsonString(): Boolean = {
    true
  }
  
  def validateSchema(): Boolean = {
    true
  }
  
  def parseJsonString(data: String): JsonNode = {
    mapper.readTree(data)
  }
}
