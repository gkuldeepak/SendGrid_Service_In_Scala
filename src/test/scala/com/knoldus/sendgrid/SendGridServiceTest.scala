package com.knoldus.sendgrid

import com.sendgrid.{Content, Email, Mail, Request, Response, SendGrid}
import com.typesafe.config.Config
import org.mockito.Matchers.any
import org.mockito.Mockito.when
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.mockito.MockitoSugar.mock

import scala.concurrent.ExecutionContext.Implicits.global

class SendGridServiceTest extends AnyFlatSpec with Matchers{

  val sendGrid = mock[SendGrid]
  val config = mock[Config]
  val request = mock[Request]
  val to = mock[Email]
  val content = mock[Content]
  val mail = mock[Mail]

  val sendGridService = new SendGridService

  it should "send user email" in {
    val mockedResponse = new Response(
      200,
      "Email send successfully!",
      new java.util.HashMap[String, String]()
    )
    when(sendGrid.api(any[Request])) thenReturn mockedResponse
    val result = sendGridService.sendUserEmail

    result map {
      response =>
        assert(response.nonEmpty)
    }
  }

  it should "failed in sending user email" in {
    val mockedResponse = new Response(
      400,
      "Email send Failed!",
      new java.util.HashMap[String, String]()
    )
    when(sendGrid.api(any[Request])) thenReturn mockedResponse
    val result = sendGridService.sendUserEmail

    result map {
      response =>
        assert(response.equals("Failed"))
    }
  }


}
