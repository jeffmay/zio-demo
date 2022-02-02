package com.rallyhealth.pzn.io
package server.model

import zio.schema.{DefaultJavaTimeSchemas, Schema, StandardType}

import java.time.LocalDate
import java.time.format.DateTimeFormatter

trait SimpleLocalDateSchema {
  /** Shadows [[DefaultJavaTimeSchemas.localDateSchema]] */
  implicit val localDateSchema: Schema[LocalDate] = Schema.primitive(StandardType.LocalDate(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
}
