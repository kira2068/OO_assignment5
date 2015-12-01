package simpledatabase;
import java.util.ArrayList;

public class Selection extends Operator{
	
	ArrayList<Attribute> attributeList;
	String whereTablePredicate;
	String whereAttributePredicate;
	String whereValuePredicate;

	
	public Selection(Operator child, String whereTablePredicate, String whereAttributePredicate, String whereValuePredicate) {
		this.child = child;
		this.whereTablePredicate = whereTablePredicate;
		this.whereAttributePredicate = whereAttributePredicate;
		this.whereValuePredicate = whereValuePredicate;
		attributeList = new ArrayList<Attribute>();

	}
	
	
	/**
     * Get the tuple which match to the where condition
     * @return the tuple
     */
	@Override
	public Tuple next(){
		int i;
		Tuple temp=child.next();
		
		if(child.from.equals(whereTablePredicate)==false){
		  return temp;	
		}
			
		else{	
		  while(temp!=null){
				for(i=0; i<temp.getAttributeList().size(); i++){
					if(temp.getAttributeValue(i).equals(whereValuePredicate) && temp.getAttributeName(i).equals(whereAttributePredicate) ){
						attributeList = new ArrayList<Attribute>();
						attributeList.addAll(temp.getAttributeList());
						temp =  new Tuple(attributeList);
						return temp;
						}
				}
				temp = child.next();
			}
		}
		return null;
	}
		
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		
		return(child.getAttributeList());
	}

	
}