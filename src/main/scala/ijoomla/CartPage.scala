package ijoomla

import org.openqa.selenium.{By, WebDriver}

class CartPage(driver: WebDriver) {

  def discount = {
    driver.findElement(By.id("promocode")).sendKeys(Config.jsPromo)
    driver.findElements(By.xpath("*//button")).get(0).click()
    assert(cartTotal <= 0.01)
    this
  }

  def cartTotal: Double = {
    val text = driver.findElement(By.id("cart_total")).getText
    text.substring(text.lastIndexOf(" ")).toDouble
  }

  def payWithCC: LoginOrRegisterPage = {
    driver.findElement(By.id("processor")).sendKeys("cr")
    driver.findElements(By.xpath("""*//button[contains(text(), "Continue")]""")).get(1).click()
    new LoginOrRegisterPage(driver)
  }

}
