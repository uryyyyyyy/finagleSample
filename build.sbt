name := """finagleSample"""

version := "1.0"

lazy val akkaVersion = "2.3.14"

resolvers += "twitter-repo" at "https://maven.twttr.com"

lazy val commonSettings = Seq(
	organization := "com.github.uryyyyyyy",
	scalaVersion := "2.11.7",
	libraryDependencies ++= Seq(
		"com.twitter" %% "finagle-core" % "6.33.0"
	)
)

lazy val http = (project in file("http")).
		settings(commonSettings: _*)

lazy val client = (project in file("client")).
		settings(commonSettings: _*)

lazy val thread = (project in file("thread")).
		settings(commonSettings: _*)

lazy val finch_helloWorld = (project in file("finch_helloWorld")).
		settings(commonSettings: _*)