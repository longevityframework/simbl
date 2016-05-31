package simpleblogging.domain

import longevity.subdomain.Shorthand

case class Email(email: String)

object Email extends Shorthand[Email, String]
