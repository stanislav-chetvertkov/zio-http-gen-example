package openapi.generator.example

import base.Pets
import base.component.Pet
import zio.http.endpoint.Endpoint
import zio.http.endpoint.EndpointMiddleware.None
import zio.http.{Handler, Route, Server}
import zio.{Scope, ZIO, ZIOAppArgs, ZIOAppDefault, ZNothing}

object ServerExample extends ZIOAppDefault {

  val endpoint: Endpoint[Unit, Pet, ZNothing, Unit, None] = Pets.createPets
  val route: Route[Any, Nothing] = endpoint.implement(Handler.fromFunctionZIO(pet => {ZIO.logInfo("Pet: " + pet.toString)}))

  // create zio http app from the generated endpoint
  val httpApp = route.toHttpApp

  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] = {
    Server.serve(httpApp).provide(Server.defaultWithPort(8082))
  }
}
