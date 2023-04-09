package com.example.model;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateModel {
	
	@NotNull
	private LocalDate to;
	
	@NotNull
	private LocalDate from;
	
}
