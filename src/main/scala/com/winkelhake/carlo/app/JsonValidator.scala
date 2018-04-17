package com.winkelhake.carlo.app

import com.fasterxml.jackson.databind.{JsonNode}
import com.github.fge.jsonschema.main.JsonSchemaFactory

import JsonParser._

object JsonValidator {

  private lazy val jsonSchemaFactory = JsonSchemaFactory.byDefault
  
  def validJsonFormat(jsonString: String): Boolean = {
    !parseJsonString(jsonString).isEmpty
  }
  
  def validateJsonString(id: String, jsonString: String, schemaString: String): JsonNode = {
    
    val schemaObj = parseJsonString(schemaString)
    val jsonObj =  parseJsonString(jsonString)
    
    if (!schemaObj.isEmpty && !jsonObj.isEmpty) {
      val schema =  jsonSchemaFactory.getJsonSchema(schemaObj.get)
      val report = schema.validate(jsonObj.get)

      var reportString = report.toString.replace("\n", "\\n")
      reportString = reportString.replace("\"", "\\\"")

      if(report.isSuccess) {
        createResponseJson("validateDocument", id, "success")
      } else {
        createResponseJson("validateDocument", id, "error", reportString)
      } 
    } else {
      if (jsonObj.isEmpty) {
        createResponseJson("validateDocument", id, "error", "The submitted JSON could not be parsed correctly.")
      } else {
        createResponseJson("validateDocument", id, "error", "The schema to compare with contains errors.")
      }
    }
  }
}
