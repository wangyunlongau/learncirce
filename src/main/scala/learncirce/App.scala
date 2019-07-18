package learncirce

import io.circe.parser.parse
import io.circe.{Json, ParsingFailure}

object App {
  def main(args: Array[String]): Unit = {
    val json: String =
      """
        |{
        |  "firstName": "Kevin",
        |  "lastName": "Durant",
        |  "jersey": 7,
        |  "isActive": true,
        |  "pos": "F",
        |  "heightMeters": 2.06,
        |  "weightPounds": 240,
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

    val doc: Either[ParsingFailure, Json] = parse(json)

    println(doc.right.get.spaces4)
  }
}
