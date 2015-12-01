package simpledatabase;
import java.util.ArrayList;
import java.util.Collections;

public class Sort extends Operator{
	
	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	ArrayList<Tuple> tuplesResult;
	int count=0;
	
	public Sort(Operator child, String orderPredicate){
		this.child = child;
		this.orderPredicate = orderPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuplesResult = new ArrayList<Tuple>();
		
	}
	
	
	/**
     * The function is used to return the sorted tuple
     * @return tuple
     */
	@Override
	public Tuple next(){
		boolean Sort = false;
		int SortPos=0;
		if(!Sort){
			Tuple temp=child.next();
			
			while(temp!=null){
				tuplesResult.add(temp);
				temp=child.next();
			}
			
			//check for the position of ordering value
			for (Tuple tuple:tuplesResult){
				for (Attribute A:tuple.attributeList){
					if (A.attributeName.equals(orderPredicate))
						SortPos = tuple.attributeList.indexOf(A); //target sorting index into SortPos
				}
			}
			
			//System.out.print(SortPos);
			
			while(count<tuplesResult.size()){ 
				int cur=count, Pointer;
				String a = tuplesResult.get(cur).getAttributeValue(SortPos).toString();
				for(Pointer=count+1; Pointer<tuplesResult.size(); Pointer++){  //doing sorting
					String b = tuplesResult.get(Pointer).getAttributeValue(SortPos).toString();
					if(a.compareTo(b)>=0){
						cur = Pointer; //get the position of swapping tuple
						a = tuplesResult.get(cur).getAttributeValue(SortPos).toString();
					}
				}

				
				newAttributeList = tuplesResult.get(cur).getAttributeList();
				temp = new Tuple(newAttributeList);
				Collections.swap(tuplesResult, count, cur); //Swap inside tuple array
				count++;
				return temp;
				
				
			}
		Sort=true;
		}
		return null;
	}
			
	
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}