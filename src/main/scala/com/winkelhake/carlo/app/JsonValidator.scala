package com.winkelhake.carlo.app

import com.fasterxml.jackson.databind.{JsonNode}
import com.github.fge.jsonschema.main.JsonSchemaFactory

import JsonParser._

object JsonValidator {

  private lazy val jsonSchemaFactory = JsonSchemaFactory.byDefault
  
  def validJsonFormat(jsonString: String): Boolean = {
    !parseJsonString(jsonString).isEmpty
  }
  
  def validateJsonString(jsonString: String, schemaString: String): Unit = {
    
    val schemaObj = parseJsonString(schemaString)
    val jsonObj =  parseJsonString(jsonString)
    
    if (!schemaObj.isEmpty && !jsonObj.isEmpty) {
      val schema =  jsonSchemaFactory.getJsonSchema(schemaObj.get)
      val report = schema.validate(jsonObj.get)
      println(report)
      
      if(report.isSuccess) {
        jsonObj.get
      } else {
        createResponseJson("1", "2", "3")
      } 
    } else {
      createResponseJson("1", "2", "3")
    }
  }
}
