package com.ander.microservicio.respuestas.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ander.commons.alumnos.models.entity.Alumno;
import com.ander.commons.examenes.models.entity.Pregunta;

@Entity
@Table(name = "respuestas")
public class Respuesta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String texto;
	
	//54. Añadiendo entity Respuesta y sus relaciones con el alumno y la pregunta
	// 54 -> 1 se agregaron las dependencias en el xml  de: commons-alumnos && commons-examenes
	//  @ManyToOne -> muchas respuestas para 1 solo Alumno, pero una respuesta en particular 1 Solo Alumnno
	@ManyToOne(fetch = FetchType.LAZY)
	private Alumno alumno;
	
	// 54 -> 2 
	// @OneToOne 1 Respuesta para 1 Pregunta
	@OneToOne(fetch = FetchType.LAZY)
	private Pregunta pregunta;
	
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	// 54 -> 3 getters and setters Alumno & Pregunta
	
	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}
	
     
	
}
