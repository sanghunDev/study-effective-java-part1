# 아이템 6 불필요한 객체 생성을 피하라
## 핵심 정리
* 객체 생성은 비싸기 때문에 피하라는 의미가 아니며 아래와 같은 경우에 주의하자
* 문자열
  * 사실상 동일한 객체라서 매번 새로 new 로 만들 필요가 없다
  * new String("test"); 를 사용하지 않고 문자열 리터럴 "test"; 를 사용해 기존에 동일한 문자열을 재사용하는 것이 좋다
* 정규식 (Pattern)
  * 생성 비용이 비싼 객체이기 때문에 반복해서 생성하기 보다는 캐싱하여 재사용 하는것이 좋다
  * 하나의 변수를 생성해서 재사용 하는것이 성능면에서 훨씬 효율적이다
* 오토박싱 (auto boxing)
  * 기본 타입 (int 등..) 을 박싱된 기본 타입(Integer)으로 상호 변환하는 기술
  * 기본 타입과 박싱된 기본 타입을 섞어서 사용하면 변환하는 과정에서 불필요한 객체가 생성 될 수 있다

## 완벽 공략
### Deprecation (사용 자제 API)
* 클라이언트가 사용하지 않길 바라는 코드
* 사용 자제를 권장하며 대안을 제시하는 방법이 있다
* @Deprecated
  * 컴파일시 경고 메세지를 통해 사용 자제를 권장하는 API 라는 것을 클라이언트에게 알려줄 수 있다
* @deprecated
  * 문서화(java doc)에 사용하며 왜 해당 API 를 사용 자제를 권장하는지, 권장하는 API 가 어떤 것인지 알려 줄 수 있다

### 내부적으로 정규 표현식이 사용되는곳
* 내부적으로 Pattern 이 쓰이는 메서드는 사용을 주의하자
  * String.matches(String regex)
  * String.split(String regex)
    * 대안 -> Pattern.compile(regex).split(str)
    * 한글자로 찾는것은 빠르기 때문에 미리 컴파일해서 사용하지 않아도 괜찮다
      * String.split(",") 이런 경우는 OK 
      * String.split(",,,") 이런 경우는 패턴을 만드는게 더 효율적이다 
  * String.replace*(String regex, String replacement)
    * 대안 -> Pattern.compile(regex).matcher(str).replaceAll(repl)

