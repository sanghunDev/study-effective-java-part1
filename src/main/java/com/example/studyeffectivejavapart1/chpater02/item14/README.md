# 아이템 14 Comparable 을 구현할지 고민하라
## 핵심 정리
* Object 가 제공하는 메서드는 아니지만 일반적으로 많이 사용하는 인터페이스
* 자연적인 순서를 정해줄때 사용하는 인터페이스이다
* 순서가 있는 경우 순서를 정해주는 방법을 정해줄 수 있다
* 제네릭을 지원하기 때문에 컴파일 시점에 타입 체킹이 가능하다
* equals 와 굉장히 비슷하지만 순서가 있고 제네릭을 지원 한다는 부분이 다르다

### compareTo 규약
* BigDecimal 참고
* Object.equals 에 더해서 순서까지 비교할 수 있으며 제네릭을 지원한다
* 자기 자신이 compareTo 에 전달된 객체보다 작으면 음수, 같으면 0, 크다면 양수를 리턴한다
* 반사성, 대칭성, 추이성을 만족해야 한다
* 반드시 따라야 하는 것은 아니지만 x.compareTo(y) == 0 이라면 x.equals(y) 가 true 여야 한다

### compareTo 구현 방법
* 자연적인 순서를 제공할 클래스에 implements Comparable<T> 을 선언한다
* compareTo 메서드를 재정의 한다
* compareTo 메서드 안에서 기본 타입은 박싱된 기본 타입의 compareTo 을 사용해 비교한다
* 핵심 필드가 여러개라면 비교 순서가 중요하다
  * 순서를 결정하는데 있어 가장 중요한 필드를 비교하고 그 값이 0 이라면 다음 필드를 비교한다
* 기존 클래스를 확장하고 필드를 추가하는 경우 compareTo 규약을 지키기 어렵다
  * 하위 클래스에서 implements Comparable 를 해서 compareTo 를 재정의 하더라도 파라미터를 변경하는 순간 사용되지 않는다
  * 별도의 Comparator 를 만들어서 compare 를 따로 구현해서 비교해서 구현은 가능하지만 굉장히 번거롭다
    * equals 규약도 깨지기 때문에 equals 에서 권장하는 것 처럼 확장이 필요한 경우 Composition 을 활용하자
    * Composition 을 활용해서 구현 후 view 의 역할을 하는 메서드를 제공해서 비교하도록 처리하자 
* java8
  * Comparator 인터페이스가 제공하는 static 메서드를 활용해서 인스턴스를 만들 수 있다
    * comparing 등..
  * 인스턴스 생성 후 default 메서드를 체이닝 해서 사용 가능하다
    * thenComparingInt 등..
  * 성능이 약 10% 정도 더 안좋다고 하지만 성능에 매우 민감하지 않으면 사용하기 좋은 방법이다
