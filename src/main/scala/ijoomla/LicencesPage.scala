package ijoomla

import org.openqa.selenium.{By, WebDriver}

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

}
