package ijoomla

import org.specs2.Specification
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.phantomjs.{PhantomJSDriver, PhantomJSDriverService}
import java.util.Arrays._
import org.specs2.specification.{Fragments, Step}
import java.util.concurrent.TimeUnit._

trait SeleniumSpec extends Specification {

  override def map(fs: =>Fragments) = fs ^ Step(cleanUp())

  lazy val driver = firefoxDriver
  driver.manage.timeouts.implicitlyWait(30, SECONDS)

  lazy val firefoxDriver = {
    val capabilities = DesiredCapabilities.firefox
    capabilities.setJavascriptEnabled(true)
    new FirefoxDriver
  }

  lazy val phantomDriver = {
    val capabilities = DesiredCapabilities.phantomjs
    capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, Config.phantomJsBinary)
    capabilities.setJavascriptEnabled(true)
    capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
      asList("--web-security=false", "--ssl-protocol=any", "--ignore-ssl-errors=true"))
    capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_GHOSTDRIVER_CLI_ARGS, Array[String]("--logLevel=INFO"))
    new PhantomJSDriver(capabilities)
  }

  def cleanUp() {
//    driver.quit()
  }


}
