import com.twitter.finagle.http.{Request, Response, Status}
import com.twitter.util.{Future}

trait HelloController {
  def helloEndpoint(req: Request): Future[Response] = {
    val response = Response(req.version, Status.Ok)
    response.setContentString("Hello, Finagle!")
    Future.value(response)
  }

  def notFoundEndpoint(req: Request): Future[Response] = {
    val response = Response(req.version, Status.NotFound)
    response.setContentString("404 Not Found")
    Future.value(response)
  }
}
