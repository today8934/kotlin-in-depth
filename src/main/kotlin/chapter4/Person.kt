package chapter4

class Person(val fullName: String /* 주생성자 파라미터*/) {
    /*
    주생성자 파라미터에서 val 이나 var 키워드를 사용하면 클래스 멤버변수 선언을 사용하지 않을 수도 있다.(권장)
     */
    private val firstName: String
    private val familyName: String

    /*
    init 블록에는 return 문을 사용할 수 없다.
    주생성자 파라미터는 init 블록 밖이나 프로퍼티 초기화 외에는 사용할 수 없다.
     */
    init {
        val names = fullName.split(" ")

        if (names.size != 2) {
            throw IllegalArgumentException("Invalid name: $fullName")
        }
        firstName = names[0]
        familyName = names[1]
    }

    fun main() {
        println("fullName: $fullName")
    }

}

class Room(vararg val persons: Person /* 함수와 마찬가지로 디폴트 값과 vararg 를 생성자 파라미터에 사용할 수 있다. */) {
    fun showNames() {
        for (person in persons) println(person.fullName)
    }
}

class Person2 {
    val firstName: String
    val familyName: String

    constructor(firstName: String, familyName: String) {
        this.firstName = firstName
        this.familyName = familyName
    }

    constructor(fullName: String) {
        val names = fullName.split(" ")
        if (names.size != 2) {
            throw IllegalArgumentException("Invalid name: $fullName")
        }
        firstName = names[0]
        familyName = names[1]
    }
}

class Person3 {
    val fullName: String

    constructor(firstName: String, familyName: String): this("$firstName $familyName") //생성자 위임 호출

    constructor(fullName: String) {
        this.fullName = fullName
    }
}

class Person4(val fullName: String) { /* Person3의 두번째 생성자를 주생성자처럼 쓸수도 있다. */
    constructor(firstName: String, familyName: String): this("$firstName $familyName")
}