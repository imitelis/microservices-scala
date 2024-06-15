import com.twitter.util.{Future, Await}
import com.twitter.finagle.Http
import com.twitter.finagle.http.{Method, Request, Response, Status}

object UserService extends UserController {
  def apply(req: Request): Future[Response] = {
    req.method match {
      case Method.Get =>
        req.path match {
          case "/users"        => getUsers(req)
          case s"/users/${id}" => getUserById(id.toLong)
          case _               => Future.value(Response(Status.NotFound))
        }
      case Method.Post =>
        req.path match {
          case "/users" => createUser(req)
          case _        => Future.value(Response(Status.NotFound))
        }
      case Method.Put =>
        req.path match {
          case s"/users/${id}" => updateUserById(id.toLong, req)
          case _               => Future.value(Response(Status.NotFound))
        }
      case Method.Delete =>
        req.path match {
          case s"/users/${id}" => deleteUserById(id.toLong)
          case _               => Future.value(Response(Status.NotFound))
        }
      case _ =>
        Future.value(Response(Status.MethodNotAllowed))
    }
  }
}
