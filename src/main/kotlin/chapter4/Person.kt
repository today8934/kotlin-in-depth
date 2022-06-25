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

fun main() {
    val person5 = Person5("John", "Doe")

    //println(person5.firstName) Person5 클래스에서 firstName과 familyName은 private이다.
    //println(person5.familyName)
    println(person5.fullName())

    //val empty = Empty() Empty 클래스의 주생성사자 private이기때문에 init할 수 없다.

    val id = Person6.Id("John", "Doe")
    val person6 = Person6(id, 25)
    person6.showMe()

    var name: String? = null

    fun initialize() {
        name = "John"
    }

    fun sayHello() {
        println(name!!.uppercase())
    }

    initialize()
    sayHello()
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

/*
다음 코드에서 firstName과 familyName은 private이므로 main() 함수에서 이 둘을 볼 수 없다. 반면 fullName()은 public이다.
 */
class Person5(
    private val firstName: String,
    private val familyName: String
    ) {
    fun fullName() = "$firstName $familyName"
}

/*
주생성자의 가시성을 지정하려면 constructor 키워드를 꼭 명시해야 한다.
 */
class Empty private constructor() {
    fun showMe() = println("Empty")
}

class Person6(val id: Id, val age: Int) {
    class Id(val firstName: String, val familyName: String)
    fun showMe() = println("${id.firstName} ${id.familyName}, $age")
}

class Person7(private val id: Id, private val age: Int) {
    class Id(private val firstName: String, private val familyName: String) {
        fun nameSake(person: Person7) = person.id.firstName == firstName
    }
}

/*
코틀린의 모든 참조타입은 기본적으로 null이 될 수 없다.
코틀린에서 널이 될 수 있는 값을 받으려면, 파라미터 선언 시 파라미터 타입 뒤에 물음표(?)를 붙여서 널이 될 수 있는 타입임을 명시해야한다.
 */
fun isLetterString(s: String): Boolean {
    if (s.isEmpty()) return false
    for (ch in s) {
        if (!ch.isLetter()) return false
    }
    return true
}

fun isBooleanString(s: String?) = s == "false" || s == "true"

fun nullableToNotNullable() {
    val s: String? = "abc"
    val ss: String

    //ss변수에 s를 넣으면 컴파일 오류가 발생한다. not nullable 타입에 nullable 타입을 넣을 수 없기 때문
}

/*
스마트캐스트
 */
fun describeNumber(n: Int?) = when (n) {
    null -> "null"
    in 0..10 -> "small"
    in 11..100 -> "large"
    else -> "out of range"
}

/*
스마트캐스트
 */
fun isSingleChar(s: String?) = s != null && s.length ==1

