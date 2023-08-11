# 아이템 7 다 쓴 객체 참조를 해제하라
## 핵심 정리
* 어떤 객체에 대한 레퍼런스가 남아있다면 해당 객체는 가비지 컬렉션의 대상이 되지 않는다
* 자기 메모리를 직접 관리하는 클래스라면 메모리 누수에 주의해야 한다
  * 스택, 캐시, 리스너, 콜백, 어떤 콜렉션에 직접 담는 경우 등.. 메모리 누수의 위험이 있다
* 참조 객체를 null 처리하는 일은 예외적인 경우이다
  * 가장 좋은 방법은 유효 범위 밖으로 밀어내는 것
* 다 쓴 객체 참조를 위한 4가지 방법
  * 사용이 끝나면 직접 null 로 할당 해제
  * WeakHashMap 등 적절한 자료구조를 사용
  * 가장 오래된 객체를 할당 해제 하도록 직접 구현
  * 백그라운드의 쓰레드를 사용해서 주기적으로 클린업을 사용

## 완벽 공략
* NullPointerException
* WeakHashMap
  * 약한 참조 (weak reference)
* 백그라운드 쓰레드
  * ScheduledThreadPoolExecutor

### NullPointerException
* java8 의 Optional 을 활용해서 최대한 NPE 를 피하자
* NPE 가 발생하는 이유
  * 메소드에서 null 을 리턴하거나 null 체크를 하지 않았기 때문
* 메소드에서 적절한 값을 리턴할 수 없는 경우에 선택할 수 있는 방법
  * 예외를 던진다
  * null 을 리턴한다
  * Optional 을 리턴한다

### WeakHashMap
* 더이상 사용하지 않는 객체를 GC 로 메모리 해제시 자동으로 삭제해주는 Map
* Key 가 더이상 강하게 참조되는 곳(레퍼런스 되는 곳)이 없다면 해당 엔트리를 제거한다
* 레퍼런스 종류 4 가지
  * Strong: 직접 new 해서 할당하는 경우
  * Soft: new SoftReference<>(); 생성자 안에 Strong 레퍼런스를 넣어준다
    * 어떤 오브젝트를 Strong 하게 레퍼런스 하는 대상이 없고 Soft 하는 대상만 있다면 GC 의 대상이 된다
      * 단 GC 를 수행한다고 해서 무조건 할당이 해제 되는것이 아닌 메모리가 필요한 상황에만 할당이 해제된다
  * Weak: new WeakReference<>(); 생성자 안에 Strong 레퍼런스를 넣어준다
    * 메모리 공간의 여부와 상관 없이 Strong 하게 레퍼런스 하는 대상이 없다면 무조건 메모리 할당이 해제된다 
  * Phantom: new PhantomReference<>(strong, queue); 생성자 안에 Strong 레퍼런스와 Strong 레퍼런스 타입의 ReferenceQueue 를 넣어준다
    * 해당 객체가 사라짐과 동시에 큐에 들어가게 된다
      * 따라서 언제 이 객체가 사라졌는지 알 수 있다
      * 즉 메모리 해제 시점을 알아야 하는 경우에 사용할 수 있다
    * clear 를 해줘야 최종적으로 사라진다
    * 자원을 반납하는 용도로 사용하려면 PhantomReference 를 상속 받아서 사용해야 한다
* 맵의 엔트리를 맵의 Value 가 아니라 Key 에 의존해야 하는 경우에 사용할 수 있다
* 캐시를 구현하는데 사용할 수 있지만 캐시를 직접 구현하는건 권장하지 않는다

### ScheduledThreadPoolExecutor
* Thread 와 Runnable 을 학습한 다음엔 Executor 를 학습하자
* Thread
  * 쓰레드 생성을 위해 java 에서 미리 구현해 둔 클래스
  * sleep: 현재 쓰레드를 멈출 수 있으며, 자원을 놓아주지는 않고 제어권을 넘겨주기 때문에 데드락이 발생 가능하다
  * interrupt: 다른 쓰레드를 깨워서 interruptedException 를 발생시키며, interrupt 가 발생한 쓰레드는 예외를 catch 하여 다른 작업을 할 수 있다
  * join: 다른 쓰레드의 작업이 끝날 때 까지 기다리게 하며, 쓰레드의 순서를 제어할 때 사용할 수 있다
* Runnable
  * 별도에 쓰레드에 대한 리턴 타입이 따로 없다
  * 1개의 메소드만 가지는 함수형 인터페이스이며 쓰레드를 구현하기 위한 템플릿에 해당한다
  * 해당 인터페이스의 구현체를 만들고 Thread 객체 생성 시 넘겨주면 실행 가능하다
  * Thread 는 상속을 받아야 하기 때문에 다중상속이 안되는 자바에서는 Runnable 이 더 유리하다
* Callable
  * 별도 쓰레드에 대한 작업의 결과를 받고 싶은 경우에 사용한다
  * 작업에 대한 결과를 Future 를 통해 받을 수 있다
  * Future 로는 비동기의 결과값을 조합하거나 error 에 대한 핸들링이 불가능하다
* CompletableFuture
  * 자바 8부터 CompletableFuture 인터페이스가 추가 되었다
  * Future 인터페이스를 구현하면서 동시에 CompletionStage 인터페이스를 구현한다
    * CompletionStage 인터페이스는 비동기 연산 Step 을 제공하기 때문에 체이닝 형태의 조합이 가능하다
  * 따라서 작업에 대한 결과를 얻은 후 결과를 처리 가능하며 여러 CompletableFuture 완료를 기다리고 일괄 수행, 또는 CompletableFuture 중 하나가 완료 된 후 수행등이 가능하다
  * 즉 외부에서 작업을 완료 시킬 수 있고 콜백 등록 및 Future 조합, 에러 처리가 가능해졌다
* ExecutorService
  * 멀티 쓰레드를 통해서 동시에 여러가지 작업을 처리하기 위해서 사용한다
    * 순서는 보장되지 않으며 뒤죽박죽 처리한다
  * 쓰레드 풀을 만들때 사용하는 인터페이스이다
    * Executors.newFixedThreadPool(int): 인자 개수 만큼 고정된 쓰레드 풀을 생성한다
      * 내부적으로 사용하는 큐가 BlockingQueue 이며 동시성에 안전하다
    * newCachedThreadPool(): 필요할때 필요한 만큼 쓰레드 풀을 생성하며 이미 생성된 쓰레드가 있다면 재활용한다
      * 작업을 위한 공간이 딱 하나이며 쓰레드가 필요하면 무한정 생성할 수 있기 때문에 주의가 필요하다
    * newSingleThreadExecutor(): 싱글 쓰레드 이름 그대로 하나의 쓰레드로 모든 작업을 다 처리한다
    * newScheduledThreadPool(int): 일정 시간 뒤에 실행되는 작업, 주기적으로 수행되는 쓰레드 풀을 인자 갯수 만큼 생성한다
    * newWorkStealingPool(int): 시스템이 가용 가능한 만큼 쓰레드를 활용하는 ExecutorService 를 생성한다
* ForkJoinPool
  * java7 부터 사용 가능한 동시성 툴이다 (java Concurrency tool)
  * ExecutorService 와 비슷하며 ThreadPool 을 생성해서 여러 작업을 병렬로 처리 가능하다
  * ExecutorService 와 다른 부분
    * 동일한 여러개의 작업을 Sub Task 로 분리하여 각각 처리 가능하다
    * 처리한 결과를 최종적으로 합쳐서 결과를 만들어내는 방식이다 
      * 분리하여 각각 처리 (fork) 최종적으로 합쳐서 결과를 만들어냄(join)
    * 분할정복법(Divide And Conquer) 알고리즘 처럼 동작한다
    * fork: Task 를 분할해서 다른 쓰레드에서 처리시킨다
      * 하나의 Task 를 작은 여러 Task 로 나눈뒤 여러 쓰레드에 Task 를 할당한다
    * join: 다른 쓰레드에서 처리된 결과를 기다렸다 합친다
      * 다른 Task 가 완료될때 까지 기다린 후 결과를 합쳐 상위의 Task 로 전달한다
* 쓰레드풀의 개수를 정할때 주의할 점
  * CPU
    * CPU 를 많이 사용하는 작업이라면 CPU 개수보다 많은 양의 쓰레드를 할당해도 CPU 가 기다리기 때문에 CPU 개수만큼의 쓰레드 풀을 만드는게 효율적이다
  * I/O
    * DB 나 Http Call 인 경우는 각 상황마다 다르기 때문에 쓰레드의 개수에 대한 정답은 없다
      * 따라서 지연시간에 따라서 적절한 쓰레드 풀의 개수를 조절하는 성능 튜닝이 필요하다