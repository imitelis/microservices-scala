file://<WORKSPACE>/src/main/scala/MyServiceImpl.scala
### dotty.tools.dotc.core.TypeError$$anon$1: Toplevel definition helloEndpoint is defined in
  <WORKSPACE>/src/main/scala/Etc.scala
and also in
  <WORKSPACE>/src/main/scala/Main.scala
One of these files should be removed from the classpath.

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 3.3.3
Classpath:
<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala3-library_3/3.3.3/scala3-library_3-3.3.3.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.12/scala-library-2.13.12.jar [exists ]
Options:



action parameters:
offset: 33
uri: file://<WORKSPACE>/src/main/scala/MyServiceImpl.scala
text:
```scala
import com.twitter.finagle.http.{@@Request, Response, Status}
import com.twitter.util.{Future, Await}
import com.twitter.finagle.Http

object MyServiceImpl extends MyService with MyController {
  def apply(req: Request): Future[Response] = {
    req.method match {
      case Method.Get =>
        req.path match {
          case "/hello" => helloEndpoint(req)
          case _ => notFoundEndpoint(req)
        }
      case _ =>
        Future.value(Response(Status.MethodNotAllowed))
    }
  }
}

```



#### Error stacktrace:

```

```
#### Short summary: 

dotty.tools.dotc.core.TypeError$$anon$1: Toplevel definition helloEndpoint is defined in
  <WORKSPACE>/src/main/scala/Etc.scala
and also in
  <WORKSPACE>/src/main/scala/Main.scala
One of these files should be removed from the classpath.