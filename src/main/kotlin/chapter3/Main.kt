package chapter3

class Main {
}

fun main() {
    println(readInt())

    fun readInt() = readLine()!!.toInt() // 지역 함수는 함수 내부에서만 통용된다.

    val s = "Hello!"

    /*
    지역 함수는 자신을 둘러싼 블록에 선언된 변수나 함수에 접근할 수 있다.
     */
    fun swap(i: Int, j: Int): String {
        val chars = s.toCharArray()
        val tmp = chars[i]
        chars[i] = chars[j]
        chars[j] = tmp

        return chars.concatToString()
    }
}
