import java.util.*;
public interface Dao<T,K> {
  void insert(T value)throws Exception; Optional<T> findById(K id)throws Exception;
  List<T> findAll()throws Exception; void update(T value)throws Exception; void delete(K id)throws Exception;
}
