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
    getSchema(params("id")).getOrElse(createResponseJson("1", "2", "3"))
  }
  
  // Upload a JSON Schema with unique `SCHEMAID`
  // Does not check for existing schema
  post("/schema/:id") {
    val jsonString = request.body
    if (validJsonFormat(jsonString)) {
      saveSchema(params("id"), jsonString) 
      createResponseJson("1", "2", "3")
    } else {
      createResponseJson("1", "2", "3")
    }
  }
  
  // Validate a JSON document against the JSON Schema identified by `SCHEMAID`
  post("/validate/:id") {
    val schemaString = getSchema(params("id"))
    if (!schemaString.isEmpty) {
      validateJsonString(request.body, schemaString.get)
    } else {
      createResponseJson("1", "2", "3")
    }
  }
}
