import java.util.List;
import java.util.Optional;

public interface CrudRepository<ID, E> {

    E save(E entity);

    List<E> findAll();

    Optional<E> findById(ID id);

    boolean deleteById(ID id);

    boolean update(E playerEntity);

}
