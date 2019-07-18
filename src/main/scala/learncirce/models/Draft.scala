package learncirce.models

import java.time.Year

import codecs.YearCodec._
import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto._

final case class Draft(teamId: String, pickNum: Int, roundNum: Int, seasonYear: Year)

object Draft {
  implicit val decodeDraft: Decoder[Draft] = deriveDecoder

  implicit val encodeDraft: Encoder[Draft] = deriveEncoder
}
