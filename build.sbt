name := """recoengCommon"""

organization := "com.ruimo"

crossScalaVersions := List("2.10.4", "2.11.2", "2.12.2") 

publishTo := Some(
  Resolver.file(
    "recoengcommon",
    new File(Option(System.getenv("RELEASE_DIR")).getOrElse("/tmp"))
  )
)

libraryDependencies += "joda-time" % "joda-time" % "2.9.9"

libraryDependencies += "org.joda" % "joda-convert" % "1.8.2"

