package learncirce.models.codecs

import java.time.Year

import cats.syntax.either._
import io.circe.{Decoder, Encoder, Json}

object YearCodec {
  implicit val decodeYear: Decoder[Year] =
    Decoder.decodeString.emap(
      str =>
        Either
          .catchNonFatal(Year.parse(str))
          .leftMap(_ => s"Fail to parse $str as LocalDate"))

  implicit val encodeYear: Encoder[Year] =
    (y: Year) => Json.fromString(y.toString)
}
