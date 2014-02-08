package name.dmitrym

import com.typesafe.config.ConfigFactory

object MacroWB {
  import scala.util.control.Exception._
  import scala.reflect.macros.Context
  import scala.reflect.macros.TypecheckException

  def helloImpl(c: Context):c.Expr[String] = c.universe.reify {
    "hello"
  }

  def codegenIntSampleImpl(c: Context):c.Expr[Integer] = {
    c.Expr[Integer](c.parse("Int.box(10)"))
  }

  def getEngineForConfig(c: Context)(cfgResource: c.Expr[String], prefix: c.Expr[String]):c.Expr[Engine] = {
    import c.universe._

    val cfgRes = cfgResource.tree match {
      case Literal(Constant(s: String)) => s
      case _ => c.abort(c.enclosingPosition, "Can't parse config resource name expression")
    }

    val cfgPrefix = prefix.tree match {
      case Literal(Constant(s: String)) => s
      case _ => c.abort(c.enclosingPosition, "Can't parse configuration prefix name expression")
    }

    val configuration = ConfigFactory.load(this.getClass.getClassLoader, cfgRes)
    if(!configuration.hasPath(cfgPrefix)) c.abort(c.enclosingPosition, s"Configuration ${cfgPrefix} doesn't exist")
    val engineCfgPath = cfgPrefix + ".engine"
    if(!configuration.hasPath(engineCfgPath)) c.abort(c.enclosingPosition, s"Configuration property ${engineCfgPath} doesn't exist")
    val engineTypeName = configuration.getString(engineCfgPath)
    val parsedTree = c.parse(s"new ${engineTypeName}")
    catching(classOf[TypecheckException]) opt c.typeCheck(parsedTree) match {
      case None => c.abort(c.enclosingPosition, s"${engineTypeName} doesn't exist in project class path")
      case Some(tree) => c.Expr[Engine](tree)
    }
  }
}
