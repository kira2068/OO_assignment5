package simpledatabase;
import java.util.ArrayList;

public class Join extends Operator{

	private ArrayList<Attribute> newAttributeList;
	private String joinPredicate;
	ArrayList<Tuple> tuples1;

	
	//Join Constructor, join fill
	public Join(Operator leftChild, Operator rightChild, String joinPredicate){
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.joinPredicate = joinPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuples1 = new ArrayList<Tuple>();
		
	}

	/**
     * It is used to return a new tuple which is already joined by the common attribute
     * @return the new joined tuple
     */
	//The record after join with two tables
	@Override
	public Tuple next(){
		
		//get all tuples from one of the table(left)
		while(true){
		  Tuple leftTemp = leftChild.next();
		  tuples1.add(leftTemp);
		  if(leftTemp==null) break;
		}
		
		
		int i,j,countL;
		Tuple rightTemp;
		while ((rightTemp = rightChild.next()) != null){ //run all tuple for another table(right)
			for(countL=0;countL<tuples1.size();countL++){
			  Tuple leftTemp=tuples1.get(countL);
					for(i=0;i<leftTemp.getAttributeList().size();i++){
						for(j=0;j<rightTemp.getAttributeList().size();j++){
							if(leftTemp.getAttributeValue(i).equals(rightTemp.getAttributeValue(j))){ //compare the tuple's value 
									newAttributeList=new ArrayList<Attribute>();
									newAttributeList.addAll(leftTemp.getAttributeList());
									newAttributeList.addAll(rightTemp.getAttributeList());
									Tuple temp =new Tuple(newAttributeList);
									return temp;}
						}
					}
			}
		}
		return null;
	}
	
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		if(joinPredicate.isEmpty())
			return child.getAttributeList();
		else
			return(newAttributeList);
	}

}