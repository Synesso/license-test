
organization := "com.github.synesso"

name := "js-license-at"

version := "1.0"

scalaVersion := "2.10.3"

resolvers ++= Seq(
  "Saucelabs Repo" at "https://repository-saucelabs.forge.cloudbees.com/release"
)

libraryDependencies ++= Seq(
  "org.seleniumhq.selenium" % "selenium-java" % "2.37.1",
  "com.github.detro.ghostdriver" % "phantomjsdriver" % "1.0.4",
  "com.saucelabs" % "saucerest" % "1.0.7",
  "org.specs2" %% "specs2" % "2.2" % "test"
)

