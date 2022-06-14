package chapter3

import kotlin.math.PI

class Chapter3 {

    /*
    코틀린 표준 상수 PI를 사용
     */
    fun circleArea(radius: Double): Double {
        return PI*radius*radius
    }

    /*
    함수 파라미터가 참조(Call by Reference 라면 함수 외부에서 파라미터로 준 객체여도 값이 변한다.(기본적으로 Call by Value임)
     */
    fun increment(a: IntArray): Int {
        return ++a[0]
    }

    /*
    Unit 타입은 자바의 void 에 해당하는 코틀린 타입으로, 함수 리턴이 없는경우 Unit 을 생략해도 코틀린 컴파일러가 Unit 함수를 정의한다고 가정한다.
     */
    fun prompt(name: String): Unit {
        println("***** Hello, $name! *****")
    }

    /*
    식이 본문인(expression-body) 함수.
    단일 식으로만 함수가 구성될 수 있으면, return 키워드와 {}를 생략할 수 있다.
    반환 타입도 생략가능(타입 추론)
     */
    fun circleArea2(radius: Double): Double = PI*radius*radius

    fun rectangleArea(width: Double, height: Double): Double = width*height

    fun swap(s: String, from: Int, to: Int): String {
        val chars = s.toCharArray()
        val tmp = chars[from]
        chars[from] = chars[to]
        chars[to] = tmp
        return chars.concatToString()
    }

    fun mul(a: Int, b: Int) = a*b // 1
    fun mul(a: Int, b: Int, c: Int) = a*b*c // 2
    fun mul(s: String, n: Int) = s.repeat(n) // 3
    fun mul(o: Any, n: Int) = Array(n) { o } // 4

    fun restrictToRange(
        from: Int = Int.MIN_VALUE,
        to: Int = Int.MAX_VALUE,
        what: Int
    ): Int = from.coerceAtLeast(to.coerceAtMost(what))
}

fun main() {
    val chapter3 = Chapter3()
    println(chapter3.circleArea(2.0))

    val a = intArrayOf(1, 2, 3)
    println(chapter3.increment(a))
    println(chapter3.increment(a))

    chapter3.prompt("Wooksang")

    println(chapter3.circleArea2(2.5))

    val w = readLine()!!.toDouble()
    val h = readLine()!!.toDouble()
    println(chapter3.rectangleArea(width = w, height = h)) // named argument
    println(chapter3.rectangleArea(height = h, width = w)) // 이런식으로도 호출 가능하다

    println(chapter3.swap("Hello", 1, 2))
    println(chapter3.swap("Hello", from = 1, to = 2))
    println(chapter3.swap("Hello", to = 3, from = 0))
    println(chapter3.swap("Hello", 1, to = 3))
    println(chapter3.swap(from = 1, s = "Hello", to = 2))
    println(chapter3.swap(s = "Hello", 1, 2))
    println(chapter3.swap(s = "Hello", 1, to = 2))

    chapter3.mul(1, 2) // Int가 Any의 하위 타입이므로 1과 4중에 1을 선택
    //chapter3.mul(1, 2L) // (Int, Long)타입을 받을 수 있는 함수가 없으므로 오류
    chapter3.mul(1L, 2) // (Long, Int) 타입을 받을 수 있는 함수는 4번
    chapter3.mul("0", 3) // String이 Any의 하위 타입이기 때문에 3과 4중에 3을 선택
    chapter3.mul("0" as Any, 3) // (Any, Int)를 받을 수 있는 함수는 4뿐이므로 4를 선택

    println(chapter3.restrictToRange(10, 20, 5))
}