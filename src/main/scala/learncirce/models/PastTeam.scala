package learncirce.models

import java.time.Year

import codecs.YearCodec._
import io.circe.generic.semiauto._
import io.circe.{Decoder, Encoder}

final case class PastTeam(teamId: String, seasonStart: Year, seasonEnd: Year)

object PastTeam {
  implicit val decodePastTeam: Decoder[PastTeam] = deriveDecoder

  implicit val encodePastTeam: Encoder[PastTeam] = deriveEncoder
}
