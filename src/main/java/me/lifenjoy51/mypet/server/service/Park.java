package me.lifenjoy51.mypet.server.service;

import java.util.List;/**
 */
public  interface Park 
{
	/**	 */
	
	public List<AnothersPet> findAnothersPet(User parameter) ;
	
	/**	 */
	
	public void registerPet(MyPet parameter) ;


	public Story getAnothersStory(AnothersPet anothersPet);
}

