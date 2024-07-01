import com.google.inject.Inject
import com.jakehschwartz.finatra.swagger.SwaggerController
import com.twitter.finagle.http.Request
import io.swagger.models.Swagger

class SampleController @Inject() (s: Swagger) extends SwaggerController {
  implicit protected val swagger = s

  getWithDoc("/knolder/:id") { id =>
    id.summary("Read the detail information about the knolder")
      .tag("Knolder")
      .routeParam[String]("id", "the knolder id")
      .responseWith[Knolder](200, "the knolder details")
      .responseWith(404, "the knolder not found")
  } { (request: Request) =>
    val knolderId: Int = request.getParam("id").toInt
    Knolder(Some(knolderId), "girish", "Consultant")
  }

  postWithDoc("/knolder") { data =>
    data
      .summary("Create a new knolder")
      .tag("Knolder")
      .bodyParam[Knolder]("knolder", "the knolder details")
      .responseWith[Knolder](200, "the knolder details with id")
      .responseWith(404, "the knolder not found")
  } { (request: Request) =>
    val knolderId = math.random().toInt
    Knolder(Some(knolderId), "girish", "Consultant")
  }

  putWithDoc("/knolder/:id") { id =>
    id.summary("Update a knolder")
      .tag("Knolder")
      .routeParam[String]("id", "the knolder id")
      .queryParam[String]("name", "the knolder name")
      .queryParam[String]("designation", "the knolder designation")
      .responseWith[Knolder](200, "the knolder details with id")
      .responseWith(404, "the knolder not found")
      .responseWith(500, "internal server error")
  } { (request: Request) =>
    val knolderId: Int = request.getParam("id").toInt
    val name = request.getParam("name").toString
    val designation = request.getParam("designation").toString
    Knolder(Some(knolderId), name, designation)
  }

  deleteWithDoc("/knolder/:id") { id =>
    id.summary("Delete a knolder")
      .tag("Knolder")
      .routeParam[String]("id", "the knolder id")
      .responseWith[Knolder](200, "the knolder details with id")
      .responseWith(404, "the knolder not found")
  } { (request: Request) =>
    val knolderId: Int = request.getParam("id").toInt
    val name = request.getParam("name").toString
    val designation = request.getParam("designation").toString
    Knolder(Some(knolderId), name, designation)
  }
}
