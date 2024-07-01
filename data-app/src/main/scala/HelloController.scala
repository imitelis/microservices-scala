import com.twitter.finatra.http.Controller
import com.twitter.finagle.http.Request

class HelloController extends Controller {

  get("/hello") { request: Request =>
    "Hello, World!"
  }
}
