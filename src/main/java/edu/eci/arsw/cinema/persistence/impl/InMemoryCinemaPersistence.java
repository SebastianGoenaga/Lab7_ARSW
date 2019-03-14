/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.persistence.impl;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.model.Movie;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

/**
 *
 * @author cristian
 */
@Component("cps")
public class InMemoryCinemaPersistence implements CinemaPersitence {

	private final Map<String, Cinema> cinemas = new HashMap<>();

	public InMemoryCinemaPersistence() {
		String functionDate = "2018-12-18 15:30";
		List<CinemaFunction> functions = new ArrayList<>();
		CinemaFunction funct1 = new CinemaFunction(new Movie("SuperHeroes Movie", "Action"), functionDate);
		CinemaFunction funct2 = new CinemaFunction(new Movie("The Night", "Horror"), functionDate);
		functions.add(funct1);
		functions.add(funct2);
		Cinema c = new Cinema("cinemaX", functions);
		cinemas.put("cinemaX", c);
		
		String functionDate2 = "2018-12-18 15:30";
		List<CinemaFunction> functions2 = new ArrayList<>();
		CinemaFunction funct4 = new CinemaFunction(new Movie("SuperHeroes Movie 2", "Action"), functionDate2);
		CinemaFunction funct5 = new CinemaFunction(new Movie("The Night 2", "Horror"), functionDate2);
		functions2.add(funct4);
		functions2.add(funct5);
		Cinema c2 = new Cinema("cinemaY", functions2);
		cinemas.put("cinemaY", c2);
	}

	@Override
	public boolean buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException {
		Cinema cine = cinemas.get(cinema);
		for (CinemaFunction function : cine.getFunctions()) {

			if (function.getMovie().getName().equals(movieName) && function.getDate().equals(date)) {

				function.buyTicket(row, col);
				return true;
			}

		}
		
		return false;

	}

	@Override
	public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) throws CinemaException {
		Cinema cine = cinemas.get(cinema);
		List<CinemaFunction> fun = new ArrayList<>();
		
		for (CinemaFunction function : cine.getFunctions()) {
			if (function.getDate().equals(date)) {
				fun.add(function);

			}
		}
		return fun;

	}
	
	@Override
	public CinemaFunction getFunctionsbyCinemaAndHourAndMovie(String cinema, String wholeDate, String movieName) throws CinemaException {
		Cinema cine = cinemas.get(cinema);
		if (cine != null) {
			for (CinemaFunction function : cine.getFunctions()) {
				if (function.getDate().equals(wholeDate.split(" ")[0])) {
					if (function.getHour().equals(wholeDate.split(" ")[1])) {
						if (function.getMovie().getName().equals(movieName)) {
							return function;
						}
					}
					

				}
			}
		}
		
		throw new CinemaException("The given function to update does not exit");

	}

	@Override
	public void saveCinema(Cinema c) throws CinemaPersistenceException {
		if (cinemas.containsKey(c.getName())) {
			throw new CinemaPersistenceException("The given cinema already exists: " + c.getName());
		} else {
			cinemas.put(c.getName(), c);
		}
	}

	@Override
	public void addCinemaFuction (String name, CinemaFunction function) {
		Cinema cinema = cinemas.get(name);
		cinema.addFuction(function);
	}
	
	@Override
	public void updateCinemaFuction (String name, CinemaFunction function) throws CinemaException{
		Cinema cinema = cinemas.get(name);
		if (!cinema.updateFunction(function)) {
			throw new CinemaException("The given function to update does not exit");
		}
	}
	
	@Override
	public Cinema getCinemaByName(String name) throws CinemaException {
		Cinema c;
		if ((c = cinemas.get(name)) == null) {
			throw new CinemaException("The given function to update does not exit");
		}
			return c;
	}
	
	//To get map of cinemas
	public Map<String, Cinema> getCinemas() {
		return cinemas;
	}

	@Override
	public Set<Cinema> getAllCinemas() throws CinemaPersistenceException {
		Set<Cinema> allCinemas = new HashSet<Cinema>();
		for (Map.Entry<String, Cinema> entry : cinemas.entrySet()) {
			allCinemas.add(entry.getValue());
		}
		return allCinemas;
	}



}
