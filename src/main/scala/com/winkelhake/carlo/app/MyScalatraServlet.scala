package com.winkelhake.carlo.app

import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import org.scalatra._
import JsonValidator._

class MyScalatraServlet extends ScalatraServlet with JacksonJsonSupport {
  
  protected implicit lazy val jsonFormats: Formats = DefaultFormats
  
  // redirect to json output
   before() {
    contentType = formats("json")
  }

  // Download a JSON Schema with unique `SCHEMAID`
  get("/schema/:id") {
    List("test")
  }
  
  // Upload a JSON Schema with unique `SCHEMAID`
  post("/schema/:id") {
    val jsonString = request.body
    
    jsonString
  }
  
  // Validate a JSON document against the JSON Schema identified by `SCHEMAID`
  post("/validate/:id") {
    List("test")
  }
}
