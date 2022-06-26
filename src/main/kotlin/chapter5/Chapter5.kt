package chapter5

fun main() {
    println(sum(intArrayOf(1, 2, 3)))
    println(max(intArrayOf(1, 2, 3)))
}

/*
op를 함수처럼 호출할 수 있다.
람다식: 단순한 형태의 문법을 사용해 정의하는 이름이 없는 지역 함수.
 */
fun aggregate(numbers: IntArray, op: (Int, Int) -> Int): Int {
    var result = numbers.firstOrNull() ?: throw IllegalArgumentException("Empty array")

    for (i in 1..numbers.lastIndex) result = op(result, numbers[i])

    return result
}

/*
result와 op는 함수 파라미터 역할을 하며 -> 다음에 오는 식은 결과를 계산하는 식이다.
 */
fun sum(numbers: IntArray) = aggregate(numbers) { result, op -> result + op }

fun max(numbers: IntArray) = aggregate(numbers) { result, op -> if (op > result) op else result}