/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.services;

import edu.eci.arsw.cinema.filter.CinemaFilter;
import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.persistence.CinemaPersistenceException;
import edu.eci.arsw.cinema.persistence.CinemaPersitence;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cristian
 */
@Service
public class CinemaServices {
	@Autowired
	CinemaPersitence cps;

	@Autowired
	CinemaFilter filter;

	public void addNewCinema(Cinema c) throws CinemaPersistenceException {
		cps.saveCinema(c);
	}

	public Set<Cinema> getAllCinemas() throws CinemaPersistenceException {
		return cps.getAllCinemas();
	}

	/**
	 * 
	 * @param name cinema's name
	 * @return the cinema of the given name created by the given author
	 * @throws CinemaException
	 */
	public Cinema getCinemaByName(String name) throws CinemaPersistenceException {
		try {
			return cps.getCinemaByName(name);
		} catch (CinemaException  e) {
			throw new CinemaPersistenceException("The given name does not exit");
		}

	}

	public boolean buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException {
		return cps.buyTicket(row, col, cinema, date, movieName);

	}

	public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) throws CinemaException {
		return cps.getFunctionsbyCinemaAndDate(cinema, date);

	}
	
	public List<CinemaFunction> getfunctionsFiltered(Cinema cinema, String info, String date)  throws CinemaPersistenceException {
		return filter.filteredFunctions(cinema, date, info);

	}
	
	
	public CinemaFunction getFunctionsbyCinemaAndHourAndMovie(String cinema, String wholeDate, String movieName) throws CinemaException {
		try {
			return cps.getFunctionsbyCinemaAndHourAndMovie(cinema, wholeDate, movieName);
		} catch (CinemaException e) {
			throw new CinemaException("The given name does not exit");
		}
		

	}
	
	public void addCinemaFuction (String name, CinemaFunction function)  {
		cps.addCinemaFuction(name, function);
	}
	
	public void updateCinemaFuction (String name, CinemaFunction function) throws CinemaException{
		cps.updateCinemaFuction(name, function);
	}

}
