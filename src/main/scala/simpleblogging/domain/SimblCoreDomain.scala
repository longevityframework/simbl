package simpleblogging.domain

import longevity.subdomain.CoreDomain
import longevity.subdomain.ShorthandPool
import longevity.subdomain.entity.EntityTypePool
import longevity.subdomain.ptype.PTypePool

class SimblCoreDomain extends CoreDomain(
  "Simple Blogging",
  PTypePool.empty,
  EntityTypePool.empty,
  ShorthandPool(Email, Markdown, Uri))
