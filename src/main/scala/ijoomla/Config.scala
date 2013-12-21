package ijoomla

import java.util.Properties
import java.io.FileReader
import scala.xml.{NodeSeq, XML}

object Config {

  val properties = new Properties()
  try {
    properties.load(new FileReader(".env"))
  } catch {
    case _: Throwable => {}
  }

  def prop(key: String) = if (properties.containsKey(key)) properties.getProperty(key) else sys.env(key)

  lazy val phantomJsBinary = prop("PHANTOMJS_BIN")
  lazy val baseUrl = prop("BASE_URL")
  lazy val baseAdminUrl = prop("BASE_ADMIN_URL")
  lazy val jsPromo = prop("JS_PROMO")
  lazy val jsAdminUser = prop("JS_AU")
  lazy val jsAdminPwd = prop("JS_AP")
  lazy val aNet = new {
    val url = prop("AN_URL")
    val u = prop("AN_U")
    val p = prop("AN_P")
  }
  lazy val remoteDriverUrl = prop("REMOTE_DRIVER_URL")
  lazy val driver = prop("DRIVER")

  object JomSocial {

    val newUser = User(prop("NEW_USER_FN"), prop("NEW_USER_LN"), prop("NEW_USER_EMAIL"), prop("NEW_USER_USERNAME"),
      prop("NEW_USER_PASSWORD"), Address(prop("NEW_USER_ADDR"), prop("NEW_USER_CITY"), prop("NEW_USER_STATE"),
        prop("NEW_USER_POSTCODE"), prop("NEW_USER_COUNTRY")), Card(prop("CARD_TYPE"), prop("CARD_NUM"),
        prop("CARD_CVV"), Expiry(prop("CARD_EXP_MON"), prop("CARD_EXP_YR"))))

  }
}
