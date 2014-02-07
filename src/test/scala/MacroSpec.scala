import com.typesafe.config.ConfigFactory
import org.specs2.mutable._

import name.dmitrym.MacroProxy

class MacroSpec extends Specification {
  "The 'MacroProxy'" should {
    "return 'hello'" in {
      MacroProxy.hello must beEqualTo("hello")
    }

    "return 10" in {
      MacroProxy.getInt must beEqualTo(10)
    }

    "obtain engine of type ProdEngine for prod prefix" in {
      MacroProxy.getEngineForConfig(
        ConfigFactory.parseString(
          """
          prod.engine = name.dmitrym.ProdEngine
          test.engine = name.dmitrym.TestEngine
          default.engine = name.dmitrym.DefaultEngine
          broken.engine = DoesNotExistEngine
          """.stripMargin
        ), "prod").getClass.getSimpleName must beEqualTo("ProdEngine")
    }

    "obtain engine of type TestEngine for test prefix" in {
      MacroProxy.getEngineForConfig(
        ConfigFactory.parseString(
          """
          prod.engine = name.dmitrym.ProdEngine
          test.engine = name.dmitrym.TestEngine
          default.engine = name.dmitrym.DefaultEngine
          broken.engine = DoesNotExistEngine
          """.stripMargin
        ), "test").getClass.getSimpleName must beEqualTo("TestEngine")
    }

    "obtain engine of type DefaultEngine for default prefix" in {
      MacroProxy.getEngineForConfig(
        ConfigFactory.parseString(
          """
          prod.engine = name.dmitrym.ProdEngine
          test.engine = name.dmitrym.TestEngine
          default.engine = name.dmitrym.DefaultEngine
          broken.engine = DoesNotExistEngine
          """.stripMargin
        ), "default").getClass.getSimpleName must beEqualTo("DefaultEngine")
    }

    /**
     * this test will fail build with s"Configuration ${cfgPrefix} doesn't exist" message
     */
//    "must fail a build for doesntexist prefix" in {
//      MacroProxy.getEngineForConfig(
//        ConfigFactory.parseString(
//          """
//          prod.engine = name.dmitrym.ProdEngine
//          test.engine = name.dmitrym.TestEngine
//          default.engine = name.dmitrym.DefaultEngine
//          broken.engine = DoesNotExistEngine
//          """.stripMargin
//        ), "doesntexist")
//      failure("Not expected to compile and run")
//    }

    /**
     * this test will fail build with s"Configuration property ${engineCfgPath} doesn't exist" message
     */
//    "must fail a build for invalid prefix" in {
//      MacroProxy.getEngineForConfig(
//        ConfigFactory.parseString(
//          """
//          prod.engine = name.dmitrym.ProdEngine
//          test.engine = name.dmitrym.TestEngine
//          default.engine = name.dmitrym.DefaultEngine
//          broken.engine = DoesNotExistEngine
//          invalid.engne = default.engine
//          """.stripMargin
//        ), "invalid")
//      failure("Not expected to compile and run")
//    }

    /**
     * this test will fail build with s"${engineTypeName} doesn't exist in project class path" message
     */
//    "must fail a build for broken prefix" in {
//      MacroProxy.getEngineForConfig(
//        ConfigFactory.parseString(
//          """
//          prod.engine = name.dmitrym.ProdEngine
//          test.engine = name.dmitrym.TestEngine
//          default.engine = name.dmitrym.DefaultEngine
//          broken.engine = DoesNotExistEngine
//          invalid.engne = default.engine
//          """.stripMargin
//        ), "broken")
//      failure("Not expected to compile and run")
//    }
  }
}
