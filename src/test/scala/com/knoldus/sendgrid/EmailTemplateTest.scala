package com.knoldus.sendgrid

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import com.knoldus.sendgrid.EmailTemplates._

class EmailTemplateTest extends AnyFlatSpec with Matchers {

  it should "return subject" in {
    subject should not be empty
  }

  it should "return email body" in {
    body should not be empty
  }

}
