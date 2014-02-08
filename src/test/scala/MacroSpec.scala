import org.specs2.mutable._

import name.dmitrym.MacroProxy

import java.net.URLClassLoader

class MacroSpec extends Specification {
  "The 'MacroProxy'" should {
    "return 'hello'" in {
      MacroProxy.hello must beEqualTo("hello")
    }

    "return 10" in {
      MacroProxy.getInt must beEqualTo(10)
    }

    "obtain engine of type ProdEngine for prod prefix" in {
      MacroProxy.getEngineForConfig("bootstrap.conf", "prod")
        .getClass.getSimpleName must beEqualTo("ProdEngine")
    }

    "obtain engine of type TestEngine for test prefix" in {
      MacroProxy.getEngineForConfig("bootstrap.conf", "test")
        .getClass.getSimpleName must beEqualTo("TestEngine")
    }

    "obtain engine of type DefaultEngine for default prefix" in {
      MacroProxy.getEngineForConfig("bootstrap.conf", "default")
        .getClass.getSimpleName must beEqualTo("DefaultEngine")
    }

    /**
     * this test will fail build with s"Configuration ${cfgPrefix} doesn't exist" message
     */
//    "must fail a build for doesntexist prefix" in {
//      MacroProxy.getEngineForConfig("bootstrap.conf", "doesntexist")
//      failure("Not expected to compile and run")
//    }

    /**
     * this test will fail build with s"Configuration property ${engineCfgPath} doesn't exist" message
     */
//    "must fail a build for invalid prefix" in {
//      MacroProxy.getEngineForConfig("bootstrap.conf", "invalid")
//      failure("Not expected to compile and run")
//    }

    /**
     * this test will fail build with s"${engineTypeName} doesn't exist in project class path" message
     */
//    "must fail a build for broken prefix" in {
//      MacroProxy.getEngineForConfig("bootstrap.conf", "broken")
//      failure("Not expected to compile and run")
//    }
  }
}
