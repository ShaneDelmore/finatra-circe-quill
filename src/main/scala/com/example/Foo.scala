package com.example

import java.time.{LocalDate, OffsetDateTime}

case class Foo(
                          id: Long,
                          firstName: String,
                          lastName: String,
                          dateOfBirth: LocalDate
                        )
