package simbl.domain

import longevity.model.annotations.keyVal

@keyVal[SimblDomainModel, User]
case class Email(email: String)
