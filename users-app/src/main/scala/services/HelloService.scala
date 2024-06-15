import com.twitter.finagle.http.{Method, Request, Response, Status}
import com.twitter.util.{Future, Await}
import com.twitter.finagle.Http

object HelloService extends HelloController {
  def apply(req: Request): Future[Response] = {
    req.method match {
      case Method.Get =>
        req.path match {
          case "/hello" => helloEndpoint(req)
          case _        => notFoundEndpoint(req)
        }
      case _ =>
        Future.value(Response(Status.MethodNotAllowed))
    }
  }
}
