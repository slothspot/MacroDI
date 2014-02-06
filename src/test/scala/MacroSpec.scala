import org.specs2.mutable._

import name.dmitrym.MacroProxy

class MacroSpec extends Specification {
  "The 'MacroProxy'" should {
    "return 'hello'" in {
      MacroProxy.hello must beEqualTo("hello")
    }
  }
}
