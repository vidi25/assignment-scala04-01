package edu.knoldus

import java.io.File

import edu.knoldus.operations.ListFiles
import org.apache.log4j.Logger

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object MainApplication {

  def main(args: Array[String]): Unit = {

    val log = Logger.getLogger(this.getClass)
    val directoryPath = "/home/knoldus/folder1"
    val app = new ListFiles
    val listOfFiles = app.getListOfFiles(new File(directoryPath))
    listOfFiles onComplete {
      case Success(list) => log.info(s"List of files = $list")
      case Failure(customException) => log.info(s"${customException.getMessage}")
    }
    val sleepTime= 200
    Thread.sleep(sleepTime)
  }

}
