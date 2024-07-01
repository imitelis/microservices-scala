import com.twitter.finatra.http.{Controller, HttpServer}
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finagle.Http
import com.twitter.finagle.http.Request
import com.jakehschwartz.finatra.swagger.DocsController
import com.twitter.finatra.swagger.SwaggerModule

object Main extends MainServer

class MainServer extends HttpServer {

  override protected def configureHttp(router: HttpRouter): Unit = {
    router
      .add[HelloController]
    // .add[DocsController] <- causes the issue

  }

  override protected def defaultHttpPort: String = ":9000"

  override protected def configureSwagger(swagger: SwaggerModule): Unit = {
    swagger
      .info(
        version = "1.0",
        title = "Your API",
        description = "API description",
        termsOfService = "https://example.com/terms",
        contact = "Your Name",
        license = "Apache 2.0",
        licenseUrl = "http://www.apache.org/licenses/LICENSE-2.0.html"
      )
      .addSwaggerResource("/swagger.json")
  }
}
