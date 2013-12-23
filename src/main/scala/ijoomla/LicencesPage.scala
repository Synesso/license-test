package ijoomla

import org.openqa.selenium.{By, WebDriver}
import scala.collection.JavaConverters._


class LicencesPage(driver: WebDriver) {

  def mustHaveALicense = {
    val expiryText = driver.findElement(By.className("digi_active")).getText.trim
    val days = "\\d++".r.findFirstIn(expiryText).map(_.toInt)
    days match {
      case Some(d) => assert(d >= 180, s"There was a licence, but it expires in $d days, not 180 or more")
      case None => assert(assertion = false, "There was no licence displayed")
    }
    this
  }

  def buyStandard = JomSocialPage.open(driver).buyStandard

  def logout() = {
    // sometimes creating a login will log you in, sometimes it won't. Try to log out, if you can...
    val logoutButtons = driver.findElements(By.cssSelector("ul.nav:nth-child(1) > li:nth-child(9) > a:nth-child(1)")).asScala
    logoutButtons.headOption.foreach(_.click())
  }

}
