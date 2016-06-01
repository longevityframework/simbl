package simpleblogging.domain

import longevity.subdomain.CoreDomain
import longevity.subdomain.ShorthandPool
import longevity.subdomain.ptype.PTypePool
import longevity.subdomain.entity.EntityTypePool

class SimblCoreDomain extends CoreDomain(
  "Simple Blogging",
  PTypePool.empty,
  EntityTypePool.empty,
  ShorthandPool(Email, Markdown, Uri))
