fun isPalindrome(str: String): Boolean {
    val str = str.replace(" ".toRegex(), "")
    return str.contentEquals(str.reversed())
}

fun main() {
    println(isPalindrome("ab b b a"))
}