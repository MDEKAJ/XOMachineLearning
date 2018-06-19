package com.uniproject.game.model;
public class Move
{
	private Integer x;
	private Integer y;
	private double value;
	
	public Move(Integer x, Integer y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void setX(Integer x)
	{
		this.x = x;
	}
	
	public void setY(Integer y)
	{
		this.y = y;
	}
	
	public Integer getX()
	{
		return this.x;
	}
	
	public Integer getY()
	{
		return this.y;
	}
	
	public double getValue()
	{
		return this.value;
	}
	
	public void setValue(double value)
	{
		this.value = value;
	}
	
	@Override
	public String toString() 
	{
		return "Move [X =" + this.x + ", Y =" + this.y + "]" + "Value = " + this.value;
	}
}
