package com.winkelhake.carlo.app

import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import org.scalatra._
import JsonValidator._
import SchemaManager._
import JsonParser._

class Endpoints extends ScalatraServlet with JacksonJsonSupport {
  
  protected implicit lazy val jsonFormats: Formats = DefaultFormats
  
  // redirect to json output
   before() {
    contentType = formats("json")
  }

  // Download a JSON Schema with unique `SCHEMAID`
  get("/schema/:id") {
    getSchema(params("id")).getOrElse(createResponseJson("downloadSchema", params("id"), "error", "schema not found"))
  }
  
  // Upload a JSON Schema with unique `SCHEMAID`
  // Does not check for existing schema
  post("/schema/:id") {
    println("here\n\n\n")
    val jsonString = request.body
    println(jsonString)
    if (validJsonFormat(jsonString)) {
      saveSchema(params("id"), jsonString) 
      createResponseJson("uploadSchema", params("id"), "success")
    } else {
      createResponseJson("uploadSchema", params("id"), "error", "Invalid JSON")
    }
  }
  
  // Validate a JSON document against the JSON Schema identified by `SCHEMAID`
  post("/validate/:id") {
    val schemaString = getSchema(params("id"))
    if (!schemaString.isEmpty) {
      validateJsonString(params("id"), request.body, schemaString.get)
    } else {
      createResponseJson("validateDocument", params("id"), "error", "schema not found")
    }
  }
}
