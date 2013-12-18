package ijoomla

import org.openqa.selenium.{By, WebDriver}

object JomSocialAdminPage {

  def openUserManagerTab(driver: WebDriver) = new JomSocialAdminPage(driver).openUserManagerTab

}

class JomSocialAdminPage(driver: WebDriver) {
  def openUserManagerTab: JomSocialAdminPage = {
    driver.get(s"${Config.baseAdminUrl}/index.php?option=com_users")
    driver.findElement(By.id("mod-login-username")).sendKeys(Config.jsAdminUser)
    driver.findElement(By.id("mod-login-password")).sendKeys(Config.jsAdminPwd)
    driver.findElement(By.id("form-login")).submit()
    this
  }

  def delete(user: User) = {
    driver.findElement(By.id("filter_search")).sendKeys(user.username)
    driver.findElement(By.xpath("""//*[@id="filter-bar"]/div[1]/button[1]""")).click()
    val usersFound = driver.findElements(By.xpath("//*[@id=\"adminForm\"]/table/tbody/tr"))
    assert(usersFound.size == 1, s"Found ${usersFound.size} users when attempting to clean up test user. Expected 1 only.")
    val username = driver.findElement(By.xpath("""//*[@id="adminForm"]/table/tbody/tr/td[3]""")).getText
    val checkbox = driver.findElement(By.xpath("""//*[@id="adminForm"]/table/tbody/tr/td[1]/input"""))
    val deleteButton = driver.findElement(By.xpath("""//*[@id="toolbar-delete"]/a"""))
    assert(username.trim == user.username, s"Attempting to delete test user, but username was $username when we expected ${user.username}")
    if (!checkbox.isSelected) checkbox.click()
    deleteButton.click()
    val flashMessage = driver.findElement(By.xpath("""//*[@id="system-message"]/dd/ul/li""")).getText
    assert("One user successfully deleted" == flashMessage, s"Delete User response was not as expected. Got '$flashMessage'")
    this
  }

  def logout() = driver.findElement(By.cssSelector(".logout > a:nth-child(1)")).click()

}
