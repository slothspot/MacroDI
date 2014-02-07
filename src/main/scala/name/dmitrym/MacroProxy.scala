package name.dmitrym

import com.typesafe.config.Config

object MacroProxy {
  def hello: String = macro MacroWB.helloImpl
  def getInt: Integer = macro MacroWB.codegenIntSampleImpl

  def getEngineForConfig(cfg: Config, prefix: String):Engine = macro MacroWB.getEngineForConfig
}