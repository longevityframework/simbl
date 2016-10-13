package simbl.domain

import longevity.subdomain.KeyVal

case class Email(email: String) extends KeyVal[User, Email]
