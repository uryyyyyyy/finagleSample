name := """finagleSample"""

version := "1.0"

lazy val akkaVersion = "2.3.14"

lazy val commonSettings = Seq(
	organization := "com.github.uryyyyyyy",
	scalaVersion := "2.11.7",
	libraryDependencies ++= Seq(
		"com.twitter" %% "finagle-core" % "6.34.0"
	)
)

lazy val http = (project in file("http")).
		settings(commonSettings: _*)
