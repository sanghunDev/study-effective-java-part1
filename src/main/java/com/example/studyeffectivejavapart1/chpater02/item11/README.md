# 아이템 11 equals 를 재정의 하려거든 hashCode 도 재정의 하라
## 핵심 정리
### hashCode 규약
* equals 비교에 사용하는 정보가 변경되지 않았다면 hashCode 는 매번 같은 값을 반환해야 한다
  * 변경되거나 애플리케이션을 다시 실행했다면 hashCode 값이 달라질 수 있다
* 두 객체에 대한 equals 가 같다면 hashCode 의 값도 같아야 한다
* 두 객체에 대한 equals 가 다르더라도 hashCode 의 값은 같을 수 있지만 해시 테이블 성능을 고려해 다른 값을 리턴하는게 좋다
  * 해시 충돌(hashCode 값이 같은 경우)이 발생할 수 있다
  * 성능 저하가 일어나는 이유
    * hash 저장소에서 hash 값을 통해서 꺼내기 때문에 index 를 통해서 꺼내는 것 처럼 빠르다
    * 하지만 중복이 발생하면 해당 hashCode 에 대한 모든 값을 꺼내보기 때문에 LinkedList 에서 값을 찾는 것 처럼 비효율 적으로 변한다
* equals 와 hashCode 를 같이 재정의 하는 이유
  * hash 값을 사용하는 Collection 을 사용할 때 문제가 발생한다
  * hash 자료 구조에서는 hashCode 를 먼저 비교하고 결과가 같다면 equals 를 비교하기 때문 

### hashCode 구현 방법
![img.png](img/img.png)
* 31 사용하는 이유는 예전 개발자들이 31이라는 소수를 썻을때 hash collision 이 가장 적게 일어났다는 연구 결과 때문
  * 지금은 관습처럼 사용한다
1. 핵심 필드 하나의 값의 해쉬값을 계산해서 result 값을 초기화 한다
2. 기본 타입은 Type.hashCode
   * 참조 타입은 해당 필드의 hashCode
   * 배열은 모든 원소를 재귀적으로 위의 로직을 적용하거나, Arrays.hashCode
   * result = 31 * result + 해당 필드의 hashCode 계산값
3. result 를 리턴한다
* 주의사항
  * 만약 지연 초기화를 사용한다면 쓰레드 안정성도 고려해야 한다
  * 성능 때문에 핵심적인 필드를 해시코드 계산시 제외하면 안된다
  * 해시코드 계산 규칙을 API 에 노출하지 말자