package ijoomla

import org.openqa.selenium.{By, WebDriver}

class MakePaymentPage(driver: WebDriver) {

  def payAs(user: User) = {

    Map(
      "activated" -> user.card.brand,
      "cardnum" -> user.card.number,
      "expmonth" -> user.card.expiry.month,
      "expyear" -> user.card.expiry.year,
      "cardcvv" -> user.card.sec,
      "cardfname" -> user.firstname,
      "cardlname" -> user.lastname,
      "email" -> user.email,
      "cardaddress1" -> user.address.street,
      "cardcountry" -> user.address.country,
      "cardstate" -> user.address.state,
      "cardcity" -> user.address.city,
      "cardzip" -> user.address.postcode
    ).foreach{case (k,v) => driver.findElement(By.id(k)).sendKeys(v)}
    driver.findElement(By.id("authorizebutton")).click()
    new LicencesPage(driver)

  }

}
