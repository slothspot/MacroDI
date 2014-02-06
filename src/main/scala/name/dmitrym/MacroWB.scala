package name.dmitrym

object MacroProxy {
  def hello: String = macro MacroWB.helloImpl
}

object MacroWB {
  import scala.reflect.macros.Context

  def helloImpl(c: Context) = c.universe.reify {
    "hello"
  }
}
