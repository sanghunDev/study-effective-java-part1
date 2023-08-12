# 아이템 13 clone 재정의는 주의해서 진행해라
## 핵심 정리
* 복사를 하기 위해서는 Cloneable 인터페이스 구현 후 오버라이드 필요
### 애매모호한 clone 규약
* clone 규약
* x.clone() != x
  * 반드시 true
  * 반드시 다른 객체여야된다
* x.clone().getClass() == x.getClass()
  * 반드시 true
  * 복사한 클래스와 원본 클래스가 같아야 한다
* x.clone().equals(x)
  * true 가 아닐 수 있음
  * equals 를 하는 경우 같을수도 있고 다를 수도 있다
    * 다른 필드 값을 셋팅 하는 경우도 있기 때문
* 불변 객체라면 다음으로 충분
  * Cloneable 인터페이스를 구현
    * clone 메서드를 재정의, 이때 super.clone 을 사용
