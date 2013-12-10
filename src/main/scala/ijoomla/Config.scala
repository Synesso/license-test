package ijoomla

import java.util.Properties
import java.io.FileReader
import scala.xml.{NodeSeq, XML}

object Config {

  val properties = new Properties()
  properties.load(new FileReader(".env"))

  lazy val phantomJsBinary = properties.getProperty("PHANTOMJS_BIN")
  lazy val baseUrl = properties.getProperty("BASE_URL")
  lazy val jsPromo = properties.getProperty("JS_PROMO")

  object JomSocial {

    lazy val newUser = {
      val <user>
        <firstname>{firstname}</firstname>
        <lastname>{lastname}</lastname>
        <email>{email}</email>
        <username>{username}</username>
        <password>{password}</password>
        <streetaddress>{streetaddress}</streetaddress>
        <country>{country}</country>
        <state>{state}</state>
        <city>{city}</city>
        <postalcode>{postcode}</postalcode>
        <card>
          <type>{brand}</type>
          <number>{number}</number>
          <ccv>{sec}</ccv>
          <expiry>
            <month>{month}</month>
            <year>{year}</year>
          </expiry>
        </card>
      </user> = XML.loadString(properties.getProperty("NEW_USER"))

      User(firstname.text, lastname.text, email.text, username.text, password.text,
        Address(streetaddress.text, city.text, state.text, postcode.text, country.text),
        Card(brand.text, number.text, sec.text, Expiry(month.text, year.text)))
    }


  }
}
