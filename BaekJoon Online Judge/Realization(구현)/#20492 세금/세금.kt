fun main(){
    val n = readln().toInt()
    println("${(n * 0.78).toInt()} ${((n * 0.8) + (n * 0.2) * 0.78).toInt()}")
}