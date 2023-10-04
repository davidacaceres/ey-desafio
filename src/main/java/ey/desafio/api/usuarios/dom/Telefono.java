package ey.desafio.api.usuarios.dom;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Telefono {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
//	@ColumnDefault("random_uuid()")
	@Type(type = "uuid-char")
	private UUID idPhone;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user")
	private Usuario usuario;

	@Column(nullable = false)
	private String number;
	@Column(nullable = false)
	private String cityCode;
	@Column(nullable = false)
	private String countryCode;

	@Override
	public String toString() {
		return "Telefono [idPhone=" + idPhone + ", countryCode=" + countryCode + ", cityCode=" + cityCode + ", number="
				+ number + "]";
	}

}
