resolvers ++= Seq(
  "less is" at "http://repo.lessis.me",
  "coda" at "http://repo.codahale.com"
)

addSbtPlugin("me.lessis" % "ls-sbt" % "0.1.3")

scalacOptions += "-deprecation"

addSbtPlugin("me.lessis" % "bintray-sbt" % "0.3.0")
