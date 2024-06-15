file://<WORKSPACE>/src/main/scala/Main.scala
### dotty.tools.dotc.core.TypeError$$anon$1: Toplevel definition helloEndpoint is defined in
  <WORKSPACE>/src/main/scala/controllers/HomeController.scala
and also in
  <WORKSPACE>/src/main/scala/Etc.scala
One of these files should be removed from the classpath.

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 3.3.3
Classpath:
<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala3-library_3/3.3.3/scala3-library_3-3.3.3.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.12/scala-library-2.13.12.jar [exists ]
Options:



action parameters:
offset: 80
uri: file://<WORKSPACE>/src/main/scala/Main.scala
text:
```scala
import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http.{Meth@@od, Request, Response, Status}
import com.twitter.util.{Await, Future}

import scala.collection.mutable
import scala.util.{Failure, Success, Try}

import io.circe._
import io.circe.generic.auto._
import io.circe.syntax._

object Main {
  

  def main(args: Array[String]): Unit = {
    val service: Service[Request, Response] = new Service[Request, Response] {
      def apply(req: Request): Future[Response] = {
        req.method match {
          case Method.Get =>
            req.path match {
              case "/hello" => helloEndpoint(req)
              case "/users" => getUsers(req)
              case s"/users/${id}" => getUserById(id.toLong)
              case _ => notFoundEndpoint(req)
            }
          case Method.Post =>
            req.path match {
              case "/users" => createUser(req)
              case _ => Future.value(Response(Status.NotFound))
            }
          case Method.Put =>
            req.path match {
              case s"/users/${id}" => updateUserById(id.toLong, req)
              case _ => Future.value(Response(Status.NotFound))
            }
          case Method.Delete =>
            req.path match {
              case s"/users/${id}" => deleteUserById(id.toLong)
              case _ => Future.value(Response(Status.NotFound))
            }
          case _ =>
            Future.value(Response(Status.MethodNotAllowed))
        }
      }
    }

    val server = Http.server.serve(":8080", service)
    val server2 = Http.server.serve(":8081", service)
    Await.all(server, server2)
  }

  
  def notFoundEndpoint(req: Request): Future[Response] = {
    val response = Response(req.version, Status.NotFound)
    response.setContentString("404 Not Found")
    Future.value(response)
  }
}

```



#### Error stacktrace:

```

```
#### Short summary: 

dotty.tools.dotc.core.TypeError$$anon$1: Toplevel definition helloEndpoint is defined in
  <WORKSPACE>/src/main/scala/controllers/HomeController.scala
and also in
  <WORKSPACE>/src/main/scala/Etc.scala
One of these files should be removed from the classpath.