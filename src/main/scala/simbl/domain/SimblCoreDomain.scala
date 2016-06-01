package simbl.domain

import longevity.subdomain.CoreDomain
import longevity.subdomain.ShorthandPool
import longevity.subdomain.entity.EntityTypePool
import longevity.subdomain.ptype.PTypePool

class SimblCoreDomain extends CoreDomain(
  "Simple Blogging",
  PTypePool(User, Blog, BlogPost),
  EntityTypePool(UserProfile),
  ShorthandPool(Email, Markdown, Uri))
