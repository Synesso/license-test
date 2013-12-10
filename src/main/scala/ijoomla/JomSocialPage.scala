package ijoomla

import org.openqa.selenium.{By, WebDriver}

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

}
