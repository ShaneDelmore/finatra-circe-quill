package com.example

import io.circe.java8._
import java.time.{LocalDate, LocalDateTime}
import java.time.format.DateTimeFormatter

import io.circe.{Decoder, Encoder, Json}

object CirceJava8Extensions {
  //Create new encoder and decoder by mapping from existing
  implicit val e: Encoder[LocalDate] = Encoder
    [LocalDateTime].contramap(_.atStartOfDay)

  implicit val d: Decoder[LocalDate] =
    Decoder[LocalDateTime].map(_.toLocalDate)

  //Create new encoders from scratch
//  final def encodeLocalDate(formatter: DateTimeFormatter): Encoder[LocalDate] =
//    Encoder.instance(time => Json.string(time.atStartOfDay.format(formatter)))
//
//  implicit final val encodeLocalDateDefault: Encoder[LocalDate] =
//    encodeLocalDate(DateTimeFormatter.BASIC_ISO_DATE)
}


