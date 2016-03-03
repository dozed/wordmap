name := "wordmap"

version := "1.0"

scalaVersion := "2.11.7"

val scalaJSReactVersion = "0.10.4"

resolvers += Opts.resolver.sonatypeSnapshots
resolvers += Opts.resolver.sonatypeReleases

libraryDependencies ++= Seq(
  "com.github.japgolly.scalajs-react" %%% "extra" % scalaJSReactVersion,
  "com.github.japgolly.scalajs-react" %%% "ext-scalaz71" % scalaJSReactVersion,
  // "com.github.japgolly.scalajs-react" %%% "ext-monocle" % scalaJSReactVersion,
  "com.github.japgolly.fork.scalaz" %%% "scalaz-core" % "7.1.3",
  "com.github.japgolly.fork.monocle" %%% "monocle-core" % "1.1.1",
  "org.singlespaced" %%% "scalajs-d3" % "0.3.1"
)

// https://github.com/non/kind-projector
// makes writing type signatures easier
addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.7.1")

scalacOptions += "-feature"

enablePlugins(ScalaJSPlugin)


// create launcher file ( its search for object extends JSApp , make sure there is only one file)
persistLauncher := true

persistLauncher in Test := false

// copy  javascript files to js folder,that are generated using fastOptJS/fullOptJS

crossTarget in (Compile, fullOptJS) := file("build")

crossTarget in (Compile, fastOptJS) := file("build")

crossTarget in (Compile, packageScalaJSLauncher) := file("build")

artifactPath in (Compile, fastOptJS) := ((crossTarget in (Compile, fastOptJS)).value /
  ((moduleName in fastOptJS).value + "-opt.js"))

