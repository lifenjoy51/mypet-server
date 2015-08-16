package me.lifenjoy51.mypet.server.service;

import java.util.List;

/**
 */
public  interface Park 
{
	/**	 */
	
	public List<AnothersPet> findAnothersPets(User user) ;
	
	/**	 */
	
	public void registerPet(Pet pet) ;
}

