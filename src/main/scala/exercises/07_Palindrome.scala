package exercises

object Palindrome {
  /** 
   *  True if the string is a palindrome, after removing all non-alphabetic 
   *  characters (e.g., spaces, numbers, and punctuation).
   *  
   *  Hint: the Scala collections API is your friend
   *  http://docs.scala-lang.org/overviews/collections/overview.html 
   */
  def isPalindrome(s: String): Boolean = {
    // remove punctuation and convert every letter to lowercase
    val data = s.filter(_.isLetter).map(_.toLower)
    data == data.reverse
  }

}