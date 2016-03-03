package com.example

import java.time._
import io.getquill._
import io.getquill.sources.sql.idiom.PostgresDialect
import io.getquill.naming.SnakeCase
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object quill {
  //Teach quill how to decode a LocalDate
  implicit val decodeLocalDate = mappedEncoding[java.util.Date, java.time.LocalDate](
    _.toInstant().atZone(ZoneId.systemDefault()).toLocalDate
  )

  lazy val db =  source(new PostgresAsyncSourceConfig[SnakeCase]("db"))

  val getFoo = quote { (id: Long) =>
    query[Foo].filter(_.id == id)
  }

  def resultFuture(fooId: Long): Future[List[Foo]] =
    Future(List(Foo(1L, "Hello", "World", LocalDate.now())))
//    db.run(getFoo)(fooId)
}
