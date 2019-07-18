package learncirce.models.codecs

import java.time.LocalDate

import cats.syntax.either._
import io.circe.Decoder

object LocalDateCodec {
  implicit val decodeLocalDate: Decoder[LocalDate] =
    Decoder.decodeString.emap(
      str =>
        Either
          .catchNonFatal(LocalDate.parse(str))
          .leftMap(_ => s"Fail to parse $str as LocalDate"))
}
