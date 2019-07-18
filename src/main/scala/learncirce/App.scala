package learncirce

import io.circe.Decoder.Result
import io.circe.parser.parse
import io.circe.syntax._
import io.circe.{Json, ParsingFailure}
import learncirce.models.Player

object App {
  def main(args: Array[String]): Unit = {
    val response: String =
      """
        |{
        |  "name": "Kevin Durant",
        |  "jersey": 7,
        |  "isActive": true,
        |  "pos": "F",
        |  "heightMeters": 2.06,
        |  "weightPounds": 239,
        |  "dateOfBirthUTC": "1988-09-29",
        |  "teams": [
        |    {
        |      "teamId": "1610612760",
        |      "seasonStart": "2007",
        |      "seasonEnd": "2015"
        |    },
        |    {
        |      "teamId": "1610612744",
        |      "seasonStart": "2016",
        |      "seasonEnd": "2018"
        |    },
        |    {
        |      "teamId": "1610612751",
        |      "seasonStart": "2019",
        |      "seasonEnd": "2019"
        |    }
        |  ],
        |  "draft": {
        |    "teamId": "1610612760",
        |    "pickNum": 2,
        |    "roundNum": 1,
        |    "seasonYear": "2007"
        |  },
        |  "nbaDebutYear": "2007",
        |  "yearsPro": 12
        |}
      """.stripMargin

    val doc: Either[ParsingFailure, Json] = parse(response)

    val json: Json = doc.getOrElse(Json.Null)

    println(json.spaces4)

    val decodedPlayer: Result[Player] = json.as[Player]

    val encodedPlayer: Json = decodedPlayer.right.get.asJson

    println(encodedPlayer.spaces4)
  }
}
