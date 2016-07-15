package simbl.domain

import longevity.subdomain.CoreDomain
import longevity.subdomain.embeddable.ETypePool
import longevity.subdomain.ptype.PTypePool

class SimblCoreDomain extends CoreDomain(
  "Simple Blogging",
  PTypePool(User, Blog, BlogPost),
  ETypePool(Markdown, Uri, UserProfile))
