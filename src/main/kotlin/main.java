public class main {
    public static void main(String[] args) {
        String s1 = "Hello!";
        String s2 = "He" + "llo!";
        String s3 = "He";
        String s4 = "llo!";

        System.out.println(s1.equals(s2));
        System.out.println(s1 == s2);
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());
        System.out.println((s3+s4).hashCode()); //s3와 s4는 다른객체이지만 Hello!가 String 상수풀에 저장되어 있으므로 s1, s2, s3+s4가 같은 참조를 가지고있다.
    }
}
