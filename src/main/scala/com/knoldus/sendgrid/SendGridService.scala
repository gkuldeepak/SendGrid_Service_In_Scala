package com.knoldus.sendgrid

import com.sendgrid._
import com.typesafe.config.ConfigFactory

import scala.concurrent.Future

class SendGridService {

  private lazy val apiKey = ConfigFactory.load().getString("sendgrid.api.key")
  private lazy val sendGrid = new SendGrid(apiKey)

  def sendUserEmail: Future[String] = {
    val body = EmailTemplates.body
    val subject = EmailTemplates.subject
    sendEmail("kuldeepak.gupta@knoldus.com", body, subject, "knoldus@knoldus.com").map{
      case true =>
        println(Console.GREEN + "Email sent Successfully" + Console.RESET)
        "Success"
      case false =>
        println(Console.RED + s"Failed to send Email with exception" + Console.RESET)
        "Failed"
    }(scala.concurrent.ExecutionContext.Implicits.global)
  }

  private def sendEmail(email: String, body: String, subject: String, sender: String): Future[Boolean] = {

    val to = new Email(email)
    val fromEmail = new Email(sender)
    fromEmail.setName("Knoldus Inc")
    val content = new Content("text/html", body)

    val mail = new Mail(fromEmail, subject, to, content)

    val request = new Request
    request.method = Method.POST
    request.endpoint = "mail/send"
    request.body = mail.build

    println(Console.CYAN + s"Sending Email to user email ${email} with subject ${subject}" + Console.RESET)

    Future {
      val response = sendGrid.api(request)
      println(response.statusCode.toString)
      response.statusCode.toString.startsWith("2")
    }(scala.concurrent.ExecutionContext.Implicits.global)
  }

}
