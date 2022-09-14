package com.ander.cursos.models.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import com.ander.commons.alumnos.models.entity.Alumno;
import com.ander.commons.examenes.models.entity.Examen;

@Entity
@Table(name="cursos")
public class Curso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//44. (validaciones) no es necesario agregar la dependencia 'validation' ya que viene en spring-boot-starter-web
	@NotEmpty
	private String nombre;
	
	@Column(name="create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	// metodo para asignar la fechas
		@PrePersist
		public void prePersist() {
			this.createAt = new Date();
		}
	
	//1. crear relacion -> Alumnos
	@OneToMany(fetch = FetchType.LAZY)
	private List<Alumno> alumnos;	   
	
	//38 -> 1. Añadiendo relación entre cursos y exámenes
	@ManyToMany(fetch = FetchType.LAZY)  // 1 curso puede tener muchos examenes y 1 examen puede estar asociado a muchos cursos por eso es ManytoMany
	private List<Examen> examenes;
	
	//3. Contructor Alumnos
	public Curso() {
		this.alumnos = new ArrayList<>();
		//38 -> 2. 
		this.examenes = new ArrayList<>();

	}
	
	//38 -> 3. getter and setters de examenes
	public List<Examen> getExamenes() {
		return examenes;
	}

	public void setExamenes(List<Examen> examenes) {
		this.examenes = examenes;
	} 
	// agregar 2 metodos add && remove
	public void addExamenes(Examen examen) {
		this.examenes.add(examen);
	}
	
	public void removeExamene(Examen examen) {
		this.examenes.remove(examen);
	}
	
	// ----------------------------------
	
	
	//2. get and set Alumno -------
	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	// get and set end  --------------

	//4. 2 metodos add && remove
	public void addAlumno(Alumno alumnos) {
		this.alumnos.add(alumnos);
	}
	
	public void removeAlumno(Alumno alumno) {
		this.alumnos.remove(alumno);
	}
		
	// ----------------------------
	
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

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	
	

}
