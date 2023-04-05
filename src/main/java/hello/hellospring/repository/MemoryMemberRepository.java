package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import java.util.*;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;  //id 생성

    //회원을 추가하고 return
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    //id로 회원 찾기
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));  //null이 나와도 감싸서 나옴
    }

    //이름으로 회원 찾기
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    //전체 찾기
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
