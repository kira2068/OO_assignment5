package simpledatabase;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Table extends Operator{
	private BufferedReader br = null;
	private boolean getAttribute=false;
	private Tuple tuple;
	String DataName="",DataType="",newline="";

	
	public Table(String from){
		this.from = from;
		//Read and fix the Data name and Type
		try{
			br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/datafile/"+from+".csv")));	
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	
	/**
     * Create a new tuple and return the tuple to its parent.
     * Set the attribute list if you have not prepare the attribute list
     * @return the tuple
     */
	@Override
	public Tuple next(){
		if(getAttribute==false){
		try{
			DataName = br.readLine();
			DataType = br.readLine();
			getAttribute=true;
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		if(getAttribute){
			try{
			  newline = br.readLine();
			  if(newline!=null){
			    tuple = new Tuple(DataName,DataType,newline);
			    tuple.setAttributeName();
				tuple.setAttributeType();
				tuple.setAttributeValue();
			    return tuple;}
			  else
				  return null;
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	return tuple;
	}
	

	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return tuple.getAttributeList();
	}
	

	
}