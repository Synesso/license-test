package ijoomla

import org.openqa.selenium.{By, WebDriver}
import scala.collection.JavaConverters._


object JomSocialPage {

  def open(driver: WebDriver) = new JomSocialPage(driver).open

}

class JomSocialPage(driver: WebDriver) {
  def open: JomSocialPage = {
    driver.get(Config.baseUrl)
    this
  }

  def buyStandard: CartPage = {
    val elements = driver.findElements(By.xpath("""//*[@id="prform"]/table/tbody/tr/td[3]/input[3]"""))
    val button = elements.get(0)
    button.click()
    new CartPage(driver)
  }

  def register(user: User) = {
    driver.findElement(By.linkText("Customer Login")).click()
    driver.findElements(By.xpath("""//input[@type="submit"]""")).asScala(1).click()
    Map(
      "firstname" -> user.firstname,
      "lastname" -> user.lastname,
      "email" -> user.email,
      "username" -> user.username,
      "password" -> user.password,
      "password_confirm" -> user.password
    ).foreach{case (k,v) => driver.findElement(By.id(k)).sendKeys(v)}
    driver.findElement(By.xpath("""//button[@type="submit"]""")).click()
    new LicencesPage(driver)
  }

}
