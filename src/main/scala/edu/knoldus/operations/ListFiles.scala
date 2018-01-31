package edu.knoldus.operations

import java.io.File

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ListFiles {

  def getListOfFiles(directoryName: File): Future[List[File]] = {
    Future {
      def getListOfFilesRecursive(inputListOfFiles: List[File], resultantListOfFiles: List[File]): List[File] = {
        inputListOfFiles match {
          case fileName :: restList if fileName.isFile => getListOfFilesRecursive(restList, fileName :: resultantListOfFiles)
          case fileName :: restList => getListOfFilesRecursive(fileName.listFiles.toList ::: restList, resultantListOfFiles)
          case Nil => resultantListOfFiles
        }
      }

      if (directoryName.exists && directoryName.isDirectory) {
        val files = directoryName.listFiles.toList
        getListOfFilesRecursive(files, List[File]())
      }
      else {
        throw new Exception("directory not found")
      }
    }
  }

}
