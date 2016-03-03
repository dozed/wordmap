package wordmap.components

import japgolly.scalajs.react.vdom.prefix_<^._
import japgolly.scalajs.react.{BackendScope, Callback, ReactComponentB}

import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue

import japgolly.scalajs.react.ReactComponentB

import scalaz._, Scalaz._

object Hello {

  val component = ReactComponentB[Unit]("Hello")
    .stateless
    .noBackend
    .render_P { case x =>

      <.div()

    }
    .build

}
