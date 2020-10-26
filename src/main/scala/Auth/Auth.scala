package Auth

import db.TokenHashDao

object Auth {
  def getMD5Hash(token: String): String = java.security.MessageDigest.getInstance("MD5").digest(token.getBytes()).map(0xFF & _).map { "%02x".format(_) }.foldLeft(""){_ + _}
  def checkaccess(token: String) = {
    if (TokenHashDao.get.contains(token)){
      true
    }
    else{
      print("You shall not pass")
      false
    }
  }
}
