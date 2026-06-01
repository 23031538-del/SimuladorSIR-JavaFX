import java.util.List;
import java.util.Optional;

/**
 * Repositorio genérico que define operaciones CRUD básicas.
 * Implementa programación genérica (<T, ID>).
 */
public interface Repository<T, ID> {
    Optional<T> findById(ID id);
    List<T> findAll();
    void save(T entity);      // insert or update según implementación
    void delete(ID id);
}
