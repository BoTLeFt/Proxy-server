package finagle

import com.twitter.finagle.{Http, Service, http}
import com.twitter.util.{Await, Future}
import util.control.Breaks._

object FinagleClient {
  val hostnames = List("www.bmstu.ru", "www.google.com")
  def startClient = {
    var resp: http.Response = http.Response()
    breakable {
      for (hostname <- hostnames) {
        resp = Request(hostname)
        if (resp.statusCode==200) {
          break
        }
      }
    }
    resp
  }

  def Request(hostname: String) = {
    var httpresponse: http.Response = http.Response()
    val client: Service[http.Request, http.Response] = Http.newService(hostname+":80")
    val request = http.Request(http.Method.Get, "/")
    request.host = hostname
    val response: Future[http.Response] = client(request)
    response.onSuccess { resp: http.Response =>
      httpresponse = resp
    }
    response.onFailure{err: Throwable => }
    Await.ready(response)
    httpresponse
  }
}
