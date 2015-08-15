package me.lifenjoy51.mypet.server.service;

import java.util.List;/**
 */
public  interface Park 
{
	/**	 */
	
	public List<AnothersPet> findAnothersPets(User parameter) ;
	
	/**	 */
	
	public void registerPet(Pet pet) ;


	public Story getAnothersStory(AnothersPet anothersPet);
}

