package com.ander.commons.alumnos.models.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="alumnos")
public class Alumno {
	
	// ENTIDAD COMPARTIDA PARA 2 MICROSERVICIOS: microservicio-usuarios && microservicio-cursos
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String nombre;
	
	@NotEmpty
	private String apellido;
	
	@NotEmpty
	@Email
	private String email;
	
	@Column(name="create_at")	
	@Temporal(TemporalType.TIMESTAMP)   // @temporal para q registre fecha y hora
	private Date createAt;
	
	//48 -> 1 Añadiendo atributo foto del tipo Blob en entity Alumno
	//@Lob permite persistir un large Object es decir un blod en la Base de Datos objeto : imagen
	@Lob
	// @JsonIgnore contenido q se guarda es binario y largo y se guarda en el json y para omitir anotamos: @JsonIgnore
	@JsonIgnore
	private byte[]foto;
	// 48 -> 2
	public Integer getFotoHashCode() {
		return (this.foto != null) ? this.foto.hashCode() : null;
	}
	
	
	@PrePersist
	public void prePersist() {
		this.createAt= new Date();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}


	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	// click derecho  -> Source -> Override/Implement Methods..  -> equeals(Object)
	@Override	
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}		
		if(!(obj instanceof Alumno)) {
			return false;
		}		
		Alumno a = (Alumno) obj;
	    // video #27. relacion curso con sus alumnos
		return this.id != null && this.id.equals(a.getId());
	
		
		
	}
	
	
	
	

}
