package db

import java.sql.{DriverManager, ResultSet}

import scala.collection.mutable.ListBuffer

object TokenHashDao {
  def get = {
    var listBufferOfToken = new ListBuffer[String]()
    val conStr = "jdbc:postgresql://localhost:5432/proxy?user=postgres&password=root"
    val conn = DriverManager.getConnection(conStr)
    try {
      val stm = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)

      val rs = stm.executeQuery("SELECT * from proxy")

      while(rs.next) {
        listBufferOfToken+= rs.getString("token")
      }
    } finally {
      conn.close()
    }
    listBufferOfToken.toList
  }
}
