package com.winkelhake.carlo.app

import com.fasterxml.jackson.databind.{JsonNode}
import com.github.fge.jsonschema.core.report.ProcessingReport
import com.github.fge.jsonschema.main.JsonSchemaFactory

import JsonParser._

object JsonValidator {

  private lazy val jsonSchemaFactory = JsonSchemaFactory.byDefault
  
  def validJsonFormat(jsonString: String): Boolean = {
    !parseJsonString(jsonString).isEmpty
  }
  
  def validateJsonString(id: String, jsonString: String, schemaString: String): (Boolean, String) = {
    
    val schemaObj = parseJsonString(schemaString)
    val jsonObj =  parseJsonString(jsonString)
    
    if (!schemaObj.isEmpty && !jsonObj.isEmpty) {
      val schema =  jsonSchemaFactory.getJsonSchema(schemaObj.get)
      val report = schema.validate(jsonObj.get)

      if(report.isSuccess) {
        (true, "")
      } else {
        (false, extractErrorMessage(report))
      } 
    } else {
      if (jsonObj.isEmpty) {
        (false, "The submitted JSON could not be parsed correctly.")
      } else {
        (false, "The schema to compare with contains errors.")
      }
    }
  }
  
  private def extractErrorMessage(report: ProcessingReport): String = {
    var reportString = report.toString.replace("\n", "\\n")
    reportString = reportString.replace("\"", "'")

    val regExp = """error:([a-zA-Z0-9 :\(\)\[\]',]*)""".r    
    regExp.findFirstIn(reportString).getOrElse("X")
  }
}
