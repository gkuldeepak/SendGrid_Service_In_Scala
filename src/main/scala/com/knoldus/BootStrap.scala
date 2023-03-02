package com.knoldus

import com.knoldus.sendgrid.SendGridService

object BootStrap extends App {

  val mailerService = new SendGridService
  mailerService.sendUserEmail

}
