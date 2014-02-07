package name.dmitrym

trait Engine {
  def doSomething:Unit
}

class DefaultEngine extends Engine {
  def doSomething = println("Do something from DefaultEngine")
}

class ProdEngine extends Engine {
  def doSomething = println("Do something from ProdEngine")
}

class TestEngine extends Engine {
  def doSomething = println("Do something from TestEngine")
}