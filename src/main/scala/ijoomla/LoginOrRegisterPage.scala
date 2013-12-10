package ijoomla

import org.openqa.selenium.{By, WebDriver}

class LoginOrRegisterPage(driver: WebDriver) {

  def register(user: User) = {
    driver.findElement(By.xpath("""//*[@id="ex-component"]/div/input[2]""")).click()
    Map(
      "firstname" -> user.firstname,
      "lastname" -> user.lastname,
      "email" -> user.email,
      "username2" -> user.username,
      "password" -> user.password,
      "password_confirm" -> user.password
    ).foreach{case (k,v) => driver.findElement(By.id(k)).sendKeys(v)}
    driver.findElement(By.id("continue_button")).click()
    this // ???
  }

}
