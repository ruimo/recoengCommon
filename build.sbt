name := """recoengCommon"""

organization := "com.ruimo"

version := "1.1-SNAPSHOT"

crossScalaVersions := List("2.10.4", "2.11.2") 

publishTo := Some(
  Resolver.file(
    "recoengcommon",
    new File(Option(System.getenv("RELEASE_DIR")).getOrElse("/tmp"))
  )
)

// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" %% "scalatest" % "2.1.6" % "test"

libraryDependencies += "joda-time" % "joda-time" % "2.3"

libraryDependencies += "org.joda" % "joda-convert" % "1.6"

