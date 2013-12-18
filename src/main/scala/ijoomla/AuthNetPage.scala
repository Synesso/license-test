package ijoomla

import org.openqa.selenium.{WebElement, By, WebDriver}
import scala.collection.JavaConverters._


class AuthNetPage(driver: WebDriver) {

  def open = {
    driver.get(Config.aNet.url)
    this
  }

  def login = {
    val (u, p) = (Config.aNet.u, Config.aNet.p)
    driver.switchTo().frame("top")
    driver.findElement(By.xpath("""//*[@id="ctl00_MainContent_welcomeText"]/div/div[1]/input""")).sendKeys(u)
    driver.findElement(By.xpath("""//*[@id="ctl00_MainContent_welcomeText"]/div/div[2]/input""")).sendKeys(p)
    driver.findElement(By.xpath("""//*[@id="ctl00_MainContent_welcomeText"]/div/div[3]/input""")).click()
    driver.findElement(By.xpath("""//*[@id="homepage"]/table/tbody/tr/td[1]/ul[1]/li/a[2]""")).click()
    this
  }

  def logout() = driver.findElement(By.id("ucHeader_ucTopMenu_lnkLogout")).click()

  def oneCentTxns: Seq[Authorisation] = {
    driver.findElements(By.xpath("//tr[starts-with(@class, 'SearchLineItemRow')]")).asScala
      .map(e => new Authorisation(e))
      .filter(_.amount == 0.01)
      .filter(_.status == "Captured/Pending Settlement")
  }

  def firstOneCentTxn = oneCentTxns.headOption

  def voidFirstOneCentTransaction = {
    firstOneCentTxn.foreach(_.void)
    this
  }

  class Authorisation(e: WebElement) {

    def void = {
      println(s"ABOUT TO VOID TXN: ${e.getText}\n")
      e.findElement(By.xpath("td[1]/a")).click()
      val settlementAmount = driver.findElement(By.id("SettlementAmount")).getText.trim
      assert(settlementAmount == "USD 0.01", s"Expected settlement amount of USD 0.01, but was $settlementAmount")
      driver.findElement(By.id("btnConfirmVoid")).click()
      driver.findElement(By.id("btnConfirmYes")).click()
      driver.findElement(By.id("btnClos2")).click()
      this
    }

    lazy val status = e.findElement(By.xpath("td[3]/div")).getText.trim

    lazy val amount = {
      val text = e.findElement(By.xpath("td[8]/div")).getText
      text.substring(text.lastIndexOf(' ')).trim.toDouble
    }

    override def toString = s"$status for $amount"

  }
}

object AuthNetPage {
  def open(driver: WebDriver) = new AuthNetPage(driver).open.login
}

