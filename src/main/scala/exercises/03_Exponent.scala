package exercises

object Exponent {
  
  /**
   * Provide a recursive implementation of the exponent function:
   * 		expt(n, e) = n^e
   * 
   * For a description of a recursive exponent algorithm and code in Java, see:
   * 		https://www.cs.cmu.edu/~ref/pgss/lecture/7/
   * 
   * NOTE: Scala has a built-in exponent function, but you're not allowed to
   * use it to implement `expt`
   */
  def expt(n: Double, e: Int): Double =
      if (e == 0)
        1
      else if (n == 0)
        0 
      else if (e < 0)
        1 / expt(n, e)      
      else
        n * expt(n, e-1)

}