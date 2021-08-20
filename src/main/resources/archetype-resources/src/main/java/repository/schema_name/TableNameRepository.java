package ${package}.repository.schema_name;

import ${package}.entity.schema_name.TableName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Optional;

public interface TableNameRepository extends JpaRepository<TableName, BigDecimal> {

    //TODO: rename class name
    //TODO: use your own queries or delete this query

    @Query("select d from TableName d where d.value = ?1")
    Optional<TableName> getCustomValue(String value);

}
