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
}

fun main() {
    val chapter3 = Chapter3()
    println(chapter3.circleArea(2.0))

    val a = intArrayOf(1, 2, 3)
    println(chapter3.increment(a))
    println(chapter3.increment(a))

    chapter3.prompt("Wooksang")

    println(chapter3.circleArea2(2.5))
}