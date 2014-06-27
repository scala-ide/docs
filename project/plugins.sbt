resolvers += Resolver.url("sbt-plugin-releases", new URL("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases/"))(Resolver.ivyStylePatterns)

resolvers += "sonatype-releases" at "https://oss.sonatype.org/service/local/repositories/releases/content/"

resolvers += "jgit-repo" at "http://download.eclipse.org/jgit/maven"

addSbtPlugin("com.typesafe.sbt" % "sbt-site" % "0.7.2")

addSbtPlugin("com.typesafe.sbt" % "sbt-ghpages" % "0.5.2")
