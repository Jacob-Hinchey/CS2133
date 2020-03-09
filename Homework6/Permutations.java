import java.util.*;

public class Permutations{
	public static void main(String[] args){
		int listLgn = 1;
		ArrayList<Integer> list = new ArrayList<>();
		long begin, totalTimeTaken = 0;
		while(totalTimeTaken < 100){
			for(int i = 0; i < listLgn; i++){
				list.add(i);
			}
			Collections.shuffle(list, new Random(System.nanoTime()));
			int place = 1;
			begin = System.currentTimeMillis();
			PermManipulate<Integer> perms = new PermManipulate<>(list);
			while(perms.hasNext()){
				ArrayList<Integer> next = perms.next();
				ArrayList<Integer> nextSorted = new ArrayList<>(next);
				Collections.sort(nextSorted);
				if(next.equals(nextSorted)){
					totalTimeTaken = System.currentTimeMillis() - begin;
					totalTimeTaken /= 1000;
					break;
				}
				else{
					place++;
				}
			}
			list.removeAll(list);
			listLgn++;
		}
	}
}

class PermManipulate<E>{
	private boolean hasNoContent;
	private E first;
	private PermManipulate<E> addedNew;
	private List<E> firstInNew;
	private int place;

	public PermManipulate(List<E> list){
		if(list.size() == 0){
			sethasNoContent(true);
			place = 0;
		}
		else{
			sethasNoContent(false);
			first = list.remove(0);
			addedNew = new PermManipulate<E>(list);
			if(addedNew.hasNext()){
				firstInNew = addedNew.next();
			}
			else{
				firstInNew = new ArrayList<E>(0);
			}
			place = 0;
		}
	}

	public void sethasNoContent(boolean none){
		hasNoContent = none;
	}

	public boolean hasNext(){
		return !hasNoContent;
	}

	public ArrayList<E> next(){
		if(place > firstInNew.size()){
			if(addedNew.hasNext()){
				firstInNew = addedNew.next();
				place = 0;
			}
			else{
				sethasNoContent(true);
				addedNew.sethasNoContent(true);
				firstInNew = new ArrayList<E>(0);
			}
		}

		if(hasNext()){
			ArrayList<E> ret = new ArrayList<>(firstInNew);
			ret.add(place, first);
			place++;
			return ret;
		}
		else{
			sethasNoContent(true);
			addedNew.sethasNoContent(true);
			return new ArrayList<E>(0);
		}
	}
}
