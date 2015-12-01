package simpledatabase;
import java.util.ArrayList;

public class Projection extends Operator{
	
	ArrayList<Attribute> newAttributeList;
	private String attributePredicate;


	public Projection(Operator child, String attributePredicate){
		
		this.attributePredicate = attributePredicate;
		this.child = child;
		newAttributeList = new ArrayList<Attribute>();
		
	}
	
	
	/**
     * Return the data of the selected attribute as tuple format
     * @return tuple
     */
	@Override
	public Tuple next(){
		Tuple temp = child.next();
		int i;
		if(temp!=null){
			newAttributeList.clear();
			for(i=0; i<temp.getAttributeList().size(); i++){ // looping
				if(temp.getAttributeName(i).equals(attributePredicate)){
					newAttributeList.add(temp.getAttributeList().get(i));
					temp = new Tuple(newAttributeList);
				}
			}
			return temp;
		}
		
		else{
		return null;}
		
	}
		

	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}