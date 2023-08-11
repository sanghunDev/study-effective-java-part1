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

### 가비지 컬렉션
* GC 관련 공부 크게 3가지로 분류 가능
1. Java 에 대한 옵션
  * GC 로그를 남기는 옵션 등..
2. Tool
  * jstat, JConsole
  * 메모리 모니터링, GC 로그 등.. 확인하는 용도
3. 기본 개념
* 각 GC 방식마다 다르지만 기본 개념을 추린다면 아래와 같다
  * Mark
    * 객체 중 계속 참조를 가지고 있는지(살아 있는지) 식별한다
  * Sweep
    * 필요 없는 오브젝트를 식별한다
  * Compact
    * 필요 없는 객체들을 지우고 살아 있는 객체들을 한곳으로 모은다
      * 파편화된 메모리를 합쳐준다
  * Young Generation (Eden, Survivor)
    * 객체들은 보통 생명 주기가 짧다
    * 최초에 Eden 에 만들어지고 Eden 이 꽉 차게 된다면 GC 상에서 예외 발생
      * Survivor0, Survivor1 등 의 공간으로 객체 이동이 발생한다 
      * Survivor 영역으로 이동 후에도 Young 영역에 공간이 부족하면 오래 살아남은 객체들이 Old Generation 으로 이동한다
  * Old Generation
    * S0, S1 등의 메모리 정리에도 오래 살아남게 되는 오브젝트가 이 공간에 할당된다
  * Minor GC
    * Young 영역에서 발생하는 GC 를 말한다
  * Major GC
    * Old 영역이나 Perm 영역에서 발생하는 GC 를 말한다
  * Full GC
    * Young 과 Old 모두 GC 를 하는 대규모 청소 작업

### Full GC 알고리즘 종류

1. Serial GC
* 대기시간이 많아도 크게 문제가 되지 않는 시스템에서 사용
* Young 영역과 Old 영역이 연속적으로 처리하며 하나의 CPU 를 사용한다
  * Sun 에서 이를 수행할때를 Stop-the-World 라고 부른다
* 살아있는 객체들은 Eden 영역에 존재 -> Eden 이 꽉 차면 Survivor 영역으로 이동
  * To Survivor 에 들어가기 너무 큰 객체가 있다면 바로 Old 영역으로 이동
* To Survivor 영역이 다 찬 경우 Eden 영역이나 From Survivor 영역에 남아 있는 객체들은 Old 영역으로 이동
* 이후 Old 영역 또는 Perm 영역에 있는 객체들은 Mark-sweep-compact 알고리즘을 따른다
  * 살아있는것과 사용이 안되는것을 표시하고 정리하는 과정 
2. Parallel GC
* java 8의 기본 GC 는 Parallel GC
  * Throughput Collector 로도 알려져 있는 방식이다
* 다른 CPU 가 대기 상태로 있는 것을 최소화 하기 위한 방식이다
* Serial GC 와 다르게 Young 영역에서의 콜렉션을 병렬로 처리한다
  * Young 영역에서 멀티 스레드 사용하기 때문에 Stop-the-World 가 짧다
* 많은 CPU 를 사용하기 때문에 GC 의 부하를 줄이고 애플리케이션의 처리량을 증가시킬 수 있다
* Old 영역의 GC 는 Serial GC 와 마찬가지로 Mark-sweep-compact 알고리즘을 사용한다
3. Parallel Compacting GC
* Parallel GC 와 다른 점은 Old 영역에서 새로운 알고리즘을 사용한다
  * Parallel GC 는 Young 영역에 대해서만 멀티 스레드 방식을 사용
  * Parallel Compacting GC 는 Old 영역까지 멀티 스레드 방식을 사용
* Old 영역의 GC
  * Mark: 살아있는 객체를 식별하여 표시
  * Sweep: 이전에 GC 를 수행하여 컴팩션된 영역에 살아 있는 객체의 위치를 조사
  * Compact: 컴펙션을 수행하는 단계이며 수행 이후에는 컴팩션된 영역과 비어있는 영역으로 나뉜다
* Parallel GC 와 마찬가지로 여러 CPU 를 사용하는 서버에 적합하다
4. CMS GC (Concurrent Mark-Sweep GC)
* 실제 사용에는 많은 테스트가 필요하며 추천하지 않는 방식이다
  * java9 부터는 deprecated 되었으며 java14 부터는 사용이 중지 되었다 
* Stop-the-World 로 애플리케이션이 멈추는 현상을 줄이기 위해 만든 GC
  * low-latency collector 로도 알려져 있다
* 힙 메모리의 크기가 클 때 적합하며 Young 영역에 대한 GC 는 Parallel GC 와 마찬가지로 멀티 스레드 방식이다
* CMS 는 추가적인 옵션으로 점진적인 방식을 지원하기 때문에 Young 영역의 GC 를 더 잘게 쪼개서 서버의 대기 시간을 줄일 수 있다
  * CPU 가 많지 않고 시스템의 대기 시간이 짧아야 하는 경우, 2개 이상의 프로세서를 사용하면 적합하다
* Old 영역의 GC
  * Initial Mark: GC Root 가 참조하는 객체만 마킹한다 
    * Stop-the-World 가 발생하며 살아있는 객체를 찾는 단계이다
  * Concurrent Mark: 참조하는 객체를 따라가며 지속적으로 마킹한다
    * Stop-the-World 가 없이 이루어 지며 서버 수행과 동시에 살아있는 객체에 표시한다
  * Remark: Concurrent Mark 과정에서 변경된 부분이 있는지 다시 한번 마킹하는 단계이다
    * Stop-the-World 가 발생한다
  * Concurrent Sweep: 접근할 수 없는 객체를 제거한다
    * Stop-the-World 가 발생하지 않고 실행된다
* 단점
  * CMS 는 컴팩션 단계를 거치지 않기 때문에 메모리를 몰아 놓는 작업을 수행하지 않는다
    * 따라서 메모리 파편화가 이루어져 있기 때문에 GC 수행 이후 Old 영역의 % 를 따로 지정해야 한다
5. G1 GC
* java 9 부터 기본으로 사용되는 GC 이며 현재 까지 가장 좋다
  * Stop-the-World 의 시간이 가장 짧다
* 지금까지 설명한 GC 중 처리 속도가 가장 빠르고 큰 메모리 공간에서 멀티 프로세스 기반으로 운영되는 애플리케이션에 적합하다
  * CMS GC 를 개선해서 만든 GC 이다
* 다른 GC 들과는 구조가 다르다
  * Heap 을 Region 이라는 일정한 부분으로 나눠서 관리한다
    * 기존 GC와 다르게 물리적으로 영역을 나누는 것이 아닌 Region 이라는 개념을 도입했다
      * Heap 을 균등하게 여러개의 Region 으로 나눈다
      * 각 Region 을 역할과 함께 논리적으로 구분해서 객체를 할당한다
        * Eden, Survivor, Old, Humongous, Available/Unused 등 지역과 역할을 논리적으로 구분한다
          * Humongous: Region 크기의 50% 를 초과하는 객체를 저장한다
          * Available/Unused: 사용되지 않는 Region 을 뜻한다 
* G1 GC 의 핵심은 전체 Heap 을 탐색하는 것이 아닌 Region 을 부분적으로 탐색한다는 점이다
  * 따라서 가비지가 많은 Region 에만 우선적으로 GC 를 수행한다
* G1 GC 도 다른 GC 와 마찬가지로 Minor GC, Major GC 로 나뉘어 수행된다
* Minor GC
  * 한 지역에 객체를 할당하다가 해당 지역이 다 차면 다른 지역에 객체를 할당 후 실행되는 GC
  * G1 GC 는 각 지역을 추적하기 때문에 Garbage First 지역을 찾아서 Mark and Sweep 을 수행한다
    * Eden 지역에서 GC 가 수행되면 살아 남은 객체를 식별(Mark) 후 메모리를 회수(Sweep)한다
    * 살아남은 객체는 다른 지역으로 이동시킨다
      * 복제되는 지역이 Available/Unused 지역이면 해당 지역은 이제 Survivor 영역이 된다
      * 반대로 Eden 영역은 Available/Unused 지역이 된다
* Major GC (Full GC)
  * 시스템이 계속 운영되다가 객체가 너무 많은 경우 수행된다
    * 메모리를 빠르게 회수할 수 없는 경우 수행
  * 이 부분에서 다른 GC 와 가장 큰 차이점을 보인다
    * 기존 GC 의 알고리즘은 모든 Heap 영역에서 GC 가 수행됐기 때문에 시간이 오래 걸렸다
    * G1 GC 는 가비지가 많은 영역을 알고 있기 때문에 GC 를 수행할 지역을 조합해서 그 지역에만 GC 를 수행한다
      * 또한 Concurrent 하게 수행되기 때문에 애플리케이션의 Latency 도 최소화 가능하다
6. Shenandoah GC
* Java 12에 release 됐으며 레드 햇에서 개발한 GC 이다
* 기존 CMS 가 가진 단편화, G1이 가진 pause 의 이슈를 해결했다
* 특징으로는 강력한 Concurrency 와 가벼운 GC 로직으로 heap 사이즈에 영향을 받지 않고 일정한 pause 시간이 소요 된다는 점이다
7. ZGC
* java 11 부터 추가된 GC 로 가장 현재까지는 가장 성능과 안정성이 검증된 GC 이다
* java 15 에 release 됐다
* 큰 메모리 8mb ~ 16TB 에서 효율적으로 GC 를 수행하기 위한 대용량 heap 을 위해 설계된 Low-Latency GC 이다
* GC 포즈 시간을 최소화 하고 높은 애플리케이션 처리량을 유지하기 위해 설계 되었으며 동시 및 병렬로 처리한다
  * Stop-the-World 시간을 10ms 이하로 최대한 적게 가져가기 위해 만들어졌다
  * 실제로 Marking 에만 Stop-the-World 가 진행된다
* ZGC 만의 특별한 기능 중 하나는 실행 중인 프로그램의 중단을 최소화 하면서 응용 프로그램의 스레드와 동시에 대부분의 작업을 수행할 수 있다
  * sliding mark window 를 사용해서 heap 의 live 객체를 표시하는데 소요되는 시간을 줄였다
  * 색상이 지정된 포인트와 로드 장벽을 사용해서 스레드가 실행중이라도 동시에 작업이 수행 가능하며 힙 추적도 가능하다
* ZGC 의 핵심 개념
  * Colored Pointers
    * reference 의 일부 bit 를 사용해서 개체의 상태를 표시한다
    * 8mb ~ 16TB 범위의 힙 영역에 대해 가능하며 힙의 크기에 따라 일시 중지 시간이 증가하지 않는다
    * G1 과 비슷하게 ZGC 도 힙 영역을 파티셔닝 하지만 동일한 사이즈로 분할하지 않는게 차이점이다
    * 동작 방식
      * ZGC 가 객체를 찾아낸 뒤 마킹, 재배치 등의 작업을 지원한다
      * 객체 포인터의 메모리 공간을 활용해서 객체의 상태 값을 저장하고 사용한다
      * 이 알고리즘은 64bit 메모리 공간이 필요하기 때문에 32bit 기반의 플랫폼에서는 사용이 불가능 하다
      * 18bit 의 미 사용 공간과 42bit 의 객체 참조 주소와 총 4bit 의 공간을 차지하는 4개의 color pointer 가 존재한다
        * meta bits 라고 부른다
        * Finalizable: Finalizer 을 통해 참조되는 객체이며 해당 pointer 가 Mark 되어 있다면 non-live Object 이다
        * Remapped: 해당 객체의 재배치 여부를 판단하는 pointer 이며 해당 bit 의 값이 1이라면 최신 참조 상태다
        * Marked 0, Marked 1: 해당 객체가 살아있는 상태 인지 확인한다
          * Load Barrier 에 의해서도 사용되기도 한다
  * Load Barriers
    * JIT 가 특정 위치에 주입한 코드를 말하며 이 코드를 통해 참조가 연결되는 객체의 Meta bits 상태를 확인한다
    * Load Barriers 는 RemapMark 와 Relocation Set 을 확인해서 참조값과 Mark 상태를 업데이트 가능하다
    * Load Barriers 는 스레드가 Stack 으로 Heap Object 참조값을 불러올 때 실행된다 
* Marking Flow
  * Pause Mark Start ~ Pause Mark End 포함
  * Root Set Mark: 객체를 참조하는 RootSet 을 찾아서 마킹하며 STW 가 발생하지만 RootSet 은 상대적으로 적기 때문에 STW 가 짧다
  * Concurrent Mark & Concurrent Remap: 애플리케이션과 동시에 수행되는 단계 
    * 마킹된 RootSet 에서 시작해서 객체 간의 참조 관계를 추적, 접근한 모든 객체를 마킹 한다
  * Concurrent Prepare & Edge Handle: Local Thread 간의 동기화를 진행, 후 Week, Phantom Reference 와 같은 일부 edge case 를 확인하고 정리
* Relocation Flow
  * Pause Relocation Start ~ End 포함
  * Concurrent Relocate
    * Mark Flow 가 끝나고 재배치할 대상을 찾아 Relocation Set 에 배치
    * 매핑되지 않은 대상들은 힙영역에서 정리
    * Relocation Set 에 연결된 대상 중 Root Set 으로부터 참조되는 모든 객체 재배치하고 업데이트
  * Concurrent Relocation and update
    * Relocation Set 에 남아있는 대상들을 추적해서 재배치
    * 이전 참조 값과 변경된 참조 값을 Mapping 하는 forwarding table 에 저장
    * Load barrier 를 이용 Relocation Set 에 배치된 대상을 참조하는 Pointer 를 감지