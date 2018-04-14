package com.winkelhake.carlo.app

import org.scalatra._

class MyScalatraServlet extends ScalatraServlet {

  get("/schema/:id") {
    params("id")
  }
}
