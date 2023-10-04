package ey.desafio.api.usuarios.dom;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
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
public class Usuario {

	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLogin;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
//	@ColumnDefault("random_uuid()")
	@Type(type = "uuid-char")
	private UUID idUser;

	@NotNull
	private String name;

	@NotNull
	@Column(unique = true)
	private String email;

	@NotNull
	private String password;

	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private List<Telefono> phones;

//	@NotBlank
	private String token;

	private boolean isActive;

	@PrePersist
	private void prePersistFunction() {
		created = new Date();
		lastLogin = created;
		modified = created;
	}

	@PreUpdate
	public void preUpdateFunction() {
		modified = new Date();
	}

	@Override
	public String toString() {
		return "Usuario [created=" + created + ", modified=" + modified + ", lastLogin=" + lastLogin + ", idUser="
				+ idUser + ", name=" + name + ", email=" + email + ", password=" + password + ", phones=" + phones
				+ ", token=" + token + ", isActive=" + isActive + "]";
	}

}
