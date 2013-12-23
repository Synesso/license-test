package ijoomla

import org.specs2.execute.Success


class JomSocialLicenseSpec extends SeleniumSpec { def is = args(sequential = true) ^ s2"""

  A new user should purchase a JS license using AN $newUserAuthNet
  An existing user should purchase a JS license using AN $oldUserAuthNet

"""

  val user = Config.JomSocial.newUser

  def newUserAuthNet = {
    val licencesPage = JomSocialPage.open(driver)
      .buyStandard
      .discount
      .payWithCC
      .register(user)
      .payAs(user)

    licencesPage.mustHaveALicense

    JomSocialAdminPage.openUserManagerTab(driver).delete(user).logout()

    AuthNetPage.open(driver).voidFirstOneCentTransaction.logout()

    Success()
  }

  def oldUserAuthNet = {
    JomSocialPage.open(driver).register(user).logout()

    val licencesPage = JomSocialPage.open(driver)
      .buyStandard
      .discount
      .payWithCC
      .loginAs(user)
      .payAs(user)

    licencesPage.mustHaveALicense

    JomSocialAdminPage.openUserManagerTab(driver).delete(user).logout()

    AuthNetPage.open(driver).voidFirstOneCentTransaction.logout()

    Success()
  }

}
