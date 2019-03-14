/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.persistence;

import edu.eci.arsw.cinema.model.Cinema;
import edu.eci.arsw.cinema.model.CinemaFunction;
import java.util.List;
import java.util.Set;

/**
 *
 * @author cristian
 */
public interface CinemaPersitence {

	/**
	 * 
	 * @param row
	 *            the row of the seat
	 * @param col
	 *            the column of the seat
	 * @param cinema
	 *            the cinema's name
	 * @param date
	 *            the date of the function
	 * @param movieName
	 *            the name of the movie
	 * @return 
	 * 
	 * @throws CinemaException
	 *             if the seat is occupied, or any other low-level persistence error
	 *             occurs.
	 */
	public boolean buyTicket(int row, int col, String cinema, String date, String movieName) throws CinemaException;

	/**
	 * 
	 * @param cinema
	 *            cinema's name
	 * @param date
	 *            date
	 * @return the list of the functions of the cinema in the given date
	 * @throws CinemaException 
	 */
	public List<CinemaFunction> getFunctionsbyCinemaAndDate(String cinema, String date) throws CinemaException;

	/**
	 * 
	 * @param cinema
	 *            new cinema
	 * @throws CinemaPersistenceException
	 *             n if a cinema with the same name already exists
	 */
	public void saveCinema(Cinema cinema) throws CinemaPersistenceException;

	/**
	 * 
	 * @param name
	 *            name of the cinema
	 * @return Cinema of the given name
	 * @throws CinemaPersistenceException
	 *             if there is no such cinema
	 * @throws CinemaException 
	 */
	public Cinema getCinemaByName(String name) throws CinemaException;

	public Set<Cinema> getAllCinemas() throws CinemaPersistenceException;

	public CinemaFunction getFunctionsbyCinemaAndHourAndMovie(String cinema, String wholeDate, String movieName)
			throws CinemaException;

	void addCinemaFuction(String name, CinemaFunction function);

	void updateCinemaFuction(String name, CinemaFunction function) throws CinemaException;

	

}
