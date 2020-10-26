import finagle.FinagleServer

object Main extends App {
  while (true) {
    FinagleServer.startServer
  }
}
