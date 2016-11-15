package simbl.domain

import longevity.subdomain.CType
import longevity.subdomain.CTypePool
import longevity.subdomain.PTypePool
import longevity.subdomain.Subdomain

class SimblSubdomain extends Subdomain(
  "Simple Blogging",
  PTypePool(User, Blog, BlogPost),
  CTypePool(
    CType[Markdown],
    CType[Uri],
    CType[UserProfile]))
