package ijoomla

import org.specs2.execute.Success


class JomSocialLicenseSpec extends SeleniumSpec { def is =s2"""

  A new user should purchase a JS license using AN $newUserAuthNet

"""

  def newUserAuthNet = {

    val user = Config.JomSocial.newUser

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

}
