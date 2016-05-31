package simpleblogging.domain

import longevity.subdomain.Shorthand

case class Markdown(markdown: String)

object Markdown extends Shorthand[Markdown, String]
