package learncirce.models

import java.time.{LocalDate, Year}

import io.circe.syntax._
import io.circe.{Decoder, Encoder, HCursor, Json}
import learncirce.models.codecs.LocalDateCodec._
import learncirce.models.codecs.YearCodec._

final case class Player(name: String,
                        jersey: Int,
                        isActive: Boolean,
                        heightMeters: Float,
                        weightPounds: Int,
                        dateOfBirthUTC: LocalDate,
                        teams: List[PastTeam],
                        draft: Draft,
                        nbaDebutYear: Year,
                        yearsPro: Int)

object Player {
  implicit val decodePlayer: Decoder[Player] = (c: HCursor) =>
    for {
      name <- c.downField("name").as[String]
      jersey <- c.downField("jersey").as[Int]
      isActive <- c.downField("isActive").as[Boolean]
      heightMeters <- c.downField("heightMeters").as[Float]
      weightPounds <- c.downField("weightPounds").as[Int]
      dateOfBirthUTC <- c.downField("dateOfBirthUTC").as[LocalDate]
      teams <- c.downField("teams").as[List[PastTeam]]
      draft <- c.get[Draft]("draft")
      nbaDebutYear <- c.get[Year]("nbaDebutYear")
      yearsPro <- c.get[Int]("yearsPro")
    } yield
      Player(name,
             jersey,
             isActive,
             heightMeters,
             weightPounds,
             dateOfBirthUTC,
             teams,
             draft,
             nbaDebutYear,
             yearsPro)

  implicit val encodePlayer: Encoder[Player] = (p: Player) =>
    Json.obj(
      ("name", Json.fromString(p.name)),
      ("jersey", Json.fromInt(p.jersey)),
      ("isActive", Json.fromBoolean(p.isActive)),
      ("heightMeters", Json.fromFloat(p.heightMeters).getOrElse(Json.Null)),
      ("weightPounds", Json.fromInt(p.weightPounds)),
      ("dateOfBirthUTC", p.dateOfBirthUTC.asJson),
      ("teams", p.teams.asJson),
      ("draft", p.draft.asJson),
      ("nbaDebutYear", p.nbaDebutYear.asJson),
      ("yearsPro", Json.fromInt(p.yearsPro))
  )
}
