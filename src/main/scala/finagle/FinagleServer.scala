package finagle

import com.twitter.finagle.{Http, Service, http}
import com.twitter.util.{Await, Future}

object FinagleServer{
  def startServer ={
    var flag = false
    val service = new Service[http.Request, http.Response] {
      def apply(req: http.Request): Future[http.Response] = {
        val authToken = req.headerMap.getOrNull("Authentication")
        if (Auth.Auth.checkaccess(Auth.Auth.getMD5Hash(authToken))) {
          flag = true
          Future.value(
            FinagleClient.startClient
          )
        } else {
          Future.value(
            http.Response(req.version, http.Status.Forbidden)
          )
        }
      }
    }
    val server = Http.serve(":9000", service)
    Await.ready(server)
    flag
  }
}
