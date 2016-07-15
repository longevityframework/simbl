package simbl.domain

import longevity.subdomain.embeddable.ValueObject
import longevity.subdomain.embeddable.ValueType

case class Markdown(markdown: String) extends ValueObject

object Markdown extends ValueType[Markdown]
