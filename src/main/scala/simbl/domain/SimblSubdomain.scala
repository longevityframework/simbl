package simbl.domain

import longevity.subdomain.Subdomain
import longevity.subdomain.embeddable.ETypePool
import longevity.subdomain.embeddable.EType
import longevity.subdomain.ptype.PTypePool

class SimblSubdomain extends Subdomain(
  "Simple Blogging",
  PTypePool(User, Blog, BlogPost),
  ETypePool(
    EType[Markdown],
    EType[Uri],
    EType[UserProfile]))
