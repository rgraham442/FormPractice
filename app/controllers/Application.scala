package controllers

import javax.inject.Inject

import models.People
import play.api.i18n.{I18nSupport,MessagesApi}
import play.api.mvc._

class Application @Inject()(val messagesApi: MessagesApi, environment: play.api.Environment) extends Controller with I18nSupport {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def listPeople = Action { implicit request =>
    Ok(views.html.listPeople(People.people,People.createPersonForm))
  }

  def createPerson = Action { implicit request =>
    val formValidationResult = People.createPersonForm.bindFromRequest
    formValidationResult.fold({ formWithErrors =>
      BadRequest(views.html.listPeople(People.people,formWithErrors))
    }, {peoples =>
      People.people.append(peoples)
      Redirect(routes.Application.listPeople)
    })
  }

}