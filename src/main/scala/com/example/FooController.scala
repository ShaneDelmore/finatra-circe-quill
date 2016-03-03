package com.example

import java.time.{LocalDate, LocalDateTime}
import java.time.format.DateTimeFormatter
import com.twitter.util.{Future => TwitterFuture}
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import com.twitter.bijection.twitter_util.UtilBijections.twitter2ScalaFuture
import com.twitter.bijection.Conversion.asMethod
import language.implicitConversions
import com.example.CirceJava8Extensions._
import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._
import io.circe._
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._
import cats.data.Xor
import io.circe.java8._


import scala.concurrent.ExecutionContext.Implicits.global

class FooController extends Controller {

  get("/foo/:id") { request: Request =>
    val fooId = request.params("id").toLong

    quill.resultFuture(fooId)
      //Just .as would work if inference could be
//      .as
      //With explicit typing it works
            .as[TwitterFuture[List[Foo]]]
      //This boilerplate is because I am not using a supported ObjectMapper
      .map(_.asJson.toString)
  }

  get("/health/lb") { request: Request =>
    "OK"
  }
}
