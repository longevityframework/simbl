package simbl

import org.scalatest.Suites
import scala.concurrent.ExecutionContext.Implicits.global

class SimblRepoCrudSpec extends Suites(
  TestSimblContext.longevityContext.repoCrudSpec)
