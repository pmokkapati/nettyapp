import org.scalatestplus.play.FakeApplicationFactory
import play.api.inject.DefaultApplicationLifecycle
import play.api.{Application, ApplicationLoader, Configuration, Environment}
import play.core.DefaultWebCommands

trait NettyApplicationFactory extends FakeApplicationFactory {

  private class NettyApplicationBuilder {
    def build(): Application = {
      val env = Environment.simple()
      val context = ApplicationLoader.Context.create(env)
      val loader = new NettyApplicationLoader()
      loader.load(context)
    }
  }

  def fakeApplication(): Application = new NettyApplicationBuilder().build()

}
