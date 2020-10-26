name := "Proxy server"

version := "0.1"

scalaVersion := "2.12.0"

// https://mvnrepository.com/artifact/com.twitter/finagle-http
libraryDependencies += "com.twitter" %% "finagle-http" % "19.12.0"

// https://mvnrepository.com/artifact/org.postgresql/postgresql
libraryDependencies += "org.postgresql" % "postgresql" % "42.2.9"