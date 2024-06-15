import scala.collection.mutable
import scala.util.{Failure, Success, Try}

import com.twitter.finagle.http.{Request, Response, Status}
import com.twitter.util.{Future}

import io.circe._
import io.circe.generic.auto._
import io.circe.syntax._

trait UserController {
  private val users = mutable.ListBuffer(
    User(1, "john_doe", "john.doe@example.com"),
    User(2, "jane_smith", "jane.smith@example.com")
  )

  def getUsers(req: Request): Future[Response] = {
    val jsonResponse = users.toList.asJson
    val response = Response(Status.Ok)
    response.setContentTypeJson()
    response.setContentString(jsonResponse.toString())
    Future.value(response)
  }

  def getUserById(id: Long): Future[Response] = {
    users.find(_.id == id) match {
      case Some(user) =>
        val jsonResponse = user.asJson
        val response = Response(Status.Ok)
        response.setContentTypeJson()
        response.setContentString(jsonResponse.toString)
        Future.value(response)
      case None =>
        val response = Response(Status.NotFound)
        response.setContentString(s"User with id $id not found")
        Future.value(response)
    }
  }

  def createUser(req: Request): Future[Response] = {
    val maybeUser = for {
      jsonBody <- Try(req.contentString)
      newUser <- io.circe.parser.decode[User](jsonBody).toTry
    } yield newUser

    maybeUser match {
      case Success(newUser) =>
        users += newUser
        val jsonResponse = newUser.asJson
        val response = Response(Status.Created)
        response.setContentTypeJson()
        response.setContentString(jsonResponse.toString)
        Future.value(response)
      case Failure(exception) =>
        val response = Response(Status.BadRequest)
        response.setContentString(
          s"Failed to parse request body: ${exception.getMessage}"
        )
        Future.value(response)
    }
  }

  def updateUserById(id: Long, req: Request): Future[Response] = {
    users.find(_.id == id) match {
      case Some(_) =>
        val maybeUser = for {
          jsonBody <- Try(req.contentString)
          updatedUser <- io.circe.parser.decode[User](jsonBody).toTry
        } yield updatedUser.copy(id = id)

        maybeUser match {
          case Success(updatedUser) =>
            users -= users.find(_.id == id).get
            users += updatedUser
            val jsonResponse = updatedUser.asJson
            val response = Response(Status.Ok)
            response.setContentTypeJson()
            response.setContentString(jsonResponse.toString)
            Future.value(response)
          case Failure(exception) =>
            val response = Response(Status.BadRequest)
            response.setContentString(
              s"Failed to parse request body: ${exception.getMessage}"
            )
            Future.value(response)
        }
      case None =>
        val response = Response(Status.NotFound)
        response.setContentString(s"User with id $id not found")
        Future.value(response)
    }
  }

  def deleteUserById(id: Long): Future[Response] = {
    users.find(_.id == id) match {
      case Some(_) =>
        users -= users.find(_.id == id).get
        val response = Response(Status.Ok)
        response.setContentString(s"User with id $id deleted")
        Future.value(response)
      case None =>
        val response = Response(Status.NotFound)
        response.setContentString(s"User with id $id not found")
        Future.value(response)
    }
  }
}
