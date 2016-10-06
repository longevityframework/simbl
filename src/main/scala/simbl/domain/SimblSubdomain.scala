package simbl.domain

import longevity.subdomain.EType
import longevity.subdomain.ETypePool
import longevity.subdomain.PTypePool
import longevity.subdomain.Subdomain

class SimblSubdomain extends Subdomain(
  "Simple Blogging",
  PTypePool(User, Blog, BlogPost),
  ETypePool(
    EType[Markdown],
    EType[Uri],
    EType[UserProfile]))
