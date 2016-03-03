package wordmap.components

import japgolly.scalajs.react.{Callback, ReactComponentB}
import japgolly.scalajs.react.vdom.prefix_<^.{^, _}
import org.singlespaced.d3js.d3

import scala.scalajs.js

object Hello {

  def drawMap(): Unit = {

    val width = 938
    val height = 620

    // beware of the casts
    val projection = d3.geo
      .mercator()
      .scale(150)
      .translate((width / 2.0, height / 1.41))
      .asInstanceOf[js.Function1[js.Tuple2[Double, Double], js.Tuple2[Double, Double]]]

    val path = d3.geo
      .path()
      .pointRadius(2)
      .projection(projection)
      // .projection(projection.apply _)
      .asInstanceOf[js.Function3[js.Any, Int, js.UndefOr[Int], d3.Primitive]]

    val svg = d3.select("#map")
      .append("svg")
      .attr("width", width)
      .attr("height", height)

    d3.json("/data/countries.topo.json", (error: js.Any, data: js.Any) => {

      val topojson = js.Dynamic.global.topojson
      val countries = data.asInstanceOf[js.Dynamic]
      val countries2 = topojson.feature(countries, countries.objects.countries).features.asInstanceOf[js.Array[js.Any]]

      svg.append("g")
        .attr("class", "countries")
        .selectAll("path")
        .data(countries2)
        .enter()
        .append("path")
        .attr("d", path)

      ()

    })

  }

  val component = ReactComponentB[Unit]("Hello")
    .stateless
    .noBackend
    .render_P { case x =>
      <.div(^.id := "map")
    }
    .componentDidMount(s => Callback(drawMap()))
    .build


}
