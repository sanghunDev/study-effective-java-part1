# 아이템 8 finalizer 와 cleaner 사용을 피하라
## 핵심 정리
* finalizer 와 cleaner 는 즉시 수행된다는 보장이 없다
* finalizer 와 cleaner 는 실행되지 않을 수도 있다
* finalizer 동작 중 예외가 발생하면 정리 작업이 처리되지 않을 수도 있다
* finalizer 와 cleaner 는 심각한 성능 문제가 있다
* finalizer 는 보안 문제가 있다
* 반납할 자원이 있는 클래스는 AutoCloseable 을 구현
  * 클라이언트에서 close() 호출 또는 try-with-resource 를 사용하는게 좋다
* cleaner 는 혹시나 try-with-resource 를 안쓰더라도 GC 가 되는 경우 자동으로 종료 되도록 안전장치로 사용하자

## 완벽 공략
### 정적이 아닌 중첩 클래스는 자동으로 바깥 객체의 참조를 가진다
* 중첩 클래스는 static 클래스로 만들자 
* 람다 역시 바깥 객체의 참조를 가지기 쉽다
  * 바깥의 변수 등을 참조 하는 경우에 생긴다
