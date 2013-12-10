
organization := "com.github.synesso"

name := "js-license-at"

version := "1.0"

scalaVersion := "2.10.3"

libraryDependencies ++= Seq(
  "org.seleniumhq.selenium" % "selenium-java" % "2.37.1",
  "com.github.detro.ghostdriver" % "phantomjsdriver" % "1.0.4",
  "org.specs2" %% "specs2" % "2.2" % "test"
)

