package openapi.generator.example

import zio.http.endpoint.openapi.OpenAPI
import zio.http.gen.scala.{Code, CodeGen}

object Example extends App {

  val jsonString = scala.io.Source.fromResource("petstore-minimal.json").mkString
  val openapi: Either[String, OpenAPI] = OpenAPI.fromJson(jsonString)
  
  val openApiSuccess = openapi match {
    case Right(value) => value
    case Left(value) => throw new Exception(value)
  }
  
  val files: Code.Files = zio.http.gen.openapi.EndpointGen.fromOpenAPI(openApiSuccess)

  // 'target' generated

  val uri = "target/generated-sources"
  CodeGen.writeFiles(files, java.nio.file.Paths.get(uri), "base", None)
  
}
