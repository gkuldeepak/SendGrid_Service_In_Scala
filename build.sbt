ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "SendGrid_Service_In_Scala"
  )

libraryDependencies ++= Seq(  "com.sendgrid" % "sendgrid-java" % "3.1.0",
  "org.scalatestplus" %% "mockito-1-10" % "3.1.0.0" % Test,
  "org.scalatest" %% "scalatest" % "3.2.9" % Test,
  "org.mockito" % "mockito-all" % "1.10.19" % Test,
  "com.typesafe" % "config" % "1.4.1"
)
