package ${package}.entity.schema_name;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

//TODO: replace TABLE_NAME, SCHEMA_NAME, TableName. Take care of case sensivity!

@Entity
@Table(name = "TABLE_NAME", schema = "SCHEMA_NAME")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TableName implements java.io.Serializable {

    //TODO: rename class name
    //TODO: use the correct fields

	@Id
	private BigDecimal id;

	@Column(name = "VALUE", nullable = false, length = 50)
	private String value;

}
