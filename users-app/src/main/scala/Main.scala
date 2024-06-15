import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http.{Method, Request, Response, Status}
import com.twitter.util.{Await, Future}

import scala.collection.mutable
import scala.util.{Failure, Success, Try}

object Main {
  def main(args: Array[String]): Unit = {
    val helloServer = new Service[Request, Response] {
      def apply(req: Request): Future[Response] = HelloService(req)
    }

    val userServer = new Service[Request, Response] {
      def apply(req: Request): Future[Response] = UserService(req)
    }

    val server0 = Http.server.serve(":8080", helloServer)
    val server1 = Http.server.serve(":8081", userServer)
    val server2 = Http.server.serve(":8082", helloServer)

    Await.all(server0, server1, server2)
  }
}
