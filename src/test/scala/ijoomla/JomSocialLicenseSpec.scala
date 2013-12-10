package ijoomla

import org.specs2.execute.Success

class JomSocialLicenseSpec extends SeleniumSpec { def is =s2"""

  A new user should purchase a JS license using AN $newUserAuthNet

"""

  def newUserAuthNet = {

    val page = JomSocialPage.open(driver)
    val cartPage = page.buyStandard
    val loginOrRegisterPage = cartPage.discount.payWithCC
    loginOrRegisterPage.register(Config.JomSocial.newUser)

    Success()
  }

}
