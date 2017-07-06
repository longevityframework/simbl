package simbl

import org.scalatest.Suites

class SimblRepoCrudSpec extends Suites(
  TestSimblContext.longevityContext.repoCrudSpec)
