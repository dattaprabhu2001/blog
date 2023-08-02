package com.blog.blog.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Getter
@Setter
@Table(name="categories")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	@Column(length = 100)
	private String categoryTitle;
	private String categoryDescription;
	@OneToMany(mappedBy ="category",cascade = CascadeType.ALL)
	List<Post> posts=new ArrayList<>();
}
