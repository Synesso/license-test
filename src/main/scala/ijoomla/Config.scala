package ijoomla

import java.util.Properties
import java.io.FileReader
import scala.xml.{NodeSeq, XML}

object Config {

  val properties = new Properties()
  properties.load(new FileReader(".env"))

  lazy val phantomJsBinary = properties.getProperty("PHANTOMJS_BIN")
  lazy val baseUrl = properties.getProperty("BASE_URL")
  lazy val baseAdminUrl = properties.getProperty("BASE_ADMIN_URL")
  lazy val jsPromo = properties.getProperty("JS_PROMO")
  lazy val jsAdminUser = properties.getProperty("JS_AU")
  lazy val jsAdminPwd = properties.getProperty("JS_AP")
  lazy val aNet = new {
    val url = properties.getProperty("AN_URL")
    val u = properties.getProperty("AN_U")
    val p = properties.getProperty("AN_P")
  }
  lazy val remoteDriverUrl = properties.getProperty("REMOTE_DRIVER_URL")
  lazy val driver = properties.getProperty("DRIVER", "firefox")

  object JomSocial {

    val newUser = {
      val xml = XML.loadString(properties.getProperty("NEW_USER"))
      def t(node: String*) = {
        def t(x: NodeSeq, node: Seq[String]): String = node match {
          case n :: Nil => (x \ n).text
          case n :: ns => t(x \ n, ns)
        }
        t(xml, node.toList)
      }

      User(t("firstname"), t("lastname"), t("email"), t("username"), t("password"),
        Address(t("streetaddress"), t("city"), t("state"), t("postcode"), t("country")),
        Card(t("card","type"),t("card","number"), t("card","ccv"),
          Expiry(t("card","expiry","month"), t("card","expiry","year"))))
    }

  }
}
