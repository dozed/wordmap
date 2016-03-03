package wordmap

import japgolly.scalajs.react.{ReactElement, _}
import japgolly.scalajs.react.extra.router._
import org.scalajs.dom
import wordmap.components.Hello

import scala.scalajs.js
import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

sealed trait Pages
object Pages {
  case object Hello extends Pages
}

object Routes {

  val routerConfig = RouterConfigDsl[Pages].buildConfig { dsl =>
    import dsl._

    ( emptyRule
      | staticRoute("hello", Pages.Hello) ~> renderR(r => Hello.component(()))
    ).notFound(redirectToPage(Pages.Hello)(Redirect.Replace))
      .renderWith(layout)

  }

  def layout(c: RouterCtl[Pages], r: Resolution[Pages]): ReactElement = {
    r.render()
  }

  val baseUrl = BaseUrl.fromWindowOrigin_/

  def router = Router(baseUrl, routerConfig)

}


object WordmapApp extends JSApp  {

  @JSExport
  override def main(): Unit = {
    Routes.router().render(dom.document.getElementById("container"))
  }

}
